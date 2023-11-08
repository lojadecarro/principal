package TablesDAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import Conexao.Conexao;
import Tables.Cliente;
import Tables.Funcionario;
import Tables.TesteDrive;
import Tables.Unidade;

public class TesteDriveDAO {
    public TesteDrive create(Funcionario funcionario, Cliente cliente, Unidade unidade) throws SQLException{
        String sql = """
            INSERT INTO teste_drive (id_funcionario, id_cliente, id_unidade, inicio) 
            VALUES (?, ?, ?, ?);    
        """;

        TesteDrive teste_drive = new TesteDrive(cliente, funcionario, unidade);

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection
            .prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        ) {
            statement.setInt(1, teste_drive.getFuncionario().getId());
            statement.setInt(1, teste_drive.getCliente().getId());
            statement.setInt(1, teste_drive.getUnidade().getId());
            statement.setTimestamp(4, Timestamp.valueOf(teste_drive.getInicio()));
            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();

            if (rs.next()) {
                teste_drive.setId(rs.getInt(1));
            }

            rs.close();

            return teste_drive;
        } 
    }

    public TesteDrive marcarFim(TesteDrive teste_drive){
        String sql = """
            UPDATE teste_drive
            SET fim = ?
            WHERE ID = ?;        
        """;

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ){
            statement.setTimestamp(1, Timestamp.valueOf(teste_drive.marcarFimTesteDrive()));
            int linhasAfetadas = statement.executeUpdate();
            
            if (linhasAfetadas > 0) {
                return teste_drive;
            }
            return null;

        } catch (SQLException e) {
            return null;
        }
    }

    public List<TesteDrive> findTesteDrivesByData(LocalDate data){
        List<TesteDrive> testes = new ArrayList<>();
        String sql = """
            SELECT * 
            FROM teste_drive
            WHERE date(inicio) = ?        
        """;

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setDate(1, Date.valueOf(data));
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                TesteDrive teste_drive = resultSetToTesteDrive(rs);
                testes.add(teste_drive);
            }

            rs.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        return testes;
    }

    private Funcionario findFuncionarioDoTesteDrive(int id){
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

    private Cliente findClienteDoTesteDrive(int id){
        String sql = "SELECT * FROM cliente WHERE id = ?;";

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return ClienteDAO.resultSetToCliente(rs);
            }

            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return null;
    }

    private Unidade findUnidadeDoTesteDrive(int id){
        String sql = "SELECT * FROM unidade WHERE id = ?;";

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return UnidadeDAO.resultSetToUnidade(rs);
            }

            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return null;
    }

    private TesteDrive resultSetToTesteDrive(ResultSet rs) throws SQLException{
        TesteDrive teste = new TesteDrive(
            rs.getInt("id"),
            findClienteDoTesteDrive(rs.getInt("id_cliente")),
            findFuncionarioDoTesteDrive(rs.getInt("id_funcionario")),
            findUnidadeDoTesteDrive(rs.getInt("id_unidade"))
        );

        teste.inicioEFimParaResultSet(rs.getTimestamp("inicio").toLocalDateTime(), rs.getTimestamp("fim").toLocalDateTime());

        return teste;
    }

}
