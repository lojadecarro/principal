package TablesDAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import Conexao.Conexao;
import Tables.CategoriaCarro;
import Tables.Modelo;
import Tables.Versao;

public class VersaoDAO {
    public Versao create(Versao versao) throws SQLException{
        String sql = """
        INSERT INTO versao (id_modelo, id_categoria_carro, nome, lancamento)
        VALUES (?, ?, ?, ?);
        """;

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        ) {
            statement.setInt(1, versao.getModelo().getId());
            statement.setInt(2, versao.getCategoriaCarro().getId());
            statement.setString(3, versao.getNome());  
            statement.setDate(4, Date.valueOf(versao.getLancamento())); 
            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();

            if (rs.next()) {
                versao.setId(rs.getInt(1));
            }

            rs.close();

            return versao;
        }
    }

    public Versao update(Versao versao) throws SQLException { 
        String sql = """
        UPDATE versao
        SET nome = ?
        WHERE id = ?;
        """;

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setString(1, versao.getNome());
            statement.setInt(2, versao.getId());
            int linhasAfetadas = statement.executeUpdate();

            if (linhasAfetadas > 0) {
                return versao;
            }
            return null;

        } catch (SQLException e) {
            return null;
        }
    }

    public Versao findByNome(String nome) { 
        String sql = "SELECT * FROM versao WHERE nome = ?;";

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setString(1, nome);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return resultSetToVersao(rs);
            }

            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return null;
    }

    public List<Versao> findVersoesByModelo(Modelo modelo) {
        List<Versao> versoes = new ArrayList<>();

        String sql = "SELECT * FROM versao WHERE id_modelo = ?;";

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setInt(1, modelo.getId());

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Versao versao = resultSetToVersao(rs);
                versoes.add(versao);
            }

            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return versoes;
    }

    private static Modelo findModeloDaVersao(int id) {
        String sql = "SELECT * FROM modelo WHERE id = ?;";

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return ModeloDAO.resultSetToModelo(rs);
            }

            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return null;
    }

    private static CategoriaCarro findCategoriaDaVersao(int id) {
        String sql = "SELECT * FROM categoria_carro WHERE id = ?;";

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return CategoriaCarroDAO.resultSetTocategoriaCarro(rs);
            }

            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return null;
    }

    protected static Versao resultSetToVersao(ResultSet rs) throws SQLException { 
        LocalDate lancamento = rs.getDate("lancamento").toLocalDate();
        return new Versao(
            rs.getInt("id"),
            rs.getString("nome"),
            lancamento,
            findModeloDaVersao(rs.getInt("id_modelo")),
            findCategoriaDaVersao(rs.getInt("id_categoria_carro"))
        );
    }
}
