package TabelasDao.Marca;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import TabelasDao.Conexao;
import Tables.Marca;

// DAO = Data Access Object
public class MarcaDAO {
    public Marca create(Marca marca) throws SQLException{
        String sql = """
        INSERT INTO marca (nome)
        VALUES (?);
        """;
        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection
            .prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        ) {
            statement.setString(1, marca.getNome());
            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();

            if(rs.next()) {
                marca.setId(rs.getInt(1));
            }

            rs.close();

            return marca;
        }
    }

    public Marca update(Marca marca) throws SQLException{
        String sql = """
        UPDATE marca
        SET nome = ?
        WHERE id = ?;
        """;

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {

            statement.setString(1, marca.getNome());
            statement.setInt(2, marca.getId());
            int linhasAfetadas = statement.executeUpdate();

            if (linhasAfetadas > 0) {
                return marca;
            }
            return null;

            } catch (SQLException e) {
                return null;
            }
    }

    public void delete(Integer id) {
        String sql = "DELETE FROM marca WHERE id = ?;";

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

    public void delete(Marca marca) {
        delete(marca.getId());
    }

    public Marca findById(Integer id) {
        String sql = "SELECT * FROM marca WHERE id = ?;";

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return resultSetToMarca(rs);
            }

            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return null;
    }

    private Marca resultSetToMarca(ResultSet rs) throws SQLException {
        return new Marca(
            rs.getInt("id"),
            rs.getString("nome")
        );
    }
}

    