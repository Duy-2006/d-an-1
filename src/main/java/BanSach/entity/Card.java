/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BanSach.entity;

import java.util.Date;
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
//public class Card {
//
//    private Integer id;
//    private Integer status;
//
//}
public class Card {
    private String maThe;
    private String maKhachHang;
    private String maHang;
    private int diemTichLuy;
    private Date ngayCap;
    private Date ngayHetHan;
    // Getters, setters, constructor
}
