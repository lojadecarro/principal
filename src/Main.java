import java.sql.Connection;
import java.sql.SQLException;

import Conexao.Conexao;

//import org.mariadb.jdbc.Connection;

public class Main {
    public static void main(String[] args) throws SQLException {
        Connection connection = Conexao.getConnection();
        if (connection != null) {
            System.out.println("Connection successful!");
        } else {
            System.out.println("Connection failed.");
        }
    }
}
