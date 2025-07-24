/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package BanSach.ui;

import BanSach.entity.Book;



/**
 *
 * @author ADMIN
 */
public interface BookController extends CrudController<Book> {

    void fillCategories();

    void chooseFile();

}
