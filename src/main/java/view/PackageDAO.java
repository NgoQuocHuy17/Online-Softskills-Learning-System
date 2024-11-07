package view;

import model.Package;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class PackageDAO extends DBContext<Package> {

    public Package getPackageById(int packageId) {
        Package pkg = null;
        String query = "SELECT * FROM packages WHERE id = ?";

        try (Connection conn = getConn(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, packageId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    pkg = new Package(
                            rs.getInt("id"),
                            rs.getInt("course_id"),
                            rs.getString("package_name"),
                            rs.getDouble("price"),
                            rs.getDouble("sale_price"),
                            rs.getTimestamp("sale_start"),
                            rs.getTimestamp("sale_end")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pkg;
    }

    public List<Package> getPackagesByCourseId(int courseId) {
        List<Package> packages = new ArrayList<>();
        String query = "SELECT * FROM packages WHERE course_id = ?";

        try (Connection conn = getConn(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, courseId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Package pkg = new Package(
                            rs.getInt("id"),
                            rs.getInt("course_id"),
                            rs.getString("package_name"),
                            rs.getDouble("price"),
                            rs.getDouble("sale_price"),
                            rs.getTimestamp("sale_start"),
                            rs.getTimestamp("sale_end")
                    );
                    packages.add(pkg);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return packages;
    }

    public double getPriceById(int packageId) {
        String sql = "SELECT price, sale_price, sale_start, sale_end FROM packages WHERE id = ?";
        double price = 0.0;

        try (Connection conn = getConn(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, packageId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                double regularPrice = rs.getDouble("price");
                double salePrice = rs.getDouble("sale_price");
                LocalDate saleStart = rs.getDate("sale_start").toLocalDate();
                LocalDate saleEnd = rs.getDate("sale_end").toLocalDate();
                LocalDate currentDate = LocalDate.now();

                // Kiểm tra xem ngày hiện tại có trong thời gian sale không
                if (currentDate.isAfter(saleStart.minusDays(1)) && currentDate.isBefore(saleEnd.plusDays(1))) {
                    price = salePrice; // Lấy sale_price nếu đang trong thời gian sale
                } else {
                    price = regularPrice; // Lấy giá thông thường
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return price;
    }

    @Override
    public List<Package> select() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Package select(int... id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int insert(Package obj) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int update(Package obj) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int delete(int... id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
