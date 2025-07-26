/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package BanSach.ui;

import BanSach.entity.Employee;

/**
 *
 * @author ADMIN
 */
public interface EmployeeController extends CrudController<Employee> {

    Employee getForm();

    void setForm(Employee e);

    void fillToTable();

    void create();

    void update();

    void delete();

    void clear();

    void edit();

    void moveFirst();

    void movePrevious();

    void moveNext();

    void moveLast();
}
