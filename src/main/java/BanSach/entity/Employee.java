/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BanSach.entity;

import java.sql.Timestamp;
import java.time.LocalDateTime;
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

 private String employeeId;      // Từ khóa chính
    private String fullName;
    private String phoneNumber;
    private String username;
    private String password;
    private boolean role;           // true = admin, false = nhân viên
    private boolean status;         // true = hoạt động, false = khóa
    private String securityQuestion;
    private String securityAnswer;
}

