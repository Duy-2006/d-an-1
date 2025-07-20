/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BanSach.entity;

import lombok.*;

/**
 *
 * @author ADMIN
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Category {

    private String Id;
    private String name;
    
    @Override
    public String toString() {
        return this.name; // Chỉ hiển thị tên, bỏ "Category" và "Name"
    }
    

}
