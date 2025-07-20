/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BanSach.entity;

import java.util.Date;
import lombok.*;

/**
 *
 * @author ADMIN
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class BookCategory {

    private String CategoryID ;
    private String CategoryName;
    
    @Override
    public String toString() {
        return this.CategoryName; // Chỉ hiển thị tên, bỏ "Category" và "Name"
    }
    

}
