/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BanSach.Dao.impl;

import BanSach.Dao.RevenueDAO;
import BanSach.entity.Revenue;
import BanSach.entity.Revenue.ByCategory;
import BanSach.entity.Revenue.ByUser;
import BanSach.util.XQuery;
import java.util.Date;
import java.util.List;


/**
 *
 * @author ADMIN
 */
public class RevenueDAOImpl implements RevenueDAO {

    @Override
    public List<Revenue.ByCategory> getByCategory(Date begin, Date end) {
        System.out.println("getByCategory - Begin: " + begin + ", End: " + end);
        String revenueByCategorySql
                = "SELECT category.Name AS Category, "
                + " sum(detail.Price*detail.Quantity*(1-detail.Discount)) AS Revenue,"
                + " sum(detail.Quantity) AS Quantity,"
                + " min(detail.Price) AS MinPrice,"
                + " max(detail.Price) AS MaxPrice,"
                + " avg(detail.Price) AS AvgPrice "
                + "FROM BillDetails detail "
                + " JOIN Drinks drink ON drink.Id=detail.DrinkId"
                + " JOIN Categories category ON category.Id=drink.CategoryId"
                + " JOIN Bills bill ON bill.Id=detail.BillId "
                + "WHERE bill.Status=1 "
                + " AND bill.Checkout IS NOT NULL "
                + " AND bill.Checkout BETWEEN ? AND ? "
                + "GROUP BY category.Name "
                + "ORDER BY Revenue DESC";
        return XQuery.getBeanList(ByCategory.class, revenueByCategorySql, begin, end);
    }

    @Override
    public List<Revenue.ByUser> getByUser(Date begin, Date end) {
        System.out.println("getByUser - Begin: " + begin + ", End: " + end);
        String revenueByUserSql
                = "SELECT bill.Username AS [User], "
                + " sum(detail.Price*detail.Quantity*(1-detail.Discount)) AS Revenue,"
                + " count(DISTINCT detail.BillId) AS Quantity,"
                + " min(bill.Checkin) AS FirstTime,"
                + " max(bill.Checkin) AS LastTime "
                + "FROM BillDetails detail "
                + " JOIN Bills bill ON bill.Id=detail.BillId "
                + "WHERE bill.Status=1 "
                + " AND bill.Checkout IS NOT NULL "
                + " AND bill.Checkout BETWEEN ? AND ? "
                + "GROUP BY bill.Username "
                + "ORDER BY Revenue DESC";
        return XQuery.getBeanList(ByUser.class, revenueByUserSql, begin, end);
    }
}
