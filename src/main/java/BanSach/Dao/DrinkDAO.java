/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package BanSach.Dao;

import BanSach.entity.Drink;
import java.util.List;


/**
 *
 * @author ADMIN
 */
public interface DrinkDAO extends CrudDAO<Drink, String> {
    List<Drink> findByCategoryId(String categoryId);
}
