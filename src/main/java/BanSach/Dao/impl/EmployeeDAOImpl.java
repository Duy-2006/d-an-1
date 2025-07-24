/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BanSach.Dao.impl;

import BanSach.entity.Employee;
import BanSach.util.XJdbc;
import BanSach.util.XQuery;
import java.util.List;
import BanSach.Dao.EmployeeDAO;

/**
 *
 * @author ADMIN
 */
public class EmployeeDAOImpl implements EmployeeDAO {

    String insertSql = "INSERT INTO Employee (EmployeeID, FullName, PhoneNumber, Username, Password, Role, Status) VALUES (?, ?, ?, ?, ?, ?, ?)";
    String updateSql = "UPDATE Employee SET FullName = ?, PhoneNumber = ?, Password = ?, Role = ?, Status = ? WHERE EmployeeID = ?";
    String deleteSql = "DELETE FROM Employee WHERE EmployeeID = ?";
    String findAllSql = "SELECT * FROM Employee";
    String findByIdSql = "SELECT * FROM Employee WHERE EmployeeID = ?";
    String findByUsernameSql = "SELECT * FROM Employee WHERE Username = ?";
    String updatePasswordSql = "UPDATE Employee SET Password = ? WHERE Username = ?";

    @Override
    public Employee create(Employee entity) {
        Object[] values = {
             entity.getEmployeeID(),
            entity.getFullName(),
            entity.getPhoneNumber(),
            entity.getUsername(),
            entity.getPassword(),
            entity.isRole(),
            entity.isStatus()
        };
        XJdbc.executeUpdate(insertSql, values);
        return entity;
    }

    @Override
    public void update(Employee entity) {
        Object[] values = {
               entity.getFullName(),
            entity.getPhoneNumber(),
            entity.getPassword(),
            entity.isRole(),
            entity.isStatus(),
            entity.getEmployeeID()
        };
        XJdbc.executeUpdate(updateSql, values);
    }

    @Override
    public void deleteById(String id) {
        XJdbc.executeUpdate(deleteSql, id);
    }

    @Override
    public List<Employee> findAll() {
        return XQuery.getBeanList(Employee.class, findAllSql);
    }

    @Override
    public Employee findById(String id) {
        return XQuery.getSingleBean(Employee.class, findByIdSql, id);
    }

    @Override
    public Employee findByUsername(String username) {
        return XQuery.getSingleBean(Employee.class, findByUsernameSql, username);
    }

    @Override
    public boolean updatePassword(String username, String newPassword) {
        
        int result = XJdbc.executeUpdate(updatePasswordSql, newPassword, username);
        return result > 0;
    }

}
