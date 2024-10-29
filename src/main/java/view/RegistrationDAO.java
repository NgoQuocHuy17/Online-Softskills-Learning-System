package view;

import java.sql.Connection;
import model.Registration;
import java.util.List;
import java.util.Vector;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;
import java.util.logging.Level;

import java.util.ArrayList;
import java.util.List;

public class RegistrationDAO extends DBContext<Registration> {

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
                registration.setTotalCost(rs.getDouble("total_cost"));
                registration.setStatus(rs.getString("status"));
                registration.setValidFrom(rs.getTimestamp("valid_from"));
                registration.setValidTo(rs.getTimestamp("valid_to"));

                // Thêm đối tượng vào danh sách
                registrations.add(registration);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return registrations;
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
