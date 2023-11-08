package TablesDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Conexao.Conexao;
import Tables.Transmissao;

public class TransmissaoDAO { 

    public Transmissao create(Transmissao transmissao) throws SQLException { 
        String sql = """
        INSERT INTO transmissao (tipo)
        VALUES (?);
        """;
        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        ) {
            statement.setString(1, transmissao.getTipo());
            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();

            if (rs.next()) {
                transmissao.setId(rs.getInt(1));
            }

            rs.close();

            return transmissao;
        }
    }

    public Transmissao update(Transmissao transmissao) throws SQLException { 
        String sql = """
        UPDATE transmissao
        SET tipo = ?
        WHERE id = ?;
        """;

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {

            statement.setString(1, transmissao.getTipo());
            statement.setInt(2, transmissao.getId());
            int linhasAfetadas = statement.executeUpdate();

            if (linhasAfetadas > 0) {
                return transmissao;
            }
            return null;

        } catch (SQLException e) {
            return null;
        }
    }

    /*
    public void delete(Integer id) {
        String sql = "DELETE FROM Transmissao WHERE id = ?;";

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

    public void delete(Transmissao Transmissao) { 
        delete(Transmissao.getId());
    }
    */

    public Transmissao findByNome(String nome) { 
        String sql = "SELECT * FROM transmissao WHERE nome = ?;";

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setString(1, nome);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return resultSetToTransmissao(rs);
            }

            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return null;
    }

    protected static Transmissao resultSetToTransmissao(ResultSet rs) throws SQLException { 
        return new Transmissao(
            rs.getInt("id"),
            rs.getString("tipo")
        );
    }
}