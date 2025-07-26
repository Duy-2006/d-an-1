package BanSach.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//import poly.cafe.entity.User;

/**
 * Lớp tiện ích hỗ trợ truy vấn và chuyển đổi sang đối tượng
 *
 * @author NghiemN
 * @version 1.0
 */
public class XQuery {

    /**
     * Truy vấn 1 đối tượng
     *
     * @param <B> kiểu của đối tượng cần chuyển đổi
     * @param beanClass lớp của đối tượng kết quả
     * @param sql câu lệnh truy vấn
     * @param values các giá trị cung cấp cho các tham số của SQL
     * @return kết quả truy vấn
     * @throws RuntimeException lỗi truy vấn
     */
    public static <B> B getSingleBean(Class<B> beanClass, String sql, Object... values) {
        List<B> list = XQuery.getBeanList(beanClass, sql, values);
        if (!list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }

    /**
     * Truy vấn nhiều đối tượng
     *
     * @param <B> kiểu của đối tượng cần chuyển đổi
     * @param beanClass lớp của đối tượng kết quả
     * @param sql câu lệnh truy vấn
     * @param values các giá trị cung cấp cho các tham số của SQL
     * @return kết quả truy vấn
     * @throws RuntimeException lỗi truy vấn
     */
    public static <B> List<B> getBeanList(Class<B> beanClass, String sql, Object... values) {
        List<B> list = new ArrayList<>();
        try {
            ResultSet resultSet = XJdbc.executeQuery(sql, values);
            while (resultSet.next()) {
                list.add(XQuery.readBean(resultSet, beanClass));
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        return list;
    }

    /**
     * Tạo bean với dữ liệu đọc từ bản ghi hiện tại
     *
     * @param <B> kiểu của đối tượng cần chuyển đổi
     * @param resultSet tập bản ghi cung cấp dữ liệu
     * @param beanClass lớp của đối tượng kết quả
     * @return kết quả truy vấn
     * @throws RuntimeException lỗi truy vấn
     */
     private static String toCamelCase(String columnName) {
        StringBuilder sb = new StringBuilder();
        boolean nextUpper = false;
        for (int i = 0; i < columnName.length(); i++) {
            char c = columnName.charAt(i);
            if (c == '_' || c == ' ') {
                nextUpper = true;
            } else {
                if (nextUpper) {
                    sb.append(Character.toUpperCase(c));
                    nextUpper = false;
                } else {
                    sb.append(c);
                }
            }
        }
        return sb.toString();
    }
    private static <B> B readBean(ResultSet resultSet, Class<B> beanClass) throws Exception {
         B bean = beanClass.getDeclaredConstructor().newInstance();
    var metaData = resultSet.getMetaData();
    int columnCount = metaData.getColumnCount();

    // Lấy danh sách tất cả setter trong bean
    Method[] methods = beanClass.getMethods();

    for (int i = 1; i <= columnCount; i++) {
        String columnName = metaData.getColumnLabel(i);
        Object value = resultSet.getObject(i);

        // Chuẩn hóa tên cột thành camelCase
        String camelCaseName = toCamelCase(columnName);

        // Tên setter mong muốn, viết hoa chữ đầu camelCase
        String setterName = "set" + camelCaseName.substring(0, 1).toUpperCase() + camelCaseName.substring(1);

        // Tìm method setter không phân biệt chữ hoa/thường
        Method setter = null;
        for (Method method : methods) {
            if (method.getName().equalsIgnoreCase(setterName) && method.getParameterCount() == 1) {
                setter = method;
                break;
            }
        }

        if (setter == null) {
            System.out.printf("+ Setter '%s' not found for column '%s'!\r\n", setterName, columnName);
            continue;
        }

        setter.setAccessible(true);

        // Xử lý kiểu dữ liệu, ví dụ LocalDate, float...
        Class<?> paramType = setter.getParameterTypes()[0];
        if (value instanceof java.sql.Date && paramType == java.time.LocalDate.class) {
            value = ((java.sql.Date) value).toLocalDate();
        } else if (value instanceof Number) {
            if (paramType == int.class || paramType == Integer.class) {
                value = ((Number) value).intValue();
            } else if (paramType == long.class || paramType == Long.class) {
                value = ((Number) value).longValue();
            } else if (paramType == float.class || paramType == Float.class) {
                value = ((Number) value).floatValue();
            } else if (paramType == double.class || paramType == Double.class) {
                value = ((Number) value).doubleValue();
            } 
            // Thêm các kiểu số khác nếu cần
        }

        try {
            setter.invoke(bean, value);
            System.out.println("Mapped column: " + columnName + " to " + setter.getName() + " with value: " + value);
        } catch (Exception e) {
            System.out.printf("+ Error mapping column '%s': %s\r\n", columnName, e.getMessage());
        }
    }
    return bean;
    }

    public static void main(String[] args) {
        demo1();
        demo2();
    }

    private static void demo1() {
        String sql = "SELECT * FROM Users WHERE Username=? AND Password=?";
//        User user = XQuery.getSingleBean(User.class, sql, "NghiemN", "123456");
    }

    private static void demo2() {
        String sql = "SELECT * FROM Users WHERE Fullname LIKE ?";
//        List<User> list = XQuery.getBeanList(User.class, sql, "%Nguyễn %");
    }

     public static int update(String sql, Object... args) {
        return XJdbc.executeUpdate(sql, args);
    }

}
