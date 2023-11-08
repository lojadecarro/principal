package TablesDAO;

import Conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Tables.Advertencia;
import Tables.Funcionario;

public class AdvertenciaDAO {
    public Advertencia create(Advertencia advertencia) throws SQLException {
        String sql = """
        INSERT INTO advertencia (id_funcionario, gravidade, motivo)
        VALUES (?, ?, ?);
        """;
        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection
            .prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        ) {
            statement.setInt(1, advertencia.getFuncionario().getId());
            statement.setByte(2, advertencia.getGravidade());
            statement.setString(3, advertencia.getMotivo());
            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();

            if(rs.next()) {
                advertencia.setId(rs.getInt(1));
            }

            rs.close();

            return advertencia;
        }
    }

/*
    public Advertencia update(Advertencia advertencia) throws SQLException {
        String sql = """
        UPDATE advertencia
        SET motivo = ?
        WHERE id = ?;
        """;

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {

            statement.setString(1, advertencia.getMotivo());
            statement.setInt(2, advertencia.getId());
            int linhasAfetadas = statement.executeUpdate();

            if (linhasAfetadas > 0) {
                return advertencia;
            }
            return null;

            } catch (SQLException e) {
                return null;
            }
    }
    */

    public void delete(Integer id) {
        String sql = "DELETE FROM advertencia WHERE id = ?;";

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
    public void delete(Advertencia advertencia) {
        delete(advertencia.getId());
    }
    */

    public Advertencia findById(Integer id) {
        String sql = "SELECT * FROM advertencia WHERE id = ?;";

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return resultSetToAdvertencia(rs);
            }

            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return null;
    }

    private Funcionario findFuncionarioAdvertido(int id){
        String sql = "SELECT * FROM funcionario WHERE id = ?;";

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return FuncionarioDAO.resultSetToFuncionario(rs);
            }

            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return null;
    }

    private Advertencia resultSetToAdvertencia(ResultSet rs) throws SQLException {
        return new Advertencia(
            rs.getInt("id"),
            rs.getByte("gravidade"),
            findFuncionarioAdvertido(rs.getInt("id_funcionario")),
            rs.getString("motivo")
        );
    }
}
