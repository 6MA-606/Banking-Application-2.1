package bankingapplication2;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BankingConnection {
    
    
    
    public static Connection connect() {
        String url = "jdbc:mysql://localhost:3306/bankdb";
        String username = "root";
        String password = "140104";
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            return connection;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(BankingConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
