package LojaDeCarros.Selects;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class ListarFuncionarioPorSalario {
    public static void main(String[] args) throws SQLException{
		String url = "jdbc:mysql://localhost/estudante?user=estudante&password=estudante&useSSL=true";
		Connection conn = DriverManager.getConnection(url);

		Scanner sc = new Scanner(System.in);
		System.out.println("Digite o salário pelo qual desejas buscar: ");
		double salario_fixo = sc.nextDouble();

		String sql = "SELECT * FROM funcionario WHERE salario_fixo = ?";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setDouble(1, salario_fixo);
        ResultSet rs = pstm.executeQuery();

        while(rs.next()){
            int id = rs.getInt("id");
            int id_cargo = rs.getInt("id_cargo");
            String nome = rs.getString("nome");
            String email = rs.getString("email");
            String contato = rs.getString("contato");
            String cpf = rs.getString("cpf");
            String data_nascimento = rs.getString("data_nascimento");
            String endereco =  rs.getString("endereco");
            String complemento = rs.getString("complemento");
            salario_fixo = rs.getDouble("salario_fixo");
            int comissao = rs.getInt("comissao");
            
            System.out.println(id);
            System.out.println(id_cargo);
            System.out.println(nome);
            System.out.println(email);
            System.out.println(contato);
            System.out.println(cpf);
            System.out.println(data_nascimento);
            System.out.println(endereco);
            System.out.println(complemento);
            System.out.println(salario_fixo);
            System.out.println(comissao);
        }
        pstm.close();
        conn.close();
        sc.close();
    }
}
