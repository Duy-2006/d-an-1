/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BanSach.Dao.impl;

import BanSach.Dao.BillDetailDAO;
import BanSach.entity.BillDetail;
import BanSach.util.XJdbc;
import BanSach.util.XQuery;
import java.util.List;


/**
 *
 * @author ADMIN
 */
public class BillDetailDAOImpl implements BillDetailDAO {

    String createSql = "INSERT INTO BillDetails (BillId, DrinkId, Price, Discount, Quantity) VALUES (?, ?, ?, ?, ?)";
    String updateSql = "UPDATE BillDetails SET BillId = ?, DrinkId = ?, Price = ?, Discount = ?, Quantity = ? WHERE Id = ?";
    String deleteSql = "DELETE FROM BillDetails WHERE Id = ?";
    String findAllSql = "SELECT bd.*, d.name AS drinkName FROM BillDetails bd JOIN Drinks d ON d.Id = bd.DrinkId";
    String findByIdSql = "SELECT bd.*, d.name AS drinkName FROM BillDetails bd JOIN Drinks d ON d.Id = bd.DrinkId WHERE bd.Id = ?";
    String findByBillIdSql = "SELECT bd.*, d.name AS drinkName FROM BillDetails bd JOIN Drinks d ON d.Id = bd.DrinkId WHERE bd.BillId = ?";
    String findByDrinkIdSql = "SELECT bd.*, d.name AS drinkName FROM BillDetails bd JOIN Drinks d ON d.Id = bd.DrinkId WHERE bd.DrinkId = ?";

    @Override
    public List<BillDetail> findByBillId(Long billId) {
        return XQuery.getBeanList(BillDetail.class, findByBillIdSql, billId);
    }

    @Override
    public List<BillDetail> findByDrinkId(String drinkId) {
        return XQuery.getBeanList(BillDetail.class, findByDrinkIdSql, drinkId);
    }

    @Override
    public BillDetail create(BillDetail entity) {
       Object[] values = {
            entity.getBillId(),
            entity.getDrinkId(),
            entity.getPrice(),
            entity.getDiscount(),
            entity.getQuantity()
        };
        XJdbc.executeUpdate(createSql, values);     
        return entity; // Không lấy lại ID nếu không có cơ chế generated keys
    }

    @Override
    public void update(BillDetail entity) {
         Object[] values = {
            entity.getBillId(),
            entity.getDrinkId(),
            entity.getPrice(),
            entity.getDiscount(),
            entity.getQuantity(),
            entity.getId()
        };
        XJdbc.executeUpdate(updateSql, values);
    }

    @Override
    public void deleteById(Long id) {
        XJdbc.executeUpdate(deleteSql, id);
    }

    @Override
    public List<BillDetail> findAll() {
        return XQuery.getBeanList(BillDetail.class, findAllSql);
    }

    @Override
    public BillDetail findById(Long id) {
        return XQuery.getSingleBean(BillDetail.class, findByIdSql, id);
    }
}
