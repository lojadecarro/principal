package TabelasDao.CategoriaModelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import TabelasDao.Conexao;
import Tables.CategoriaModelo;

public class CategoriaModeloDAO {
    public CategoriaModelo create(CategoriaModelo categoriaModelo) throws SQLException {
        String sql = """
        INSERT INTO categoria_modelo (nome)
        VALUES (?);
        """;
        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection
            .prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        ) {
            statement.setString(1, categoriaModelo.getNome());
            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();

            if(rs.next()) {
                categoriaModelo.setId(rs.getInt(1));
            }

            rs.close();

            return categoriaModelo;
        }
    }

    public CategoriaModelo update(CategoriaModelo categoriaModelo) throws SQLException {
        String sql = """
        UPDATE categoria_modelo
        SET nome = ?
        WHERE id = ?;
        """;

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {

            statement.setString(1, categoriaModelo.getNome());
            statement.setInt(2, categoriaModelo.getId());
            int linhasAfetadas = statement.executeUpdate();

            if (linhasAfetadas > 0) {
                return categoriaModelo;
            }
            return null;

            } catch (SQLException e) {
                return null;
            }
    }

    public void delete(Integer id) {
        String sql = "DELETE FROM categoria_modelo WHERE id = ?;";

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

    public void delete(CategoriaModelo categoriaModelo) {
        delete(categoriaModelo.getId());
    }

    public CategoriaModelo findById(Integer id) {
        String sql = "SELECT * FROM categoria_modelo WHERE id = ?;";

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return resultSetToCategoriaModelo(rs);
            }

            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return null;
    }

    private CategoriaModelo resultSetToCategoriaModelo(ResultSet rs) throws SQLException {
        return new CategoriaModelo(
            rs.getInt("id"),
            rs.getString("nome")
        );
    }
}
