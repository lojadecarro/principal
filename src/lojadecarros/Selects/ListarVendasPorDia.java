package LojaDeCarros.Selects;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.sql.PreparedStatement;

public class ListarVendasPorDia {
    public static void main(String[] args) throws SQLException{
        String url = "jdbc:mysql://localhost/estudante?user=estudante&password=estudante&useSSL=true";
        Connection conn = DriverManager.getConnection(url);

        Scanner sc = new Scanner(System.in);
		System.out.println("Digite o dia que deseja para listar todas as vendas que ocorreram nele (aaaa-mm-dd): ");
		String dia = sc.nextLine();

        String sql = "SELECT * FROM venda WHERE DATE(dia_horario) = ?";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, dia);
        ResultSet rs = pstm.executeQuery(sql);

        while(rs.next()){
            int id = rs.getInt("id");
            int idFuncionario = rs.getInt("id_funcionario");
            int idCliente = rs.getInt("id_cliente");
            int idFormaDePagamento = rs.getInt("id_forma_pagamento");
            double desconto = rs.getDouble("desconto");
            int parcelas = rs.getInt("parcelas");
            int juros = rs.getInt("juros");
            
            System.out.println(id);
            System.out.println(idFuncionario);
            System.out.println(idCliente);
            System.out.println(idFormaDePagamento);
            System.out.println(desconto);
            System.out.println(parcelas);
            System.out.println(juros);
        }
        pstm.close();
        conn.close();
        sc.close();
    }
}