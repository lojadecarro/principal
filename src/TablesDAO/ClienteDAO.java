package TablesDAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Conexao.Conexao;
import Tables.Cliente;
import Tables.Endereco;

public class ClienteDAO {
    public Cliente create(Cliente cliente) throws SQLException{
        String sql = """
                INSERT INTO cliente (id_endereco, nome, email, contato, cpf, data_nascimento, data_registro)
                VALUES (?, ?, ?, ?, ?, ?, ?);
                """;
        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection
            .prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        ) {
            statement.setInt(1, cliente.getEndereco().getId());
            statement.setString(2, cliente.getNome());
            statement.setString(3, cliente.getEmail());
            statement.setString(4, cliente.getContato());
            statement.setString(5, cliente.getCpf());
            statement.setDate(6, Date.valueOf(cliente.getData_nascimento()));
            statement.setDate(7, Date.valueOf(cliente.getData_registro()));
            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();

            if(rs.next()) {
                cliente.setId(rs.getInt(1));
            }

            rs.close();

            return cliente;
        }
    }

    public Cliente update(Cliente cliente) throws SQLException {
        String sql = """
            UPDATE cliente SET id_endereco= ?, nome = ?, email = ?, contato = ?
            WHERE id = ?;
            """;
        
        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection
            .prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        ){
            statement.setInt(1, cliente.getEndereco().getId());
            statement.setString(2, cliente.getNome());
            statement.setString(3, cliente.getEmail());
            statement.setString(4, cliente.getContato());

            int linhasAfetadas = statement.executeUpdate();

            if (linhasAfetadas > 0) {
                return cliente;
            }
            return null;
        
        } catch (SQLException e){
            return null;
        }
    }
 
    public void delete(Integer id) {
        String sql = "DELETE FROM cliente WHERE id = ?;";

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
    public void delete(Cliente cliente) {
        delete(cliente.getId());
    }
    

    public Cliente findById(Integer id) {
        String sql = "SELECT * FROM Cliente WHERE id = ?;";

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return resultSetToCliente(rs);
            }

            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        class ClienteDAO
        return null;
    }
    */

    public Cliente findByEmail(String email) {
        String sql = "SELECT * FROM cliente WHERE email = ?;";

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setString(1, email);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return resultSetToCliente(rs);
            }

            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return null;
    }

    public Cliente findByCpf(String cpf) {
        String sql = "SELECT * FROM cliente WHERE cpf = ?;";

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            String cpffmtd = cpf.substring(0, 3) + "." + cpf.substring(3, 6) + "." + cpf.substring(6, 9) + "-" + cpf.substring(9);
            statement.setString(1, cpffmtd);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return resultSetToCliente(rs);
            }

            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return null;
    }

    public Cliente findByContato(String contato) {
        String sql = "SELECT * FROM cliente WHERE contato = ?;";

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            String contatofmtd = "(" + contato.substring(0, 2) + ")" + " " + contato.substring(2, 7) + "-" + contato.substring(7);
            statement.setString(1, contatofmtd);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return resultSetToCliente(rs);
            }

            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return null;
    }

    private static Endereco findEnderecoDoCliente(int id){
        String sql = "SELECT * FROM endereco WHERE id = ?;";

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return EnderecoDAO.resultSetToEndereco(rs);
            }

            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return null;
    }

    protected static Cliente resultSetToCliente(ResultSet rs) throws SQLException {
         Cliente cliente = new Cliente(
            rs.getInt("id"),
            rs.getString("nome"),
            rs.getString("email"),
            rs.getString("contato"),
            rs.getString("cpf"),
            (rs.getDate("data_nacsimento")).toLocalDate(), 
            findEnderecoDoCliente(rs.getInt("id_endereco"))
        );
        
        cliente.data_registroParaResultSet(rs.getDate("data_registro").toLocalDate());
        return cliente;
    }
}
