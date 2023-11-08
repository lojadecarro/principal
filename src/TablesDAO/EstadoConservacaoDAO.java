package TablesDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Conexao.Conexao;
import Tables.EstadoConservacao;

public class EstadoConservacaoDAO {
    public EstadoConservacao create(EstadoConservacao estadoConservacao) throws SQLException {
        String sql = """
        INSERT INTO estado_conservacao (nome)
        VALUES (?);
        """;
        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection
            .prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        ) {
            statement.setString(1, estadoConservacao.getNome());
            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();

            if(rs.next()) {
                estadoConservacao.setId(rs.getInt(1));
            }

            rs.close();

            return estadoConservacao;
        }
    }

    public EstadoConservacao update(EstadoConservacao estadoConservacao) throws SQLException {
        String sql = """
        UPDATE estado_conservacao
        SET nome = ?
        WHERE id = ?;
        """;

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {

            statement.setString(1, estadoConservacao.getNome());
            statement.setInt(2, estadoConservacao.getId());
            int linhasAfetadas = statement.executeUpdate();

            if (linhasAfetadas > 0) {
                return estadoConservacao;
            }
            return null;

            } catch (SQLException e) {
                return null;
            }
    }

    public EstadoConservacao findByNome(String nome) {
        String sql = "SELECT * FROM estado_conservacao WHERE nome = ?;";

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setString(1, nome);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return resultSetToEstadoConservacao(rs);
            }

            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return null;
    }

    protected static EstadoConservacao resultSetToEstadoConservacao(ResultSet rs) throws SQLException {
        return new EstadoConservacao(
            rs.getInt("id"),
            rs.getString("nome")
        );
    }
}
