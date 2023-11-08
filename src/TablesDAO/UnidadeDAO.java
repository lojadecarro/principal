package TablesDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Conexao.Conexao;
import Tables.Cor;
import Tables.EstadoConservacao;
import Tables.Transmissao;
import Tables.Unidade;
import Tables.Versao;

public class UnidadeDAO {
    public Unidade create(Unidade unidade) throws SQLException {
        String sql = """
        INSERT INTO unidade (id_transmissao, id_cor, id_versao, id_estado_conservacao, ano, placa, quilometragem, valor_unitario, disponibilidade)
        VALUES (?, ?, ?, ?, ?, ?, ?, ?);
        """;

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        ) {
            statement.setInt(1, unidade.getTransmissao().getId());
            statement.setInt(2, unidade.getCor().getId());
            statement.setInt(3, unidade.getVersao().getId());
            statement.setInt(4, unidade.getEstado_conservacao().getId());
            statement.setShort(5, unidade.getAno());
            statement.setString(6, unidade.getPlaca());  
            statement.setInt(7, unidade.getQuilometragem());
            statement.setDouble(8, unidade.getValor_unitario()); 
            statement.setBoolean(9, false);
            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();

            if (rs.next()) {
                unidade.setId(rs.getInt(1));
            }

            rs.close();

            return unidade;
        }
    }

    public Unidade updateTransmissao(Unidade unidade) throws SQLException { 
        String sql = """
        UPDATE unidade
        SET id_transmissao = ?
        WHERE id = ?;
        """;

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setInt(1, unidade.getTransmissao().getId());
            statement.setInt(2, unidade.getId());
            int linhasAfetadas = statement.executeUpdate();

            if (linhasAfetadas > 0) {
                return unidade;
            }
            return null;

        } catch (SQLException e) {
            return null;
        }
    }

    public Unidade updateCor(Unidade unidade) throws SQLException { 
        String sql = """
        UPDATE unidade
        SET id_cor = ?
        WHERE id = ?;
        """;

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setInt(1, unidade.getCor().getId());
            statement.setInt(2, unidade.getId());
            int linhasAfetadas = statement.executeUpdate();

            if (linhasAfetadas > 0) {
                return unidade;
            }
            return null;

        } catch (SQLException e) {
            return null;
        }
    }

    public Unidade updateEstadoConservacao(Unidade unidade) throws SQLException { 
        String sql = """
        UPDATE unidade
        SET id_estado_conservacao = ?
        WHERE id = ?;
        """;

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setInt(1, unidade.getEstado_conservacao().getId());
            statement.setInt(2, unidade.getId());
            int linhasAfetadas = statement.executeUpdate();

            if (linhasAfetadas > 0) {
                return unidade;
            }
            return null;

        } catch (SQLException e) {
            return null;
        }
    }

    public Unidade updatePlaca(Unidade unidade) throws SQLException { 
        String sql = """
        UPDATE unidade
        SET placa = ?
        WHERE id = ?;
        """;

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setString(1, unidade.getPlaca());
            statement.setInt(2, unidade.getId());
            int linhasAfetadas = statement.executeUpdate();

            if (linhasAfetadas > 0) {
                return unidade;
            }
            return null;

        } catch (SQLException e) {
            return null;
        }
    }

    public Unidade updateQuilometragem(Unidade unidade) throws SQLException { 
        String sql = """
        UPDATE unidade
        SET quilometragem = ?
        WHERE id = ?;
        """;

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setInt(1, unidade.getQuilometragem());
            statement.setInt(2, unidade.getId());
            int linhasAfetadas = statement.executeUpdate();

            if (linhasAfetadas > 0) {
                return unidade;
            }
            return null;

        } catch (SQLException e) {
            return null;
        }
    }

    public Unidade updateValorUnitario(Unidade unidade) throws SQLException { 
        String sql = """
        UPDATE unidade
        SET valor_unitario = ?
        WHERE id = ?;
        """;

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setDouble(1, unidade.getValor_unitario());
            statement.setInt(2, unidade.getId());
            int linhasAfetadas = statement.executeUpdate();

            if (linhasAfetadas > 0) {
                return unidade;
            }
            return null;

        } catch (SQLException e) {
            return null;
        }
    }

    public Unidade updateDisponibilidade(Unidade unidade) throws SQLException { 
        String sql = """
        UPDATE unidade
        SET disponibilidade = ?
        WHERE id = ?;
        """;

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setBoolean(1, !unidade.getDisponibilidade());
            statement.setInt(2, unidade.getId());
            int linhasAfetadas = statement.executeUpdate();

            if (linhasAfetadas > 0) {
                return unidade;
            }
            return null;

        } catch (SQLException e) {
            return null;
        }
    }

    public void delete(Integer id) { 
        String sql = "DELETE FROM unidade WHERE id = ?;";

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

    public List<Unidade> findUnidadesByEstadoConservacao(EstadoConservacao estadoConservacao) { 
        List<Unidade> unidades = new ArrayList<>();
        String sql = "SELECT * FROM unidades_para_venda WHERE id_estado_conservacao = ?;";

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setInt(1, estadoConservacao.getId());
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                Unidade unidade = resultSetToUnidade(rs);
                unidades.add(unidade);
            }

            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return unidades;
    }

    public List<Unidade> findUnidadesByVersao(Versao versao) { 
        List<Unidade> unidades = new ArrayList<>();
        String sql = "SELECT * FROM unidades_para_venda WHERE versao = ?;";

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setString(1, versao.getNome());
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                Unidade unidade = resultSetToUnidade(rs);
                unidades.add(unidade);
            }

            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return unidades;
    }

    public List<Unidade> findUnidadesByValorUnitario(Double valorMaximo) { 
        List<Unidade> unidades = new ArrayList<>();
        String sql = "SELECT * FROM unidades_para_venda WHERE valor_unitario <= ?;";

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setDouble(1, valorMaximo);
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                Unidade unidade = resultSetToUnidade(rs);
                unidades.add(unidade);
            }

            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return unidades;
    }

    public List<Unidade> findUnidadesDisponiveis() { 
        List<Unidade> unidades = new ArrayList<>();
        String sql = "SELECT * FROM unidades_para_venda;";

        try (
            Connection connection = Conexao.getConnection();
            Statement statement = connection.createStatement();
        ) {
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                Unidade unidade = resultSetToUnidade(rs);
                unidades.add(unidade);
            }

            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return unidades;
    }

    private static EstadoConservacao findEstadoConservacaoDaUnidade(int id) {
        String sql = "SELECT * FROM estado_conservacao WHERE id = ?;";

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return EstadoConservacaoDAO.resultSetToEstadoConservacao(rs);
            }

            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return null;
    }

    private static Versao findVersaoDaUnidade(int id) {
        String sql = "SELECT * FROM versao WHERE id = ?;";

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return VersaoDAO.resultSetToVersao(rs);
            }

            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return null;
    }

    private static Transmissao findTransmissaoDaUnidade(int id) {
        String sql = "SELECT * FROM transmissao WHERE id = ?;";

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return TransmissaoDAO.resultSetToTransmissao(rs);
            }

            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return null;
    }

    private static Cor findCorDaUnidade(int id) {
        String sql = "SELECT * FROM cor WHERE id = ?;";

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return CorDAO.resultSetToCor(rs);
            }

            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return null;
    }

    protected static Unidade resultSetToUnidade(ResultSet rs) throws SQLException { 
        return new Unidade(
            rs.getInt("id"),
            rs.getShort("ano"),
            rs.getString("placa"),
            rs.getInt("quilometragem"),
            findEstadoConservacaoDaUnidade(rs.getInt("id_estado_conservacao")),
            rs.getDouble("valor_unitario"),
            findVersaoDaUnidade(rs.getInt("id_versao")),
            findTransmissaoDaUnidade(rs.getInt("id_transmissao")),
            findCorDaUnidade(rs.getInt("id_cor")),
            rs.getBoolean("disponibilidade")
        );
    }
}
