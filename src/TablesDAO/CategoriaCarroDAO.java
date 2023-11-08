package TablesDAO;

import Tables.CategoriaCarro;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Conexao.Conexao;

public class CategoriaCarroDAO {
    public CategoriaCarro create(CategoriaCarro categoriaCarro) throws SQLException {
        String sql = """
        INSERT INTO categoria_carro (nome)
        VALUES (?);
        """;
        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection
            .prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        ) {
            statement.setString(1, categoriaCarro.getNome());
            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();

            if(rs.next()) {
                categoriaCarro.setId(rs.getInt(1));
            }

            rs.close();

            return categoriaCarro;
        }
    }

    public CategoriaCarro update(CategoriaCarro categoriaCarro) throws SQLException {
        String sql = """
        UPDATE categoria_carro
        SET nome = ?
        WHERE id = ?;
        """;

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {

            statement.setString(1, categoriaCarro.getNome());
            statement.setInt(2, categoriaCarro.getId());
            int linhasAfetadas = statement.executeUpdate();

            if (linhasAfetadas > 0) {
                return categoriaCarro;
            }
            return null;

            } catch (SQLException e) {
                return null;
            }
    }

    /* 
    public void delete(Integer id) {
        String sql = "DELETE FROM categoria_carro WHERE id = ?;";

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

    public void delete(CategoriaCarro categoriaCarro) {
        delete(categoriaCarro.getId());
    }
    */

    public CategoriaCarro findByNome(String nome) {
        String sql = "SELECT * FROM categoria_modelo WHERE nome = ?;";

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setString(1, nome);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return resultSetTocategoriaCarro(rs);
            }

            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return null;
    }

    protected static CategoriaCarro resultSetTocategoriaCarro(ResultSet rs) throws SQLException {
        return new CategoriaCarro(
            rs.getInt("id"),
            rs.getString("nome")
        );
    }
}
