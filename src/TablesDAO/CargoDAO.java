package TablesDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Conexao.Conexao;
import Tables.Cargo;

// DAO = Data Access Object
public class CargoDAO {
    public Cargo create(Cargo cargo) throws SQLException {
        String sql = """
        INSERT INTO cargo (nome)
        VALUES (?);
        """;
        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection
            .prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        ) {
            statement.setString(1, cargo.getNome());
            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();

            if(rs.next()) {
                cargo.setId(rs.getInt(1));
            }

            rs.close();

            return cargo;
        }
    }

    public Cargo update(Cargo cargo) throws SQLException {
        String sql = """
        UPDATE cargo
        SET nome = ?
        WHERE id = ?;
        """;

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {

            statement.setString(1, cargo.getNome());
            statement.setInt(2, cargo.getId());
            int linhasAfetadas = statement.executeUpdate();

            if (linhasAfetadas > 0) {
                return cargo;
            }
            return null;

            } catch (SQLException e) {
                return null;
            }
    }

    public void delete(Integer id) {
        String sql = "DELETE FROM cargo WHERE id = ?;";

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

    /* 
    public void delete(Cargo cargo) {
        delete(cargo.getId());
    }
    */

    public Cargo findByNome(String nome) {
        String sql = "SELECT * FROM cargo WHERE nome = ?;";

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setString(1, nome);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return resultSetToCargo(rs);
            }

            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return null;
    }

    protected static Cargo resultSetToCargo(ResultSet rs) throws SQLException {
        return new Cargo(
            rs.getInt("id"),
            rs.getString("nome")
        );
    }
}
