/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BanSach.Dao.impl;

import BanSach.Dao.PromotionBookDAO;
import BanSach.util.XJdbc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class PromotionBookDAOImpl implements PromotionBookDAO {

    public void insert(String promotionID, String bookID) {
        String sql = "INSERT INTO Promotion_Book (PromotionID, BookID) VALUES (?, ?)";
        XJdbc.executeUpdate(sql, promotionID, bookID);
    }

    public void delete(String promotionID, String bookID) {
        String sql = "DELETE FROM Promotion_Book WHERE PromotionID = ? AND BookID = ?";
        XJdbc.executeUpdate(sql, promotionID, bookID);
    }

    public boolean exists(String promotionID, String bookID) {
        String sql = "SELECT * FROM Promotion_Book WHERE PromotionID = ? AND BookID = ?";
        ResultSet rs = XJdbc.executeQuery(sql, promotionID, bookID);
        try {
            return rs.next();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> findBookIDsByPromotion(String promotionID) {
        List<String> list = new ArrayList<>();
        String sql = "SELECT BookID FROM Promotion_Book WHERE PromotionID = ?";
        ResultSet rs = XJdbc.executeQuery(sql, promotionID);
        try {
            while (rs.next()) {
                list.add(rs.getString("BookID"));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

}
