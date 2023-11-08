package TablesDAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import Conexao.Conexao;
import Tables.Cargo;
import Tables.Endereco;
import Tables.Funcionario;
import Tables.Turno;

public class FuncionarioDAO {
    public Funcionario create(Funcionario funcionario) throws SQLException{
        String sql = """
                INSERT INTO funcionario (id_endereco, id_cargo, id_turno, nome, email, contato, cpf, data_nascimento, data_registro, dia_pagamento, salario_fixo, comissao, intervalo1, intervalo2, duracao_intervalo, disponivel)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);
                """;
        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection
            .prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        ) {
            statement.setInt(1, funcionario.getEndereco().getId());
            statement.setInt(2, funcionario.getCargo().getId());
            statement.setInt(3, funcionario.getTurno().getId());
            statement.setString(4, funcionario.getNome());
            statement.setString(5, funcionario.getEmail());
            statement.setString(6, funcionario.getContato());
            statement.setString(7, funcionario.getCpf());
            statement.setDate(8, Date.valueOf(funcionario.getData_nascimento()));
            statement.setDate(9, Date.valueOf(funcionario.getData_registro()));
            statement.setShort(10, funcionario.getDia_pagamentoOriginal());
            statement.setDouble(11, funcionario.getSalario_fixo());
            statement.setDouble(12, funcionario.getComissao());
            LocalTime[] intervalos = funcionario.getIntervalos();
            statement.setTime(13, Time.valueOf(intervalos[0]));
            statement.setTime(14, Time.valueOf(intervalos[1]));
            statement.setShort(15, funcionario.getDuracaoIntervalosMinutos());
            statement.setBoolean(16, false);
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
            UPDATE funcionario SET 
            id_endereco = ?,
            id_cargo = ?,
            id_turno = ?,
            nome = ?,
            email = ?,
            contato = ?,
            dia_pagamento = ?,
            salario_fixo = ?,
            comissao = ?,
            horas_extras = ?,
            intervalo1 = ?,
            intervalo2 = ?,
            duracao_intervalo = ? 
            WHERE id = ?;
            """;
        
        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection
            .prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        ){
            statement.setInt(1, funcionario.getEndereco().getId());
            statement.setInt(2, funcionario.getCargo().getId());
            statement.setInt(3, funcionario.getTurno().getId());
            statement.setString(4, funcionario.getNome());
            statement.setString(5, funcionario.getEmail());
            statement.setString(6, funcionario.getContato());
            statement.setShort(7, funcionario.getDia_pagamentoOriginal());
            statement.setDouble(8, funcionario.getSalario_fixo());
            statement.setDouble(9, funcionario.getComissao());
            statement.setDouble(10, funcionario.getHorasDeTrabalhoDeDiferenca());
            LocalTime[] intervalos = funcionario.getIntervalos();
            statement.setTime(10, Time.valueOf(intervalos[0]));
            statement.setTime(11, Time.valueOf(intervalos[1]));
            statement.setShort(12, funcionario.getDuracaoIntervalosMinutos());
            statement.setInt(13, funcionario.getId());

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

    /*
    public void delete(Funcionario funcionario) {
        delete(funcionario.getId());
    }
    */

    public Funcionario findByEmail(String email) {
        String sql = "SELECT * FROM funcionario WHERE email = ?;";

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setString(1, email);

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

    public Funcionario findByCpf(String cpf) {
        String sql = "SELECT * FROM funcionario WHERE cpf = ?;";

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            String cpffmtd = cpf.substring(0, 3) + "." + cpf.substring(3, 6) + "." + cpf.substring(6, 9) + "-" + cpf.substring(9);
            statement.setString(1, cpffmtd);

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

    public Funcionario findByContato(String contato) {
        String sql = "SELECT * FROM funcionario WHERE contato = ?;";

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            String contatofmtd = "(" + contato.substring(0, 2) + ")" + " " + contato.substring(2, 7) + "-" + contato.substring(7);
            statement.setString(1, contatofmtd);

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

    public List<Funcionario> findFuncionariosDisponiveis() {
        List<Funcionario> funcionarios = new ArrayList<>();
        String sql = "SELECT * FROM funcionario WHERE disponivel = 1;";

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Funcionario funcionario = resultSetToFuncionario(rs);
                funcionarios.add(funcionario);
            }

            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return funcionarios;
    }

    public List<Funcionario> findFuncionariosByTurno(Turno turno) {
        List<Funcionario> funcionarios = new ArrayList<>();
        String sql = "SELECT * FROM funcionario WHERE id_turno = 1;";

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setInt(1, turno.getId());
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Funcionario funcionario = resultSetToFuncionario(rs);
                funcionarios.add(funcionario);
            }

            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return funcionarios;
    }

    public List<Funcionario> findFuncionariosByCargo(Cargo cargo) {
        List<Funcionario> funcionarios = new ArrayList<>();
        String sql = "SELECT * FROM funcionario WHERE id_cargo = 1;";

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Funcionario funcionario = resultSetToFuncionario(rs);
                funcionarios.add(funcionario);
            }

            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return funcionarios;
    }

    private static Endereco findEnderecoDoFuncionario(int id){
        String sql = "SELECT * FROM endereco WHERE id = ?;";

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return EnderecoDAO.resultSetToEndereco(rs);
            }

            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return null;
    }

    private static Turno findTurnoDoFuncionario(int id){
        String sql = "SELECT * FROM turno WHERE id = ?;";

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return TurnoDAO.resultSetToTurno(rs);
            }

            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return null;
    }

    private static Cargo findCargoDoFuncionario(int id){
        String sql = "SELECT * FROM cargo WHERE id = ?;";

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return CargoDAO.resultSetToCargo(rs);
            }

            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return null;
    }

    public List<Funcionario> FindDadosFuncionarios(){
        List<Funcionario> funcionarios = new ArrayList<>();
        String sql = "SELECT * FROM dados_funcionarios;";

        try (
            Connection connection = Conexao.getConnection();
            Statement statement = connection.createStatement();
        ) {
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                Funcionario funcionario = resultSetToFuncionario(rs);
                funcionarios.add(funcionario);
            }

            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return funcionarios;
    }

    public List<Funcionario> FindFuncionariosMaisAdvertidos(){
        List<Funcionario> funcionarios = new ArrayList<>();
        String sql = "SELECT * FROM funcionarios_mais_advertidos;";

        try (
            Connection connection = Conexao.getConnection();
            Statement statement = connection.createStatement();
        ) {
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                Funcionario funcionario = resultSetToFuncionario(rs);
                funcionarios.add(funcionario);
            }

            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return funcionarios;
    }

    protected static Funcionario resultSetToFuncionario(ResultSet rs) throws SQLException {
        Funcionario funcionario = new Funcionario(
            rs.getInt("id"),
            rs.getString("nome"),
            rs.getString("email"),
            rs.getString("contato"),
            rs.getString("cpf"),
            (rs.getDate("data_nascimento")).toLocalDate(), 
            findEnderecoDoFuncionario(rs.getInt("id_endereco")),
            rs.getDouble("salario_fixo"),
            rs.getShort("dia_pagamento"),
            rs.getShort("duracao_intervalo"),
            rs.getTime("intervalo1").toLocalTime(),
            findCargoDoFuncionario(rs.getInt("id_cargo")),
            findTurnoDoFuncionario(rs.getInt("id_turno"))
        );

        funcionario.setComissao(rs.getDouble("comissao"));
        funcionario.adicionarIntervalo(rs.getTime("intervalo2").toLocalTime());
        funcionario.data_registroParaResultSet(rs.getDate("data_registro").toLocalDate());

        if (rs.getBoolean("disponivel") == true) 
            funcionario.setDisponivel();

        return funcionario;
    }
}
