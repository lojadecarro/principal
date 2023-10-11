package LojaDeCarros.Selects;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class ListarUnidadesPorAno {
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:mysql://localhost/estudante?user=estudante&password=estudante&useSSL=true";
        Connection conn = DriverManager.getConnection(url);

		Scanner sc = new Scanner(System.in);
		System.out.println("Digite o ano de fabricacao desejado para a busca por unidade que o tenham: ");
		int ano = sc.nextInt();

        String sql = "SELECT * FROM unidade WHERE ano = ?";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, ano);
        ResultSet rs = pstm.executeQuery(sql);

        while(rs.next()){
            int id = rs.getInt("id");
            int id_direcao = rs.getInt("id_direcao");
            int id_cor = rs.getInt("id_cor");
            int id_versao = rs.getInt("id_versao");
            ano = rs.getInt("ano");
            int placa = rs.getInt("placa");
            int quilometragem = rs.getInt("km");
            int valor_unitario = rs.getInt("valor_unitario");
            boolean disponibilidade = rs.getBoolean("disponibilidade");

            if (disponibilidade = true) {
                System.out.println(id);
                System.out.println(id_direcao);
                System.out.println(id_cor);
                System.out.println(id_versao);
                System.out.println(ano);
                System.out.println(placa);
                System.out.println(quilometragem);
                System.out.println(valor_unitario);
            }
        }
        pstm.close();
        conn.close();
        sc.close();
    }
}