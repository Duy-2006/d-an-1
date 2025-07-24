/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package BanSach.Dao;

import java.util.List;

/**
 *
 * @author ADMIN
 */
public interface PromotionBookDAO {
     void insert(String promotionID, String bookID);
    void delete(String promotionID, String bookID);
    List<String> findBookIDsByPromotion(String promotionID);
    boolean exists(String promotionID, String bookID);
    
}
