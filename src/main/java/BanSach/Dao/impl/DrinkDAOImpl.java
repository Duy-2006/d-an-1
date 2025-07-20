/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BanSach.Dao.impl;

import BanSach.Dao.DrinkDAO;
import BanSach.entity.Drink;
import BanSach.util.XJdbc;
import BanSach.util.XQuery;
import java.util.List;


/**
 *
 * @author ADMIN
 */
public class DrinkDAOImpl implements DrinkDAO {

    String createSql = "INSERT INTO Drinks(Id, Name, Price, Discount, Image, Available, CategoryId) VALUES (?, ?, ?, ?, ?, ?, ?)";
    String updateSql = "UPDATE Drinks SET Name=?, Price=?, Discount=?, Image=?, Available=?, CategoryId=? WHERE Id=?";
    String deleteSql = "DELETE FROM Drinks WHERE Id=?";
    String findAllSql = "SELECT * FROM Drinks";
    String findByIdSql = "SELECT * FROM Drinks WHERE Id=?";
    String findByCategoryIdSql = "SELECT * FROM Drinks WHERE CategoryId = ? AND Available = 1";

    @Override
    public List<Drink> findByCategoryId(String categoryId) {
        
        return XQuery.getBeanList(Drink.class, findByCategoryIdSql, categoryId);
    }

    @Override
    public Drink create(Drink entity) {
        XQuery.update(createSql,
                entity.getId(),
                entity.getName(),
                entity.getPrice(),
                entity.getDiscount(),
                entity.getImage(),
                entity.isAvailable(),
                entity.getCategoryid()
        );
        return entity;
    }

    @Override
    public void update(Drink entity) {
        XQuery.update(updateSql,
                entity.getName(),
                entity.getPrice(),
                entity.getDiscount(),
                entity.getImage(),
                entity.isAvailable(),
                entity.getCategoryid(),
                entity.getId()
        );
    }

    @Override
    public void deleteById(String id) {
        XJdbc.executeUpdate(deleteSql, id);
    }

    @Override
    public List<Drink> findAll() {
        return XQuery.getBeanList(Drink.class, findAllSql);
    }

    @Override
    public Drink findById(String id) {
        return XQuery.getSingleBean(Drink.class, findByIdSql, id);
    }

}
