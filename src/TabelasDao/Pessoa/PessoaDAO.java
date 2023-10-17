package TabelasDao.Pessoa;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import TabelasDao.Conexao;
import Tables.Pessoa;

public class PessoaDAO {
    public Pessoa create(Pessoa pessoa) throws SQLException{
        String sql = """
                INSERT INTO pessoa (nome, email, contato, cpf, data_nascimento, endereco, complemento)
                VALUES (?, ?, ?, ?, ?, ?, ?);
                """;
        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection
            .prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        ) {
            statement.setString(1, pessoa.getNome());
            statement.setString(2, pessoa.getEmail());
            statement.setString(3, pessoa.getContato());
            statement.setString(4, pessoa.getCpf());
            statement.setDate(5, Date.valueOf(pessoa.getData_nascimento()));
            statement.setString(6, pessoa.getEndereco());
            statement.setString(7, pessoa.getComplemento());
            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();

            if(rs.next()) {
                pessoa.setId(rs.getInt(1));
            }

            rs.close();

            return pessoa;
        }
    }

    public Pessoa update(Pessoa pessoa) throws SQLException {
        String sql = """
            UPDATE pessoa SET nome = ?, email = ?, contato = ?, 
            cpf = ?, data_nascimento = ?, endereco = ?, complemento = ?, 
            WHERE id = ?;
            """;
        
        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection
            .prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        ){
            statement.setString(1, pessoa.getNome());
            statement.setString(2, pessoa.getEmail());
            statement.setString(3, pessoa.getContato());
            statement.setString(4, pessoa.getCpf());
            statement.setDate(5, Date.valueOf(pessoa.getData_nascimento()));
            statement.setString(6, pessoa.getEndereco());
            statement.setString(7, pessoa.getComplemento());
            statement.setInt(8, pessoa.getId());

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                return pessoa;
            }
            return null;
        
        } catch (SQLException e){
            return null;
        }
    }
 
    public void delete(Integer id) {
        String sql = "DELETE FROM pessoa WHERE id = ?;";

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

    public void delete(Pessoa pessoa) {
        delete(pessoa.getId());
    }

    public Pessoa findById(Integer id) {
        String sql = "SELECT * FROM pessoa WHERE id = ?;";

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return resultSetToPessoa(rs);
            }

            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return null;
    }

    private Pessoa resultSetToPessoa(ResultSet rs) throws SQLException {
        return new Pessoa(
            rs.getInt("id"),
            rs.getString("nome"),
            rs.getString("email"),
            rs.getString("contato"),
            rs.getString("cpf"),
            rs.getDate("data_nascimento").toLocalDate(),
            rs.getString("endereco")
        );
    }
}
