/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package BanSach.Dao;

import BanSach.entity.Employee;

/**
 *
 * @author ADMIN
 */
public interface EmployeeDAO extends CrudDAO<Employee, String> {

    Employee findByUsername(String username);

    boolean updatePassword(String username, String newPassword);

}
