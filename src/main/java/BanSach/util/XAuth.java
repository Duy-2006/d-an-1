/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BanSach.util;

import BanSach.entity.Employee;



/**
 *
 * @author ADMIN
 */
public class XAuth {

  /**
 * Tiện ích xác thực người dùng hiện tại
 * Giữ thông tin người dùng đã đăng nhập và hỗ trợ kiểm tra quyền hạn
 */

    // Biến lưu người dùng hiện tại (đã đăng nhập)
    public static Employee user = null;

    /**
     * Đăng xuất: xoá người dùng hiện tại
     */
    public static void clear() {
        user = null;
    }

    /**
     * Kiểm tra đã đăng nhập hay chưa
     */
    public static boolean isLogin() {
        return user != null;
    }

    /**
     * Kiểm tra người dùng hiện tại có phải là quản lý (admin) hay không
     */
    public static boolean isManager() {
        return isLogin() && user.isStatus();
    }

    /**
     * Lấy tên đăng nhập của người dùng hiện tại
     */
    public static String getUsername() {
        return isLogin() ? user.getUsername() : "";
    }

    /**
     * Lấy họ tên người dùng (nếu đã đăng nhập)
     */
    public static String getFullName() {
        return isLogin() ? user.getFullName() : "";
    }
}
