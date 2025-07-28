/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BanSach.Dao.impl;

import BanSach.entity.Book;
import BanSach.util.XJdbc;
import BanSach.util.XQuery;
import java.util.List;
import BanSach.Dao.BookDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class BookDAOImpl implements BookDAO {

    String createSql = "INSERT INTO Drinks(Id, Name, Price, Discount, Image, Available, CategoryId) VALUES (?, ?, ?, ?, ?, ?, ?)";
    String updateSql = "UPDATE Drinks SET Name=?, Price=?, Discount=?, Image=?, Available=?, CategoryId=? WHERE Id=?";
    String deleteSql = "DELETE FROM Drinks WHERE Id=?";
    String findAllSql = "SELECT * FROM Drinks";
    String findByIdSql = "SELECT * FROM Drinks WHERE Id=?";
    String findByCategoryIdSql = "SELECT * FROM Drinks WHERE CategoryId = ? AND Available = 1";

    @Override
    public List<Book> findByCategoryId(String categoryId) {

        return XQuery.getBeanList(Book.class, findByCategoryIdSql, categoryId);
    }

    private Connection conn;

    public BookDAOImpl() {
        this.conn = XJdbc.openConnection(); // ✅ gọi từ class XJdbc
    }
    
    

//    @Override
//    public Book create(Book entity) {
//        XQuery.update(createSql,
//                entity.getId(),
//                entity.getName(),
//                entity.getPrice(),
//                entity.getDiscount(),
//                entity.getImage(),
//                entity.isAvailable(),
//                entity.getCategoryid()
//        );
//        return entity;
//    }
//    @Override
////    public void update(Book entity) {
////        XQuery.update(updateSql,
////                entity.getName(),
////                entity.getPrice(),
////                entity.getDiscount(),
////                entity.getImage(),
////                entity.isAvailable(),
////                entity.getCategoryid(),
////                entity.getId()
////        );
////    }
    @Override
    public void deleteById(String id) {
        XJdbc.executeUpdate(deleteSql, id);
    }

    @Override
    public List<Book> findAll() {
        return XQuery.getBeanList(Book.class, findAllSql);
    }

    @Override
    public Book findById(String id) {
        return XQuery.getSingleBean(Book.class, findByIdSql, id);
    }

    @Override
    public List<Book> timKiemSach(String tuKhoa) {
        List<Book> list = new ArrayList<>();
        String sql = "SELECT * FROM Book WHERE BookName LIKE ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + tuKhoa + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String id = rs.getString("BookID");
                String name = rs.getString("BookName");
                double salePrice = rs.getDouble("SalePrice");
                String image = rs.getString("image");

                list.add(new Book(id, name, image, salePrice));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public Book create(Book entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Book entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
