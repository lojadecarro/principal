package TabelasDao.Direcao; 

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import TabelasDao.Conexao;
import Tables.Direcao;

public class DirecaoDAO { 

    public Direcao create(Direcao direcao) throws SQLException { 
        String sql = """
        INSERT INTO direcao (tipo)
        VALUES (?);
        """;
        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        ) {
            statement.setString(1, direcao.getTipo());
            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();

            if (rs.next()) {
                direcao.setId(rs.getInt(1));
            }

            rs.close();

            return direcao;
        }
    }

    public Direcao update(Direcao direcao) throws SQLException { 
        String sql = """
        UPDATE direcao
        SET tipo = ?
        WHERE id = ?;
        """;

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {

            statement.setString(1, direcao.getTipo());
            statement.setInt(2, direcao.getId());
            int linhasAfetadas = statement.executeUpdate();

            if (linhasAfetadas > 0) {
                return direcao;
            }
            return null;

        } catch (SQLException e) {
            return null;
        }
    }

    public void delete(Integer id) {
        String sql = "DELETE FROM direcao WHERE id = ?;";

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

    public void delete(Direcao direcao) { 
        delete(direcao.getId());
    }

    public Direcao findById(Integer id) { 
        String sql = "SELECT * FROM direcao WHERE id = ?;";

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return resultSetToDirecao(rs);
            }

            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return null;
    }

    private Direcao resultSetToDirecao(ResultSet rs) throws SQLException { 
        return new Direcao(
            rs.getInt("id"),
            rs.getString("tipo")
        );
    }
}
