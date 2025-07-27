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
 * @author KHANH HUNG
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Invoice {

    private String ngayTao;
    private String ngayThanhToan;
    private String maHD;
    private String tenSach;
    private int soLuong;
    private double giaBan;
    private String trangThai;
}
