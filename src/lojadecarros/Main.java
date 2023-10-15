package lojadecarros;

import java.sql.SQLException;

import org.mariadb.jdbc.Connection;

import lojadecarros.TabelasDao.Conexao;

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
