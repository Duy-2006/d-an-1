/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BanSach.Dao.impl;

import BanSach.Dao.PromotionDAO;
import BanSach.entity.Promotion;
import BanSach.util.XJdbc;
import BanSach.util.XQuery;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class PromotionDAOImpl implements PromotionDAO {

    String insertSql = "INSERT INTO Promotion (PromotionID, PromotionName, StartDate, EndDate, DiscountPercent, Status) VALUES (?, ?, ?, ?, ?, ?)";
    String updateSql = "UPDATE Promotion SET PromotionName = ?, StartDate = ?, EndDate = ?, DiscountPercent = ?, Status = ? WHERE PromotionID = ?";
    String deleteSql = "DELETE FROM Promotion WHERE PromotionID = ?";
    String findAllSql = "SELECT * FROM Promotion";
    String findByIdSql = "SELECT * FROM Promotion WHERE PromotionID = ?";

    @Override
    public Promotion create(Promotion entity) {
        Object[] values = {
            entity.getPromotionID(),
            entity.getPromotionName(),
            entity.getStartDate(),
            entity.getEndDate(),
            entity.getDiscountPercent(),
            entity.isStatus() // thêm status
        };
        XJdbc.executeUpdate(insertSql, values);
        return entity;
    }

    @Override
    public void update(Promotion entity) {
        Object[] values = {
            entity.getPromotionName(),
            entity.getStartDate(),
            entity.getEndDate(),
            entity.getDiscountPercent(),
            entity.isStatus(),
            entity.getPromotionID()
        };
        XJdbc.executeUpdate(updateSql, values);
    }

    @Override
    public void deleteById(String id) {
        XJdbc.executeUpdate(deleteSql, id);
    }

    @Override
    public List<Promotion> findAll() {
        return XQuery.getBeanList(Promotion.class, findAllSql);
    }

    @Override
    public Promotion findById(String id) {
        return XQuery.getSingleBean(Promotion.class, findByIdSql, id);
    }

}
