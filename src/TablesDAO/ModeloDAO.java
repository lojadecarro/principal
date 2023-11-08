package TablesDAO;

import Tables.Marca;
import Tables.Modelo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Conexao.Conexao;

public class ModeloDAO {
    public Modelo create(Modelo modelo) throws SQLException{
        String sql = """
        INSERT INTO modelo (id_marca, nome)
        VALUES (?, ?);
        """;
        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        ) {
            statement.setInt(1, modelo.getMarca().getId());
            statement.setString(2, modelo.getNome());   
            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();

            if (rs.next()) {
                modelo.setId(rs.getInt(1));
            }

            rs.close();

            return modelo;
        }
    }

    public Modelo update(Modelo modelo) throws SQLException { 
        String sql = """
        UPDATE modelo
        SET nome = ?
        WHERE id = ?;
        """;

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setString(1, modelo.getNome());
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

    /* 
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
    */

    public Modelo findByNome(String nome) { 
        String sql = "SELECT * FROM modelo WHERE nome = ?;";

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setString(1, nome);

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

    private static Marca findMarcaDoModelo(int id) {
        String sql = "SELECT * FROM marca WHERE id = ?;";

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return MarcaDAO.resultSetToMarca(rs);
            }

            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return null;
    }




    public List<Modelo> findModelosByMarca(Marca marca) {
        List<Modelo> modelos = new ArrayList<>();

        String sql = "SELECT * FROM modelo WHERE id_marca = ?;";

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setInt(1, marca.getId());

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Modelo modelo = resultSetToModelo(rs);
                modelos.add(modelo);
            }

            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return modelos;
    }
    
    protected static Modelo resultSetToModelo(ResultSet rs) throws SQLException { 
        return new Modelo(
            rs.getInt("id"),
            rs.getString("nome"),
            findMarcaDoModelo(rs.getInt(2))
        );
    }
    
}