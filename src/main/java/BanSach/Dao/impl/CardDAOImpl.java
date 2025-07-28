/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BanSach.Dao.impl;

import BanSach.Dao.CardDAO;
import BanSach.entity.Card;
import BanSach.util.XJdbc;
import BanSach.util.XQuery;
import java.util.List;


/**
 *
 * @author ADMIN
 */
public class CardDAOImpl implements CardDAO {

    String createSql = "INSERT INTO Cards (Id, Status) VALUES (?, ?)";
    String updateSql = "UPDATE Cards SET Status = ? WHERE Id = ?";
    String deleteSql = "DELETE FROM Cards WHERE Id = ?";
    String findAllSql = "SELECT Id AS id, Status AS status FROM Cards";
    String findByIdSql = "SELECT Id AS id, Status AS status FROM Cards WHERE Id = ?";
  


    @Override
    public Card create(Card entity) {
        XJdbc.executeUpdate(createSql,
                entity.getId(),
                entity.getStatus()
        );
        return entity;
    }

    @Override
    public void update(Card entity) {
        XJdbc.executeUpdate(updateSql,
                entity.getStatus(),
                entity.getId()
        );
        
    }

    @Override
    public List<Card> findAll() {
        return XQuery.getBeanList(Card.class, findAllSql);
    }

    @Override
    public Card findById(Integer id) {
        return XQuery.getSingleBean(Card.class, findByIdSql, id);
    }

    @Override
    public void deleteById(Integer id) {
        XJdbc.executeUpdate(deleteSql, id);
    }

    @Override
    public void deleteById(String id) {
        XJdbc.executeUpdate(deleteSql, id);
    }

    @Override
    public Card findById(String id) {
        return XQuery.getSingleBean(Card.class, findByIdSql, id);
    }
}
