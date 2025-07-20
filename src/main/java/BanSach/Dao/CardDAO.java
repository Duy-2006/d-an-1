/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package BanSach.Dao;

import BanSach.entity.Card;



/**
 *
 * @author ADMIN
 */
public interface CardDAO extends CrudDAO<Card, Integer> {

    public void deleteById(String id);

    public Card findById(String id);
    
}
