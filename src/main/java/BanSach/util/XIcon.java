package BanSach.util;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class XIcon {
    /**
     * Đọc icon từ resource hoặc file
     * @param path đường dẫn file, đường dẫn resource hoặc tên resource
     * @return ImageIcon
     */
    public static ImageIcon getIcon(String path) {
        try {
        if (!path.contains("/") && !path.contains("\\")) { // resource name
            return getIcon("/poly/cafe/icons/" + path);
        }
        if (path.startsWith("/")) { // resource path
            java.net.URL location = XIcon.class.getResource(path);
            if (location != null) {
                return new ImageIcon(location);
            } else {
                System.err.println("Resource not found: " + path);
                return null;
            }
        }
        return new ImageIcon(path); // fallback to file path
    } catch (Exception e) {
        System.err.println("Error loading icon: " + path);
        return null;
    }
    }
    /**
     * Đọc icon theo kích thước
     * @param path đường dẫn file hoặc tài nguyên
     * @param width chiều rộng
     * @param height chiều cao
     * @return Icon
     */
    public static ImageIcon getIcon(String path, int width, int height) {
        ImageIcon icon = getIcon(path);
    if (icon == null) {
        icon = getIcon("default.png"); // ảnh mặc định nếu null
    }

    // Kiểm tra null tiếp (trường hợp default cũng không tồn tại)
    if (icon == null || icon.getIconWidth() <= 0 || icon.getIconHeight() <= 0 || width <= 0 || height <= 0) {
        System.err.println("Không thể resize ảnh: " + path + " - Kích thước không hợp lệ.");
        return new ImageIcon(); // trả về ảnh trống
    }

    Image image = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
    return new ImageIcon(image);
    }
    /**
     * Thay đổi icon của JLabel
     * @param label JLabel cần thay đổi
     * @param path đường dẫn file hoặc tài nguyên
     */
    public static void setIcon(JLabel label, String path) {
         // Nếu chưa render xong, set icon sau 1 chút
    if (label.getWidth() <= 0 || label.getHeight() <= 0) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            label.setIcon(getIcon(path, 100, 100)); // fallback size
        });
    } else {
        label.setIcon(getIcon(path, label.getWidth(), label.getHeight()));
    }
    }
    /**
     * Thay đổi icon của JLabel
     * @param label JLabel cần thay đổi
     * @param file file icon
     */
    public static void setIcon(JLabel label, File file) {
        XIcon.setIcon(label, file.getAbsolutePath());
    }
    /**
     * Sao chép file vào thư mục với tên file mới là duy nhất
     * @param fromFile file cần sao chép
     * @param folder thư mục đích
     * @return File đã sao chép
     */
    public static File copyTo(File fromFile, String folder) {
        String fileExt = fromFile.getName().substring(fromFile.getName().lastIndexOf("."));
        File toFile = new File(folder, XStr.getKey() + fileExt);
        toFile.getParentFile().mkdirs();
        try {
            Files.copy(fromFile.toPath(), toFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            return toFile;
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
    public static File copyTo(File fromFile) {
        return copyTo(fromFile, "files");
    }
}
