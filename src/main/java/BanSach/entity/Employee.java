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

public class Employee {
    private String maNV;
    private String tenNV;
    private String sdt;
    private String tenDangNhap;
    private String matKhau;
    private boolean vaiTro;
    private boolean trangThai;

    public Employee(String maNV, String tenNV, String sdt, String tenDangNhap, String matKhau, boolean vaiTro, boolean trangThai) {
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.sdt = sdt;
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
        this.vaiTro = vaiTro;
        this.trangThai = trangThai;
    }

    public boolean isRole() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

