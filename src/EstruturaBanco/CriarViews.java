package EstruturaBanco;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import Conexao.Conexao;

public class CriarViews {
    public void criar() throws SQLException{
        String sqlUnidades_para_venda = """
            create view unidades_para_venda as
            select ma.nome as marca, mo.nome as modelo, ve.nome as versao, es.nome as estado_conservacao, co.nome as cor, un.valor_unitario
            from unidade un
            inner join cor co on co.id = un.id_cor
            inner join estado_conservacao es on es.id = un.id_estado_conservacao
            inner join versao ve on ve.id = un.id_versao
            inner join modelo mo on mo.id = ve.id_modelo
            inner join marca ma on ma.id = mo.id_marca
            where un.disponibilidade = 1
            order by un.valor_unitario; 
        """;

        String sqlMelhores_funcionario_ultimo_mes = """
            create view melhores_funcionarios_ultimo_mes as
            select fu.nome as funcionario, fu.cpf, count(ve.id_funcionario) as numero_vendas, sum(un.valor_unitario+(un.valor_unitario*(ve.juros/100))-ve.desconto) as valor_vendas
            from funcionario fu
            inner join venda ve on fu.id = ve.id_funcionario
            inner join unidade un on un.id = ve.id_unidade
            where ve.dia_horario >= now() - interval 1 month
            group by fu.nome, fu.cpf
            order by numero_vendas;     
        """;

        String sqlCompras_clientes = """
            create view compras_clientes as
            select cl.nome as cliente, cl.cpf, count(ve.id_cliente) as total_compras
            from cliente cl
            inner join venda ve on ve.id_cliente = cl.id
            group by cl.nome, cl.cpf
            order by total_compras;     
        """; 

        String sqlModelos_mais_vendidos_ultimo_ano = """
            create view modelos_mais_vendidos_ultimo_ano as
            select mo.nome as modelo, count(ve.id_unidade) as total_vendas_ultimo_ano
            from modelo mo
            inner join versao ver on ver.id_modelo = mo.id
            inner join unidade un on un.id_versao = ver.id
            inner join venda ve on ve.id_unidade = un.id
            where ve.dia_horario >= now() - interval 1 year
            group by mo.nome
            order by total_vendas_ultimo_ano desc;     
        """;

        String sqlDados_funcionarios = """
            create view dados_funcionarios as 
            select fu.nome, fu.cpf, fu.contato, fu.email, ca.nome as cargo, concat(en.logradouro, " ", en.numero) as endereco
            from funcionario fu
            inner join cargo ca on fu.id_cargo = ca.id
            inner join endereco en on fu.id_endereco = en.id;
        """;

        String sqlFuncionarios_mais_advertidos = """
            create view funcionarios_mais_advertidos as
            select fu.nome, fu.cpf, count(ad.id_funcionario) as advertencias, avg(ad.gravidade) as media_gravidade_advertencias
            from funcionario fu
            inner join advertencia ad on ad.id_funcionario = fu.id
            group by fu.nome, fu.cpf
            order by advertencias desc;     
        """;

        try (Connection connection = Conexao.getConnection();
            Statement statement = connection.createStatement()) {
            statement.executeUpdate(sqlUnidades_para_venda);
            statement.executeUpdate(sqlModelos_mais_vendidos_ultimo_ano);
            statement.executeUpdate(sqlCompras_clientes);
            statement.executeUpdate(sqlMelhores_funcionario_ultimo_mes);
            statement.executeUpdate(sqlDados_funcionarios);
            statement.executeUpdate(sqlFuncionarios_mais_advertidos);          
        }
    }
}
