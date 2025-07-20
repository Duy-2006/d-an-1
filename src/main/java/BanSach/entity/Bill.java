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
public class Bill {

    public enum Status {
        Servicing, Completed, Canceled
    }
    private Long id;
    private String username;
    private Integer cardId;
    private Date checkin;
    private Date checkout;
    private int status;

//   @Builder(builderMethodName = "create")
//    public static Bill create(Long id, String username, Integer cardId, Date checkout, int status) {
//        return new Bill(id, username, cardId, new Date(), checkout, status);
//    }

    
}
