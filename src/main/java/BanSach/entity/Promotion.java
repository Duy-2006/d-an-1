/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BanSach.entity;

import lombok.*;
import java.time.LocalDate;

/**
 *
 * @author ADMIN
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Promotion {

    private String promotionID;      // Mã khuyến mãi
    private String promotionName;    // Tên chương trình khuyến mãi
    private LocalDate startDate;     // Ngày bắt đầu
    private LocalDate endDate;       // Ngày kết thúc
    private float discountPercent;   // Phần trăm giảm giá
    private boolean status;
    
     @Override
    public String toString() {
        return promotionID; // để hiển thị trong combo box
    }
    
   
}
