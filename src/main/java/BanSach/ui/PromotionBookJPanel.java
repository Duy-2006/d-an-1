/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package BanSach.ui;

import BanSach.Dao.PromotionDAO;
import BanSach.Dao.impl.BookDAOImpl;
import BanSach.Dao.impl.PromotionBookDAOImpl;
import BanSach.Dao.impl.PromotionDAOImpl;
import BanSach.entity.Book;
import BanSach.entity.Promotion;
import BanSach.util.XJdbc;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 */
public class PromotionBookJPanel extends javax.swing.JPanel {
private static final Logger LOGGER = Logger.getLogger(PromotionBookJPanel.class.getName());
    private PromotionDAO promotionDAO = new PromotionDAOImpl();
    private PromotionBookDAOImpl pbDao = new PromotionBookDAOImpl();
    private BookDAOImpl bookDAO = new BookDAOImpl();

    /**
     * Creates new form PromotionBookJPanel
     */
    public void loadAllPromotionBooks() {
         DefaultTableModel model = (DefaultTableModel) tblBooks.getModel();
        model.setRowCount(0);
        try {
            List<String[]> list = pbDao.findAllPromotionBook();
            for (String[] row : list) {
                model.addRow(new Object[]{row[0], row[1]});
            }
            tblBooks.repaint();
            tblBooks.revalidate();
        } catch (Exception e) {
            LOGGER.severe("Error loading promotion books: " + e.getMessage());
            JOptionPane.showMessageDialog(this, "Lỗi khi load dữ liệu sách khuyến mãi: " + e.getMessage());
        }
    }

    public PromotionBookJPanel() {
        initComponents();
        loadPromotionList();
        loadBookList();
        loadBooksInPromotion();

        cboPromotion.addActionListener(e -> loadBooksInPromotion());
        btnadd.addActionListener(e -> addBookToPromotion());
        btnremove.addActionListener(e -> removeBookFromPromotion());

    }
    
   

    public void loadPromotionList() {

        cboPromotion.removeAllItems();
        try {
            List<Promotion> promotions = promotionDAO.findAll();
            cboPromotion.addItem(""); // Thêm tùy chọn rỗng
            for (Promotion p : promotions) {
                cboPromotion.addItem(p.getPromotionID());
            }
        } catch (Exception e) {
            LOGGER.severe("Error loading promotions: " + e.getMessage());
            JOptionPane.showMessageDialog(this, "Lỗi khi load danh sách khuyến mãi: " + e.getMessage());
        }
    }

    private void loadBooksInPromotion() {
        String pid = (String) cboPromotion.getSelectedItem();
        DefaultTableModel model = (DefaultTableModel) tblBooks.getModel();
        model.setRowCount(0);
        if (pid != null && !pid.isEmpty()) {
            try {
                List<String[]> list = pbDao.findPromotionBooksByPromotionID(pid);
                for (String[] row : list) {
                    model.addRow(new Object[]{row[0], row[1]});
                }
                tblBooks.repaint();
                tblBooks.revalidate();
            } catch (Exception e) {
                LOGGER.severe("Error loading books for promotion " + pid + ": " + e.getMessage());
                JOptionPane.showMessageDialog(this, "Lỗi khi load sách khuyến mãi: " + e.getMessage());
            }
        }

    }

    private void loadBookList() {
        cboBookss.removeAllItems();
        try {
            List<Book> books = bookDAO.findAll();
            cboBookss.addItem(""); // Thêm tùy chọn rỗng
            for (Book b : books) {
                cboBookss.addItem(b.getBookID());
            }
        } catch (Exception e) {
            LOGGER.severe("Error loading books: " + e.getMessage());
            JOptionPane.showMessageDialog(this, "Lỗi khi load danh sách sách: " + e.getMessage());
        }
    }

    private void addBookToPromotion() {
       String pid = (String) cboPromotion.getSelectedItem();
        String bid = (String) cboBookss.getSelectedItem();

        if (pid == null || pid.isEmpty() || bid == null || bid.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn khuyến mãi và sách.");
            return;
        }

        if (!pbDao.exists(pid, bid)) {
            try {
                pbDao.insert(pid, bid);
                loadBooksInPromotion();
                JOptionPane.showMessageDialog(this, "Thêm sách vào khuyến mãi thành công!");
            } catch (Exception e) {
                LOGGER.severe("Error adding book to promotion: " + e.getMessage());
                JOptionPane.showMessageDialog(this, "Lỗi khi thêm sách: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Sách đã tồn tại trong khuyến mãi này.");
        }
    }

    private void removeBookFromPromotion() {
       int row = tblBooks.getSelectedRow();
        if (row >= 0) {
            String pid = (String) cboPromotion.getSelectedItem();
            String bid = (String) tblBooks.getValueAt(row, 1); // Lấy BookID từ cột thứ 2
            if (pid == null || pid.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn khuyến mãi.");
                return;
            }
            try {
                pbDao.delete(pid, bid);
                loadBooksInPromotion();
                JOptionPane.showMessageDialog(this, "Xóa sách khỏi khuyến mãi thành công!");
            } catch (Exception e) {
                LOGGER.severe("Error removing book from promotion: " + e.getMessage());
                JOptionPane.showMessageDialog(this, "Lỗi khi xóa sách: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sách để xóa.");
        }
    }

    public List<String[]> findAllPromotionBook() {
        List<String[]> list = new ArrayList<>();
        String sql = "SELECT PromotionID, BookID FROM PromotionBook"; // hoặc Promotion_Book
        try (Connection conn = XJdbc.openConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                String[] row = new String[2];
                row[0] = rs.getString("PromotionID");
                row[1] = rs.getString("BookID");
                list.add(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public JComboBox<String> getCboPromotion() {
    return cboPromotion;
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cboPromotion = new javax.swing.JComboBox<>();
        cboBookss = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblBooks = new javax.swing.JTable();
        btnadd = new javax.swing.JButton();
        btnremove = new javax.swing.JButton();

        jLabel1.setText("Chọn khuyến mãi ");

        jLabel2.setText("Chọn sách ");

        tblBooks.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Mã khuyến mãi ", "Mã sách "
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblBooks);

        btnadd.setText("Thêm ");
        btnadd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnaddActionPerformed(evt);
            }
        });

        btnremove.setText("Xóa ");
        btnremove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnremoveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 971, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addGap(33, 33, 33))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(cboPromotion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cboBookss, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnadd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnremove)
                        .addGap(20, 20, 20))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cboPromotion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboBookss, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnadd)
                    .addComponent(btnremove))
                .addContainerGap(59, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnaddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnaddActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_btnaddActionPerformed

    private void btnremoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnremoveActionPerformed
        // TODO add your handling code here:
        this.removeBookFromPromotion();
    }//GEN-LAST:event_btnremoveActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnadd;
    private javax.swing.JButton btnremove;
    private javax.swing.JComboBox<String> cboBookss;
    private javax.swing.JComboBox<String> cboPromotion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblBooks;
    // End of variables declaration//GEN-END:variables
}
