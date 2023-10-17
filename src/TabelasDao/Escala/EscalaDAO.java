package TabelasDao.Escala; 

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import TabelasDao.Conexao;
import Tables.Escala; 

public class EscalaDAO { 

    public Escala create(Escala escala) throws SQLException { 
        String sql = """
        INSERT INTO escala (dias_trabalho, dias_folga, tipo)
        VALUES (?, ?, ?);
        """;
        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        ) {
            statement.setByte(1, escala.getDias_trabalho());
            statement.setByte(2, escala.getDias_folga());
            statement.setString(3, escala.getTipo());   
            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();

            if (rs.next()) {
                escala.setId(rs.getInt(1));
            }

            rs.close();

            return escala;
        }
    }

    public Escala update(Escala escala) throws SQLException { 
        String sql = """
        UPDATE escala
        SET dias_trabalho = ?, dias_folga = ?
        WHERE id = ?;
        """;

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setByte(1, escala.getDias_trabalho());
            statement.setByte(2, escala.getDias_folga());
            statement.setInt(3, escala.getId());
            int linhasAfetadas = statement.executeUpdate();

            if (linhasAfetadas > 0) {
                return escala;
            }
            return null;

        } catch (SQLException e) {
            return null;
        }
    }

    public void delete(Integer id) { 
        String sql = "DELETE FROM escala WHERE id = ?;";

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

    public void delete(Escala escala) { 
        delete(escala.getId());
    }

    public Escala findById(Integer id) { 
        String sql = "SELECT * FROM escala WHERE id = ?;";

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return resultSetToEscala(rs);
            }

            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return null;
    }

    private Escala resultSetToEscala(ResultSet rs) throws SQLException { 
        return new Escala(
            rs.getInt("id"),
            rs.getByte("dias_trabalho"),
            rs.getByte("dias_folga"),
            rs.getString("tipo")
        );
    }
}
