package Conexao;

//import org.mariadb.jdbc.Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    public static Connection getConnection() {
        //String url = "jdbc:mysql://localhost/estudante1?user=estudante1&password=estudante1&useSSL=true";
     
        //Class.forName("org.mariadb.jdbc.Driver");
        String url = "jdbc:mysql://localhost/projetobdd?user=root&password=1234&useSSL=true";
        //String url = "jdbc:mariadb://localhost:3306/javaalunos?user=troarmen&password=0000";
        
        try {
            return (Connection) DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}