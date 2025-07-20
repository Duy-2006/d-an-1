/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package BanSach.Dao;

import BanSach.Dao.CrudDAO;
import BanSach.entity.Bill;
import java.util.Date;
import java.util.List;


/**
 *
 * @author ADMIN
 */
public interface BillDAO extends CrudDAO<Bill, Long> {

    List<Bill> findByTimeRange(Date begin, Date end);

    List<Bill> findByCardId(Integer cardId);

    List<Bill> findByUsername(String username);
    
    public Bill findServicingByCardId(Integer cardId);
    
    List<Bill> findByUserAndTimeRange(String username, Date begin, Date end);

    public Object findById(String id);

}
