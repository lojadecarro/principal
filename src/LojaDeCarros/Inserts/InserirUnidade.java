package LojaDeCarros.Inserts;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class InserirUnidade {
    public static void main(String[] args) throws SQLException{
        String url = "jdbc:mysql://localhost/estudante?user=estudante&password=estudante&useSSL=true";
        Connection conn = DriverManager.getConnection(url);

        Scanner sc = new Scanner(System.in);

        System.out.println("Digite o ID da unidade a ser inserida: ");
        int id = sc.nextInt();

        System.out.println("Digite o ID do tipo de direção que a unidade possui: ");
        int id_direcao = sc.nextInt();

        System.out.println("Digite o ID da cor da unidade: ");
        int id_cor = sc.nextInt();

        System.out.println("Digite o ID da versão da unidade: ");
        int id_versao = sc.nextInt();

        System.out.println("Digite o ano de fabricacao da unidade ");
        int ano = sc.nextInt();

        sc.nextLine();

        System.out.println("Digite a placa do carro: ");
        String placa = sc.nextLine();

        System.out.println("Digite a quilometragem que o carro possui: ");
        int quilometragem = sc.nextInt();

        System.out.println("Digite o valor unitário do carro: ");
        double valor_unitario = sc.nextInt();

        boolean disponibilidade = true;

        String sql = "INSERT INTO unidade VALUES(?, ?, ?, ?, ?, ?, ?, ?, )";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, id);
        pstm.setInt(2, id_direcao);
        pstm.setInt(3, id_cor);
        pstm.setInt(4, id_versao);
        pstm.setInt(5, ano);
        pstm.setString(6, placa);
        pstm.setInt(7, quilometragem);
        pstm.setDouble(8, valor_unitario);
        pstm.setBoolean(9, disponibilidade);
        pstm.executeUpdate();

        pstm.close();
        conn.close();
        sc.close();
    }
}