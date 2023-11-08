package TablesDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Conexao.Conexao;
import Tables.Endereco;

public class EnderecoDAO {
    public Endereco create(Endereco endereco) throws SQLException {
        String sql = """
        INSERT INTO endereco (logradouro, numero, complemento, cep) 
        VALUES (?, ?, ?, ?);"
        """;

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection
            .prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        ) {
            statement.setString(1, endereco.getLogradouro());
            statement.setShort(2, endereco.getNumero());
            statement.setString(3, endereco.getComplemento());
            statement.setString(4, endereco.getCep());
            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();

            if(rs.next()) {
                endereco.setId(rs.getInt(1));
            }

            rs.close();

            return endereco;
        }
    }

    public Endereco update(Endereco endereco) throws SQLException {
        String sql = """
        UPDATE endereco
        SET logradouro = ?, numero = ?, complemento = ?, cep = ?
        WHERE id = ?;
        """;

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {

            statement.setString(1, endereco.getLogradouro());
            statement.setShort(2, endereco.getNumero());
            statement.setString(3, endereco.getComplemento());
            statement.setString(4, endereco.getCep());
            int linhasAfetadas = statement.executeUpdate();

            if (linhasAfetadas > 0) {
                return endereco;
            }
            return null;

        } catch (SQLException e) {
            return null;
        }
    }

    public void delete(Integer id) {
        String sql = "DELETE FROM endereco WHERE id = ?;";

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

    public Endereco findById(Integer id) {
        String sql = "SELECT * FROM endereco WHERE id = ?;";

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return resultSetToEndereco(rs);
            }

            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return null;
    }

    protected static Endereco resultSetToEndereco(ResultSet rs) throws SQLException {
        return new Endereco(
            rs.getInt("id"),
            rs.getString("logradouro"),
            rs.getShort("numero"),
            rs.getString("complemento"),
            rs.getString("cep")
        );
    }
}
