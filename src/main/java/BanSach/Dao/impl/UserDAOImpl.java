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

    String insertSql = "INSERT INTO Users (Username, Password, FullName, Email, PhoneNumber, Address, RegistrationDate, Enabled, Manager) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    String updateSql = "UPDATE Users SET Password = ?, FullName = ?, Email = ?, PhoneNumber = ?, Address = ?, RegistrationDate = ?, Enabled = ?, Manager = ? WHERE Username = ?";
    String deleteSql = "DELETE FROM Users WHERE Username = ?";
    String findAllSql = "SELECT * FROM Users";
    String findByIdSql = "SELECT * FROM Users WHERE Username = ?";
    String sql = "UPDATE Users SET Password = ? WHERE Username = ?";

    @Override
    public User create(User entity) {
        Object[] values = {
            entity.getUsername(),
            entity.getPassword(),
            entity.getFullName(),
            entity.getEmail(),
            entity.getPhoneNumber(),
            entity.getAddress(),
            entity.getRegistrationDate(),
            entity.isEnabled(),
            entity.isManager()
        };
        XJdbc.executeUpdate(insertSql, values);
        return entity;
    }

    @Override
    public void update(User entity) {
        Object[] values = {
            entity.getPassword(),
            entity.getFullName(),
            entity.getEmail(),
            entity.getPhoneNumber(),
            entity.getAddress(),
            entity.getRegistrationDate(),
            entity.isEnabled(),
            entity.isManager(),
            entity.getUsername() // WHERE Username = ?
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

    @Override
    public User findByUsername(String username) {
        return XQuery.getSingleBean(User.class, findByIdSql, username);
    }

    @Override
    public boolean updatePassword(String username, String newPassword) {
        int result = XJdbc.executeUpdate(sql, newPassword, username);
        return result > 0;
    }

}
