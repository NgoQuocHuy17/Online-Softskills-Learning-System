package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Slider;

public class SliderDAO {
    private Connection conn;

    public SliderDAO() {
        DBContext dbContext = new DBContext();
        this.conn = dbContext.getConn();
    }

    // Method to get all visible sliders from the database
    public List<Slider> getVisibleSliders() {
        List<Slider> sliders = new ArrayList<>();
        String sql = "SELECT id, title, image_url, backlink, status FROM sliders WHERE status = 'Visible'";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Slider slider = new Slider(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("image_url"),
                    rs.getString("backlink"),
                    rs.getString("status")
                );
                sliders.add(slider);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sliders;
    }
}
