/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ThuNghiem;

import BanSach.entity.Book;

/**
 *
 * @author KHANH HUNG
 */
public interface SalesPanelController {

    void onAddToInvoice(Book book, int quantity); //thêm sách vào hóa đơn


    void onUpdateQuantity(Book book, int quantity);
}
