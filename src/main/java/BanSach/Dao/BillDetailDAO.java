/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package BanSach.Dao;

import BanSach.entity.BillDetail;
import java.util.List;


/**
 *
 * @author ADMIN
 */
public interface BillDetailDAO extends CrudDAO<BillDetail, Long> {

    List<BillDetail> findByBillId(Long billId);

    List<BillDetail> findByDrinkId(String drinkId);

}
