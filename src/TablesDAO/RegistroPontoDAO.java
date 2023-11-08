package TablesDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import Conexao.Conexao;
import Tables.Advertencia;
import Tables.RegistroPonto;


public class RegistroPontoDAO {
    public RegistroPonto create(RegistroPonto ponto) throws SQLException {
        String sql = """
        INSERT INTO registro_ponto (id_funcionario)
        VALUES (?);
        """;

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection
            .prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        ) {
            statement.setInt(1, ponto.getFuncionario().getId());
            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();

            if(rs.next()) {
                ponto.setId(rs.getInt(1));
            }

            rs.close();

            return ponto;
        }
    }

    public RegistroPonto marcarSaida(RegistroPonto ponto) throws SQLException {
        ponto.baterPontoSaida();

        String sql = """
        UPDATE ponto
        SET saida = ?
        WHERE id = ?;
        """;

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {

            statement.setTimestamp(1, Timestamp.valueOf(ponto.getSaida()));
            statement.setInt(2, ponto.getId());
            int linhasAfetadas = statement.executeUpdate();

            if (ponto.calcularHorasDeTrabalhoDeDiferenca() < -(20/60)) {
                Advertencia advertecia = new Advertencia((byte) 1, ponto.getFuncionario(), "Funcionário com atraso superior a 20 minutos");
                AdvertenciaDAO ad = new AdvertenciaDAO();
                ad.create(advertecia);
            } else if (ponto.calcularHorasDeTrabalhoDeDiferenca() > 20/60 && ponto.calcularHorasDeTrabalhoDeDiferenca() < 2) {
                ponto.getFuncionario().ModificarHorasDeTrabalhoDeDiferenca(ponto.calcularHorasDeTrabalhoDeDiferenca());
            } else if (ponto.calcularHorasDeTrabalhoDeDiferenca() > 2) {
                ponto.getFuncionario().ModificarHorasDeTrabalhoDeDiferenca(2);
            } else {
            }

            if (ponto.verificarEntrada() != null) {
                AdvertenciaDAO ad = new AdvertenciaDAO();
                ad.create(ponto.verificarEntrada());
            }

            FuncionarioDAO funcionarioUpdt = new FuncionarioDAO();
            funcionarioUpdt.update(ponto.getFuncionario());

            if (linhasAfetadas > 0) {
                return ponto;
            }
            return null;
         
            } catch (SQLException e) {
                return null;
            }
    }
}