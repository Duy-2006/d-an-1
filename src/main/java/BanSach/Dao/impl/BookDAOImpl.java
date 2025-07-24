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
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class BookDAOImpl implements BookDAO {

    String insertSql = "INSERT INTO Book (BookID, BookName, Author, Publisher, Quantity, SalePrice, CategoryID, Image) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    String updateSql = "UPDATE Book SET BookName=?, Author=?, Publisher=?, Quantity=?, SalePrice=?, CategoryID=?, Image=? "
            + "WHERE BookID=?";
    String deleteSql = "DELETE FROM Book WHERE BookID=?";
    String selectAllSql = "SELECT * FROM Book";
    String selectByIdSql = "SELECT * FROM Book WHERE BookID=?";
    String selectByCategorySql = "SELECT * FROM Book WHERE CategoryID=?";

    @Override
    public Book create(Book entity) {
        XQuery.update(insertSql,
                entity.getBookID(),
                entity.getBookName(),
                entity.getAuthor(),
                entity.getPublisher(),
                entity.getQuantity(),
                entity.getSalePrice(),
                entity.getCategoryID(),
                entity.getImage()
        );
        return entity;
    }

    @Override
    public void update(Book entity) {
        XQuery.update(updateSql,
                entity.getBookName(),
                entity.getAuthor(),
                entity.getPublisher(),
                entity.getQuantity(),
                entity.getSalePrice(),
                entity.getCategoryID(),
                entity.getImage(), // ✅ 7 (bị thiếu)
                entity.getBookID() // ✅ 8 (đúng vị trí WHERE)
        );
    }

    @Override
    public void deleteById(String id) {
        XJdbc.executeUpdate(deleteSql, id);
    }

    @Override
    public List<Book> findAll() {
        return XQuery.getBeanList(Book.class, selectAllSql);
    }

    @Override
    public Book findById(String id) {
        return XQuery.getSingleBean(Book.class, selectAllSql, id);
    }

    @Override
    public List<Book> findByCategoryId(String categoryId) {
        return XQuery.getBeanList(Book.class, selectByCategorySql, categoryId);
    }
    public List<String> findAllBookIDs() {
    List<Book> books = findAll();
    List<String> bookIDs = new ArrayList<>();
    for (Book b : books) {
        bookIDs.add(b.getBookID());
    }
    return bookIDs;
}

}
