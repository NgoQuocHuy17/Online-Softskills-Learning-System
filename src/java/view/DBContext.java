package view;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Minh
 */

public class DBContext {
    
    private Connection conn;

    public DBContext() {
        this("jdbc:sqlserver://localhost:1433;databaseName=SoftSkillsOnlineLearningSystem;trustServerCertificate=true",
                "sa", "123");
    }
    
    public DBContext(String url, String user, String pass) {
        try {
            //Driver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //connection
            conn = DriverManager.getConnection(url, user, pass);
            System.out.println("Connected");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public Connection getConn() {
        return conn;
    }

}
