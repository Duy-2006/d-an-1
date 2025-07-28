/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ThuNghiem;

import BanSach.entity.Book;
import BanSach.ui.SalesJPanel;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author KHANH HUNG
 */
public class Test {
   public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("TEST Sách + Hóa đơn");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);

            // Tạo SalesJPanel giả để test
            SalesJPanel salesPanel = new SalesJPanel(); // Giả sử bạn đã tạo cái này

            // Tạo 1 cuốn sách để gán vào panel
            Book book = Book.builder()
                    .id("B001")
                    .name("Lập trình Java")
                    .salePrice(100000)
                    .image("product.png")
                    .build();

            // Tạo SachPanel và set dữ liệu
            SachPanel sachPanel = new SachPanel(salesPanel);
            sachPanel.setBook(book);

            // Thêm vào giao diện
            frame.setLayout(new BorderLayout());
//            frame.add(sachPanel, BorderLayout.NORTH);
            frame.add(salesPanel, BorderLayout.CENTER);

            frame.setVisible(true);
            
            frame.pack(); // ✅ tự động tính size theo nội dung

        });
    } 
   
   
}
