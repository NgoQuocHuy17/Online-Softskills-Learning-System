package view;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Minh
 */
public abstract class DBContext<E> {

    private Connection conn;

    public DBContext() {
        this("jdbc:sqlserver://localhost:1433;databaseName=OnlineSoftSkillsLearningSystem;trustServerCertificate=true",
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

    public abstract List<E> select();

    public abstract E select(int... id);

    public abstract int insert(E oj);

    public abstract int update(E oj);

    public abstract int delete(int... id);

    public Connection getConn() {
        return conn;
    }
}
