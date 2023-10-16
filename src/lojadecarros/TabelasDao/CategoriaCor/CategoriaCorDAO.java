package lojadecarros.TabelasDao.CategoriaCor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import lojadecarros.TabelasDao.Conexao;
import lojadecarros.Tables.CategoriaCor;

public class CategoriaCorDAO {
    public CategoriaCor create(CategoriaCor categoriaCor) throws SQLException {
        String sql = """
        INSERT INTO categoria_cor (nome)
        VALUES (?);
        """;
        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection
            .prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        ) {
            statement.setString(1, categoriaCor.getNome());
            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();

            if(rs.next()) {
                categoriaCor.setId(rs.getInt(1));
            }

            rs.close();

            return categoriaCor;
        }
    }

    public CategoriaCor update(CategoriaCor categoriaCor) throws SQLException {
        String sql = """
        UPDATE categoria_cor
        SET nome = ?
        WHERE id = ?;
        """;

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {

            statement.setString(1, categoriaCor.getNome());
            statement.setInt(2, categoriaCor.getId());
            int linhasAfetadas = statement.executeUpdate();

            if (linhasAfetadas > 0) {
                return categoriaCor;
            }
            return null;

            } catch (SQLException e) {
                return null;
            }
    }

    public void delete(Integer id) {
        String sql = "DELETE FROM categoria_cor WHERE id = ?;";

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

    public void delete(CategoriaCor categoriaCor) {
        delete(categoriaCor.getId());
    }

    public CategoriaCor findById(Integer id) {
        String sql = "SELECT * FROM categoria_cor WHERE id = ?;";

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return resultSetToCategoriaCor(rs);
            }

            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return null;
    }

    private CategoriaCor resultSetToCategoriaCor(ResultSet rs) throws SQLException {
        return new CategoriaCor(
            rs.getInt("id"),
            rs.getString("nome")
        );
    }
}
