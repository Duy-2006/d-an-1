/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BanSach.Dao.impl;

import BanSach.Dao.BillDAO;
import BanSach.entity.Bill;
import BanSach.util.XAuth;
import BanSach.util.XJdbc;
import BanSach.util.XQuery;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.List;


/**
 *
 * @author ADMIN
 */
public class BillDAOImpl implements BillDAO {

    String createSql = "INSERT INTO Bills(Username, CardId, Checkin, Checkout, Status) OUTPUT INSERTED.Id VALUES (?, ?, ?, ?, ?)";
    String updateSql = "UPDATE Bills SET Username = ?, CardId = ? WHERE Id = ?";
    String deleteSql = "DELETE FROM Bills WHERE Id = ?";
    String findAllSql = "SELECT * FROM Bills";
    String findByIdSql = "SELECT * FROM Bills WHERE Id = ?";
    String findByUsernameSql = "SELECT * FROM Bills WHERE Username = ?";
    String findByCardIdSql = "SELECT * FROM Bills WHERE CardId = ?";

    @Override
    public List<Bill> findByCardId(Integer cardId) {
        return XQuery.getBeanList(Bill.class, findByCardIdSql, cardId);
    }

    @Override
    public List<Bill> findByUsername(String username) {
        return XQuery.getBeanList(Bill.class, findByUsernameSql, username);
    }

    @Override
    public Bill create(Bill entity) {
        try (Connection conn = XJdbc.openConnection(); PreparedStatement stmt = conn.prepareStatement(createSql)) {

            stmt.setString(1, entity.getUsername());
            stmt.setInt(2, entity.getCardId());
            stmt.setTimestamp(3, new java.sql.Timestamp(entity.getCheckin().getTime()));

            if (entity.getCheckout() != null) {
                stmt.setTimestamp(4, new java.sql.Timestamp(entity.getCheckout().getTime()));
            } else {
                stmt.setNull(4, java.sql.Types.TIMESTAMP);
            }

            stmt.setInt(5, entity.getStatus());

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                entity.setId(rs.getLong(1)); // ✅ Lấy id sinh tự động
            }

        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi tạo Bill: " + e.getMessage(), e);
        }

        return entity;
    }

    @Override
    public void update(Bill entity) {
        String updateSql = "UPDATE Bills SET Username = ?, CardId = ?, Checkin = ?, Checkout = ?, Status = ? WHERE Id = ?";
        Object[] values = {
            entity.getUsername(),
            entity.getCardId(),
            entity.getCheckin() != null ? new java.sql.Timestamp(entity.getCheckin().getTime()) : null,
            entity.getCheckout() != null ? new java.sql.Timestamp(entity.getCheckout().getTime()) : null,
            entity.getStatus(),
            entity.getId()
        };
        try {
            int rowsAffected = XJdbc.executeUpdate(updateSql, values);
            System.out.println("Update bill ID=" + entity.getId() + ", Rows affected: " + rowsAffected);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Lỗi khi cập nhật hóa đơn: " + e.getMessage());
        }
    }

    @Override
    public void deleteById(Long id) {
        XJdbc.executeUpdate(deleteSql, id);
    }

    @Override
    public List<Bill> findAll() {
        return XQuery.getBeanList(Bill.class, findAllSql);
    }

    @Override
    public Bill findById(Long id) {
        return XQuery.getSingleBean(Bill.class, findByIdSql, id);
    }

    @Override
    public List<Bill> findByTimeRange(Date begin, Date end) {
        String sql = "SELECT * FROM Bills " + " WHERE Checkin BETWEEN ? AND ? ORDER BY Checkin DESC";
        return XQuery.getBeanList(Bill.class, sql, begin, end);
    }

    @Override
    public Bill findServicingByCardId(Integer cardId) {
        String sql = "SELECT * FROM Bills WHERE CardId=? AND Status=0";
        Bill bill = XQuery.getSingleBean(Bill.class, sql, cardId);
        if (bill == null) { // không tìm thấy -> tạo mới
            Bill newBill = new Bill();
            newBill.setCardId(cardId);
            newBill.setCheckin(new Date());
            newBill.setStatus(0); // đang phục vụ
            newBill.setUsername(XAuth.user.getUsername());
            bill = this.create(newBill); // insert
        }
        return bill;
    }

    @Override
    public List<Bill> findByUserAndTimeRange(String username, Date begin, Date end) {
        String sql = "SELECT * FROM Bills "
                + " WHERE Username=? AND Checkin BETWEEN ? AND ?";
        return XQuery.getBeanList(Bill.class, sql, username, begin, end);

    }

    @Override
    public Object findById(String id) {
        return XQuery.getSingleBean(Bill.class, findByIdSql, id);
    }

}
