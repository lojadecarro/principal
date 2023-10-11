package LojaDeCarros.Inserts;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class InserirFuncionario{
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:mysql://localhost/estudante?user=estudante&password=estudante&useSSL=true";
        Connection conn = DriverManager.getConnection(url);

        Scanner sc = new Scanner(System.in);

        System.out.println("Digite o ID do funcionário que deseja inserir: ");
        int id = sc.nextInt();

        System.out.println("Digite o ID do cargo: ");
        int id_cargo = sc.nextInt();

        sc.nextLine();

        System.out.println("Digite o nome do funcionario: ");
        String nome = sc.nextLine();

        System.out.println("Digite seu email: ");
        String email = sc.nextLine();

        System.out.println("Digite seu numero de contato: ");
        int contato = sc.nextInt();

        System.out.println("Digite seu CPF: ");
        int cpf = sc.nextInt();

        sc.nextLine();

        System.out.println("Digite a data de nascimento do funcionario (aaaa-mm-dd): ");
        String data_nascimento = sc.nextLine();

        System.out.println("Digite o endereço: ");
        String endereco = sc.nextLine();

        System.out.println("Se necessario, digite o complemento do endereco: ");
        String complemento = sc.nextLine();

        System.out.println("Digite o salário fixo: ");
        double salario_fixo = sc.nextDouble();

        System.out.println("Se necessario, digite a comissão (%): ");
        int comissao = sc.nextInt();

        String sql = "INSERT INTO funcionario VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, id);
        pstm.setInt(2, id_cargo);
        pstm.setString(3, nome);
        pstm.setString(4, email);
        pstm.setInt(5, contato);
        pstm.setInt(6, cpf);
        pstm.setString(7, data_nascimento);
        pstm.setString(8, endereco);
        pstm.setString(9, complemento);
        pstm.setDouble(10, salario_fixo);
        pstm.setInt(11, comissao);
        pstm.executeUpdate();

        pstm.close();
        conn.close();
        sc.close();
    }
}