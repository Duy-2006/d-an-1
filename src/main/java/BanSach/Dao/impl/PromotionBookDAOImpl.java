/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BanSach.Dao.impl;

import BanSach.Dao.PromotionBookDAO;
import BanSach.util.XJdbc;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class PromotionBookDAOImpl implements PromotionBookDAO {

    private static final String TABLE_NAME = "Promotion_Book";

    @Override
    public void insert(String promotionID, String bookID) {
        String sql = "INSERT INTO " + TABLE_NAME + " (PromotionID, BookID) VALUES (?, ?)";
        try (Connection conn = XJdbc.openConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, promotionID);
            ps.setString(2, bookID);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi thêm liên kết khuyến mãi-sách: " + e.getMessage());
        }
    }

    public void delete(String promotionID, String bookID) {
        String sql = bookID == null
                ? "DELETE FROM " + TABLE_NAME + " WHERE PromotionID = ?"
                : "DELETE FROM " + TABLE_NAME + " WHERE PromotionID = ? AND BookID = ?";
        try (Connection conn = XJdbc.openConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, promotionID);
            if (bookID != null) {
                ps.setString(2, bookID);
            }
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi xóa liên kết khuyến mãi-sách: " + e.getMessage());
        }
    }

    public boolean exists(String promotionID, String bookID) {
        String sql = "SELECT 1 FROM " + TABLE_NAME + " WHERE PromotionID = ? AND BookID = ?";
        try (Connection conn = XJdbc.openConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, promotionID);
            ps.setString(2, bookID);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi kiểm tra liên kết: " + e.getMessage());
        }
    }

    public List<String> findBookIDsByPromotion(String promotionID) {
        List<String> list = new ArrayList<>();
        String sql = "SELECT BookID FROM " + TABLE_NAME + " WHERE PromotionID = ?";
        try (Connection conn = XJdbc.openConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, promotionID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getString("BookID"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi tìm sách liên kết: " + e.getMessage());
        }
        return list;
    }

    public List<String[]> findAllPromotionBook() {
        List<String[]> list = new ArrayList<>();
        String sql = "SELECT PromotionID, BookID FROM " + TABLE_NAME;
        try (Connection conn = XJdbc.openConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new String[]{rs.getString("PromotionID"), rs.getString("BookID")});
            }
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi lấy danh sách liên kết khuyến mãi-sách: " + e.getMessage());
        }
        return list;
    }

    public List<String[]> findPromotionBooksByPromotionID(String promotionID) {
        List<String[]> list = new ArrayList<>();
        String sql = "SELECT PromotionID, BookID FROM " + TABLE_NAME + " WHERE PromotionID = ?";
        try (Connection conn = XJdbc.openConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, promotionID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new String[]{rs.getString("PromotionID"), rs.getString("BookID")});
            }
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi lấy sách khuyến mãi: " + e.getMessage());
        }
        return list;
    
    }

}
