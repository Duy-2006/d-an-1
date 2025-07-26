/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package BanSach.Dao;

import BanSach.entity.Employee;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public interface EmployeeDAO extends CrudDAO<Employee, String> {

   Employee create(Employee entity);
    void update(Employee entity);
    void deleteById(String id);
    List<Employee> findAll();
    Employee findById(String id);
    Employee findByUsername(String username);
    boolean updatePassword(String username, String newPassword);


}
