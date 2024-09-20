package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBContext {
    
    private String url;
    private String user;
    private String pass;

    public DBContext() {
        this("jdbc:sqlserver://localhost:1433;databaseName=SoftSkillsOnlineLearningSystem;trustServerCertificate=true",
                "sa", "123456");
    }
    
    public DBContext(String url, String user, String pass) {
        this.url = url;
        this.user = user;
        this.pass = pass;
    }

    public Connection getConn() {
        Connection conn = null;
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
        return conn;
    }
}