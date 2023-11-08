package EstruturaBanco;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import Conexao.Conexao;

public class RemoverTodasViews {
    public void remover() throws SQLException{
        String removerSeExisteUnidades_para_vendas = """
            drop view if exists unidades_para_venda;        
        """;

        String removerSeExisteMelhores_funcionarios_ultimo_mes = """
            drop view if exists melhores_funcionarios_ultimo_mes;        
        """;

        String removerSeExisteCompras_clientes = """
            drop view if exists compras_clientes;        
        """;

        String removerSeExisteModelos_mais_vendidos_ultimo_ano = """
            drop view if exists modelos_mais_vendidos_ultimo_ano;        
        """;

        String removerSeExisteDados_funcionarios = """
            drop view if exists dados_funcionarios;        
        """;

        String removerSeExisteFuncionarios_mais_advertidos = """
            drop view if exists funcionarios_mais_advertidos;        
        """;

        String removerSeExisteVendas_funcionarios = """
            drop view if exists vendas_funcionarios;        
        """;

        try (Connection connection = Conexao.getConnection();
            Statement statement = connection.createStatement()) {
            statement.executeUpdate(removerSeExisteUnidades_para_vendas);
            statement.executeUpdate(removerSeExisteMelhores_funcionarios_ultimo_mes);
            statement.executeUpdate(removerSeExisteCompras_clientes);
            statement.executeUpdate(removerSeExisteModelos_mais_vendidos_ultimo_ano);
            statement.executeUpdate(removerSeExisteDados_funcionarios);
            statement.executeUpdate(removerSeExisteFuncionarios_mais_advertidos);        
            statement.executeUpdate(removerSeExisteVendas_funcionarios);        
        }
    }
}
