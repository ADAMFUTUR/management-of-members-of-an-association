package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Utilitaire {

    private Connection connection;

    public Utilitaire() {
        try {
        	

            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/gestionadh";
            String username = "root";
            String password = "";
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            // Handle exception properly based on your application's requirements
        }
    }

    public Connection getConnection() {
        return connection;
    }

   
}
