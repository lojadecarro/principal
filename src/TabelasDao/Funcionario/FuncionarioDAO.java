package TabelasDao.Funcionario;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import TabelasDao.Conexao;
import Tables.Funcionario;

public class FuncionarioDAO {
    public Funcionario create(Funcionario funcionario) throws SQLException{
        String sql = """
                INSERT INTO funcionario (id_cargo, id_turno, id_escala, nome, email, contato, cpf, data_nascimento, endereco, complemento)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);
                """;
        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection
            .prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        ) {
            statement.setInt(1, funcionario.getIdCargo());
            statement.setInt(2, funcionario.getIdTurno());
            statement.setInt(3, funcionario.getIdEscala());
            statement.setString(4, funcionario.getNome());
            statement.setString(5, funcionario.getEmail());
            statement.setString(6, funcionario.getContato());
            statement.setString(7, funcionario.getCpf());
            statement.setDate(8, Date.valueOf(funcionario.getData_nascimento()));
            statement.setString(9, funcionario.getEndereco());
            statement.setString(10, funcionario.getComplemento());
            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();

            if(rs.next()) {
                funcionario.setId(rs.getInt(1));
            }

            rs.close();

            return funcionario;
        }
    }

    public Funcionario update(Funcionario funcionario) throws SQLException {
        String sql = """
            UPDATE funcionario SET id_cargo = ?, id_turno = ?, id_escala = ?, nome = ?, email = ?, contato = ?, 
            cpf = ?, data_nascimento = ?, endereco = ?, complemento = ?, 
            WHERE id = ?;
            """;
        
        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection
            .prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        ){
            statement.setInt(1, funcionario.getIdCargo());
            statement.setInt(2, funcionario.getIdTurno());
            statement.setInt(3, funcionario.getIdEscala());
            statement.setString(4, funcionario.getNome());
            statement.setString(5, funcionario.getEmail());
            statement.setString(6, funcionario.getContato());
            statement.setString(7, funcionario.getCpf());
            statement.setDate(8, Date.valueOf(funcionario.getData_nascimento()));
            statement.setString(9, funcionario.getEndereco());
            statement.setString(10, funcionario.getComplemento());
            statement.setInt(11, funcionario.getId());

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                return funcionario;
            }
            return null;
        
        } catch (SQLException e){
            return null;
        }
    }
 
    public void delete(Integer id) {
        String sql = "DELETE FROM funcionario WHERE id = ?;";

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

    public void delete(Funcionario funcionario) {
        delete(funcionario.getId());
    }

    public Funcionario findById(Integer id) {
        String sql = "SELECT * FROM funcionario WHERE id = ?;";

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return resultSetToFuncionario(rs);
            }

            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return null;
    }

    private Funcionario resultSetToFuncionario(ResultSet rs) throws SQLException {
        return new Funcionario(
            rs.getInt("id"),
            rs.getInt("id_cargo"),
            rs.getInt("id_turno"),
            rs.getInt("id_escala"),
            rs.getString("nome"),
            rs.getString("email"),
            rs.getString("contato"),
            rs.getString("cpf"),
            rs.getDate("data_nascimento").toLocalDate(),
            rs.getString("endereco"),
            rs.getDouble("salario_fixo")
        );
    }
}