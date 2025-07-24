/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BanSach.Dao.impl;

import BanSach.entity.BookCategory;
import BanSach.util.XJdbc;
import BanSach.util.XQuery;
import java.util.List;
import BanSach.Dao.BookCategoryDAO;


/**
 *
 * @author ADMIN
 */
public class BookCategoryDAOImpl implements BookCategoryDAO {

   String createSql = "INSERT INTO BookCategory(CategoryID, CategoryName) VALUES(?, ?)";
String updateSql = "UPDATE BookCategory SET CategoryName=? WHERE CategoryID=?";
String deleteSql = "DELETE FROM BookCategory WHERE CategoryID=?";
String findAllSql = "SELECT CategoryID AS categoryID, CategoryName AS categoryName FROM BookCategory";
String findByIdSql = "SELECT CategoryID AS categoryID, CategoryName AS categoryName FROM BookCategory WHERE CategoryID=?";


    @Override
    public BookCategory create(BookCategory entity) {
        Object[] values = {
            entity.getCategoryID(),
            entity.getCategoryName()
        };
        XJdbc.executeUpdate(createSql, values);
        return entity;
    }

    @Override
    public void update(BookCategory entity) {
        Object[] values = {
            entity.getCategoryName(),
            entity.getCategoryID()
        };
        XJdbc.executeUpdate(updateSql, values);
    }

    @Override
    public void deleteById(String id) {
        XJdbc.executeUpdate(deleteSql, id);

    }

    @Override
    public List<BookCategory> findAll() {
        return XQuery.getBeanList(BookCategory.class, findAllSql);
    }

    @Override
    public BookCategory findById(String id) {
        return XQuery.getSingleBean(BookCategory.class, findByIdSql, id);
    }

}
