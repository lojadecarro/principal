package TabelasDao.Modelo;

import Tables.Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import TabelasDao.Conexao;

public class ModeloDAO {
    public Modelo create(Modelo modelo) throws SQLException{
        String sql = """
        INSERT INTO modelo (id_marca, id_categoria_modelo, nome)
        VALUES (?, ?, ?);
        """;
        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        ) {
            statement.setInt(1, modelo.getIdMarca());
            statement.setInt(2, modelo.getIdCategoriaModelo());
            statement.setString(3, modelo.getNome());   
            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();

            if (rs.next()) {
                modelo.setId(rs.getInt(1));
            }

            rs.close();

            return modelo;
        }
    }

    public Modelo updateByIdMarca(Modelo modelo) throws SQLException { 
        String sql = """
        UPDATE modelo
        SET id_marca = ?
        WHERE id = ?;
        """;

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setInt(1, modelo.getIdMarca());
            statement.setInt(2, modelo.getId());
            int linhasAfetadas = statement.executeUpdate();

            if (linhasAfetadas > 0) {
                return modelo;
            }
            return null;

        } catch (SQLException e) {
            return null;
        }
    }

    public Modelo updateByIdCategoriaModelo(Modelo modelo) throws SQLException { 
        String sql = """
        UPDATE modelo
        SET id_categoria_modelo = ?
        WHERE id = ?;
        """;

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setInt(1, modelo.getIdCategoriaModelo());
            statement.setInt(2, modelo.getId());
            int linhasAfetadas = statement.executeUpdate();

            if (linhasAfetadas > 0) {
                return modelo;
            }
            return null;

        } catch (SQLException e) {
            return null;
        }
    }


    public void delete(Integer id) { 
        String sql = "DELETE FROM modelo WHERE id = ?;";

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

    public void delete(Modelo modelo) { 
        delete(modelo.getId());
    }

    public Modelo findById(Integer id) { 
        String sql = "SELECT * FROM modelo WHERE id = ?;";

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return resultSetToModelo(rs);
            }

            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return null;
    }

    private Modelo resultSetToModelo(ResultSet rs) throws SQLException { 
        return new Modelo(
            rs.getInt("id"),
            rs.getInt("id_marca"),
            rs.getInt("id_categoria_modelo"),
            rs.getString("nome")
        );
    }
}
