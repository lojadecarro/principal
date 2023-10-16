package lojadecarros.TabelasDao.Cor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import lojadecarros.TabelasDao.Conexao;
import lojadecarros.Tables.Cor;


public class CorDAO {
    public Cor create(Cor cor) throws SQLException {
        String sql = """
        INSERT INTO cor (id_categoria_cor, nome)
        VALUES (?);
        """;
        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection
            .prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        ) {
            statement.setInt(1, cor.getIdCategoriaCor());
            statement.setString(2, cor.getNome());
            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();

            if(rs.next()) {
                cor.setId(rs.getInt(1));
            }

            rs.close();

            return cor;
        }
    }

    public Cor update(Cor cor) throws SQLException {
        String sql = """
        UPDATE cor
        SET nome = ?
        WHERE id = ?;
        """;

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {

            statement.setString(1, cor.getNome());
            statement.setInt(2, cor.getId());
            int linhasAfetadas = statement.executeUpdate();

            if (linhasAfetadas > 0) {
                return cor;
            }
            return null;

            } catch (SQLException e) {
                return null;
            }
    }

    public void delete(Integer id) {
        String sql = "DELETE FROM cor WHERE id = ?;";

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

    public void delete(Cor cor) {
        delete(cor.getId());
    }

    public Cor findById(Integer id) {
        String sql = "SELECT * FROM cor WHERE id = ?;";

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return resultSetToCor(rs);
            }

            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return null;
    }

    private Cor resultSetToCor(ResultSet rs) throws SQLException {
        return new Cor(
            rs.getInt("id"),
            rs.getInt("id_categoria_cor"),
            rs.getString("nome")
        );
    }
    
}
