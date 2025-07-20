/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package BanSach.ui;

import BanSach.entity.Drink;



/**
 *
 * @author ADMIN
 */
public interface DrinkController extends CrudController<Drink> {

    void fillCategories();

    void chooseFile();

}
