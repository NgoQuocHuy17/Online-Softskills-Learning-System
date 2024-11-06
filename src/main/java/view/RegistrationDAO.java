package view;

import java.sql.Connection;
import java.sql.Date;
import model.Registration;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

public class RegistrationDAO extends DBContext<Registration> {

    public boolean deleteRegistrationById(int registrationId) {
        String query = "DELETE FROM registrations WHERE id = ?";
        try (Connection conn = getConn(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, registrationId);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0; // Return true if a record was deleted
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Return false if there was an error
        }
    }

    public List<Registration> getRegistrationsByUserIdWithPagination(int userId, int page, int pageSize, String category, String searchTerm) {
        List<Registration> registrations = new ArrayList<>();
        StringBuilder query = new StringBuilder("SELECT r.* FROM registrations r "
                + "JOIN courses c ON r.course_id = c.id "
                + "WHERE r.user_id = ?");

        // Thêm điều kiện cho category
        if (category != null && !category.isEmpty()) {
            query.append(" AND c.category = ?"); // Sử dụng c.category để lọc
        }

        // Thêm điều kiện cho searchTerm
        if (searchTerm != null && !searchTerm.isEmpty()) {
            query.append(" AND c.title LIKE ?");
        }

        query.append(" ORDER BY r.valid_from DESC OFFSET ? ROWS FETCH NEXT ? ROWS ONLY");

        try (Connection connection = getConn(); PreparedStatement ps = connection.prepareStatement(query.toString())) {
            ps.setInt(1, userId); // Đặt user ID

            int paramIndex = 2;

            // Thêm tham số cho category
            if (category != null && !category.isEmpty()) {
                ps.setString(paramIndex++, category); // Lọc theo category
            }

            // Thêm tham số cho searchTerm
            if (searchTerm != null && !searchTerm.isEmpty()) {
                ps.setString(paramIndex++, "%" + searchTerm + "%"); // Tìm kiếm theo title
            }

            // Tính toán offset và giới hạn số bản ghi trả về
            ps.setInt(paramIndex++, (page - 1) * pageSize); // Tính toán offset
            ps.setInt(paramIndex, pageSize); // Số bản ghi trả về

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                // Tạo đối tượng Registration từ kết quả truy vấn
                Registration registration = new Registration();
                registration.setId(rs.getInt("id"));
                registration.setUserId(rs.getInt("user_id"));
                registration.setPackageId(rs.getInt("package_id"));
                registration.setCourseId(rs.getInt("course_id"));
                registration.setCreatedBy(rs.getInt("created_by"));
                registration.setTotalCost(rs.getDouble("total_cost"));
                registration.setStatus(rs.getString("status"));
                registration.setValidFrom(rs.getTimestamp("valid_from"));
                registration.setValidTo(rs.getTimestamp("valid_to"));
                registration.setNotes(rs.getString("notes"));

                registrations.add(registration);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return registrations;
    }

    // Hàm tính số trang cần thiết cho phân trang
    public int getTotalRegistrations(int userId, String category, String searchTerm) {
        int totalCount = 0;
        StringBuilder query = new StringBuilder("SELECT COUNT(*) FROM registrations r "
                + "JOIN courses c ON r.course_id = c.id "
                + "WHERE r.user_id = ?");

        // Thêm điều kiện cho category
        if (category != null && !category.isEmpty()) {
            query.append(" AND c.category = ?"); // Lọc theo category
        }

        // Thêm điều kiện cho searchTerm
        if (searchTerm != null && !searchTerm.isEmpty()) {
            query.append(" AND c.title LIKE ?"); // Tìm kiếm theo title
        }

        try (Connection connection = getConn(); PreparedStatement ps = connection.prepareStatement(query.toString())) {
            ps.setInt(1, userId); // Đặt user ID

            int paramIndex = 2;

            // Thêm tham số cho category
            if (category != null && !category.isEmpty()) {
                ps.setString(paramIndex++, category); // Lọc theo category
            }

            // Thêm tham số cho searchTerm
            if (searchTerm != null && !searchTerm.isEmpty()) {
                ps.setString(paramIndex++, "%" + searchTerm + "%"); // Tìm kiếm theo title
            }

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                totalCount = rs.getInt(1); // Lấy tổng số lượng
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return totalCount;
    }

    public List<Registration> getRegistrationsByUserId(int userId) {
        List<Registration> registrations = new ArrayList<>();
        String query = "SELECT * FROM registrations WHERE user_id = ?";

        try (Connection connection = getConn(); PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                // Tạo đối tượng Registration từ kết quả truy vấn
                Registration registration = new Registration();
                registration.setId(rs.getInt("id"));
                registration.setUserId(rs.getInt("user_id"));
                registration.setPackageId(rs.getInt("package_id"));
                registration.setCourseId(rs.getInt("course_id"));
                registration.setCreatedBy(rs.getInt("created_by"));
                registration.setTotalCost(rs.getDouble("total_cost"));
                registration.setStatus(rs.getString("status"));
                registration.setValidFrom(rs.getTimestamp("valid_from"));
                registration.setValidTo(rs.getTimestamp("valid_to"));
                registration.setNotes(rs.getString("notes"));

                // Thêm đối tượng vào danh sách
                registrations.add(registration);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return registrations;
    }

    public List<Registration> getAllRegistrations() {
        List<Registration> registrations = new ArrayList<>();
        String query = "SELECT * FROM registrations";

        try (Connection connection = getConn(); PreparedStatement ps = connection.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                // Tạo đối tượng Registration từ kết quả truy vấn
                Registration registration = new Registration();
                registration.setId(rs.getInt("id"));
                registration.setUserId(rs.getInt("user_id"));
                registration.setPackageId(rs.getInt("package_id"));
                registration.setCourseId(rs.getInt("course_id"));
                registration.setCreatedBy(rs.getInt("created_by"));
                registration.setTotalCost(rs.getDouble("total_cost"));
                registration.setStatus(rs.getString("status"));
                registration.setValidFrom(rs.getTimestamp("valid_from"));
                registration.setValidTo(rs.getTimestamp("valid_to"));
                registration.setNotes(rs.getString("notes"));

                // Thêm đối tượng vào danh sách
                registrations.add(registration);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return registrations;
    }

    // Phương thức để thêm đăng ký vào cơ sở dữ liệu
    public boolean addRegistration(int userId, int packageId, int courseId, double totalCost, int createdBy, String notes) {
        String query = "INSERT INTO registrations (user_id, package_id, course_id, total_cost, created_by, notes) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = getConn(); PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, userId);
            ps.setInt(2, packageId);
            ps.setInt(3, courseId);
            ps.setDouble(4, totalCost);
            ps.setInt(5, createdBy); // truyền created_by từ servlet
            ps.setString(6, notes);

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Phương thức để lấy thông tin đăng ký dựa trên ID
    public Registration getRegistrationById(int id) {
        String query = "SELECT * FROM registrations WHERE id = ?";
        Registration registration = null;

        try (Connection connection = getConn(); PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                registration = new Registration();
                registration.setId(rs.getInt("id"));
                registration.setUserId(rs.getInt("user_id"));
                registration.setPackageId(rs.getInt("package_id"));
                registration.setCourseId(rs.getInt("course_id"));
                registration.setCreatedBy(rs.getInt("created_by"));
                registration.setTotalCost(rs.getDouble("total_cost"));
                registration.setStatus(rs.getString("status"));
                registration.setValidFrom(rs.getTimestamp("valid_from"));
                registration.setValidTo(rs.getTimestamp("valid_to"));
                registration.setNotes(rs.getString("notes"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return registration;
    }

    public boolean updateRegistrationDetails(int registrationId, int userId, int packageId, int courseId, String status, Date validFrom, Date validTo, String notes) {
        String query = "UPDATE registrations SET user_id = ?, package_id = ?, course_id = ?, status = ?, valid_from = ?, valid_to = ?, notes = ? WHERE id = ?";

        try (Connection conn = getConn(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, userId);
            ps.setInt(2, packageId);
            ps.setInt(3, courseId);
            ps.setString(4, status);
            ps.setDate(5, validFrom);
            ps.setDate(6, validTo);
            ps.setString(7, notes);
            ps.setInt(8, registrationId);

            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Registration> select() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Registration select(int... id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int insert(Registration obj) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int update(Registration obj) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int delete(int... id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
