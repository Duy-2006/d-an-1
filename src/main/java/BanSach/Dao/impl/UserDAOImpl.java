/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BanSach.Dao.impl;


import BanSach.Dao.UserDAO;
import BanSach.entity.User;
import BanSach.util.XJdbc;
import BanSach.util.XQuery;
import java.util.List;


/**
 *
 * @author ADMIN
 */
public class UserDAOImpl implements UserDAO {

    String createSql = "INSERT INTO Users (Username, Password, Enabled, Fullname, Photo, Manager) VALUES (?, ?, ?, ?, ?, ?)";
    String updateSql = "UPDATE Users SET Password = ?, Enabled = ?, Fullname = ?, Photo = ?, Manager = ? WHERE Username = ?";
    String deleteSql = "DELETE FROM Users WHERE Username = ?";
    String findAllSql = "SELECT * FROM Users";
    String findByIdSql = "SELECT * FROM Users WHERE Username = ?";


    @Override
    public User create(User entity) {
      Object[] values = {
            entity.getUsername(),
            entity.getPassword(),
            entity.isEnabled(),
            entity.getFullname(),
            entity.getPhoto(),
            entity.isManager()           
        };
        XJdbc.executeUpdate(createSql, values);
        return entity;
    }

    @Override
    public void update(User entity) {
        Object[] values = {
        entity.getPassword(),
        entity.isEnabled(),
        entity.getFullname(),
        entity.getPhoto(),
        entity.isManager(),
        entity.getUsername()  // ⚠️ Cần ở cuối cho WHERE
    };
        XJdbc.executeUpdate(updateSql, values);
    }

    @Override
    public void deleteById(String id) {
        XJdbc.executeUpdate(deleteSql, id);
    }

    @Override
    public List<User> findAll() {
     return XQuery.getBeanList(User.class, findAllSql);
}

    @Override
    public User findById(String id) {
       return XQuery.getSingleBean(User.class, findByIdSql, id);
    }
}
