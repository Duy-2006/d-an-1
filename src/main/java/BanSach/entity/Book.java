/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BanSach.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author ADMIN
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Book {


     private String bookID;         // BookID trong DB
    private String bookName;       // BookName
    private String author;         // Author
    private String publisher;      // Publisher
    private int quantity;          // Quantity
    private double salePrice;      // SalePrice
    private String categoryID;     // CategoryID - khóa ngoại
    @Builder.Default
    private String image = "no_image.png"; // Image - mặc định nếu không có


    


}
