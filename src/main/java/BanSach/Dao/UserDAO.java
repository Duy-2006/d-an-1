/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package BanSach.Dao;

import BanSach.entity.User;

/**
 *
 * @author ADMIN
 */
public interface UserDAO extends CrudDAO<User, String> {

    User findByUsername(String username);

    boolean updatePassword(String username, String newPassword);

}
