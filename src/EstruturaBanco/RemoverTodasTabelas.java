package EstruturaBanco;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import Conexao.Conexao;

public class RemoverTodasTabelas {
    public void remover() throws SQLException{
        String removerSeExisteCompra = """
            drop table if exists compra;        
        """;

        String removerSeExisteVenda = """
            drop table if exists venda;        
        """;

        String removerSeExisteTeste_drive = """
            drop table if exists teste_drive;        
        """;

        String removerSeExisteUnidade = """
            drop table if exists unidade;        
        """;

        String removerSeExisteAdvertencia = """
            drop table if exists advertencia;        
        """;

        String removerSeExisteRegistro_ponto = """
            drop table if exists registro_ponto;        
        """;

        String removerSeExisteFuncionario = """
            drop table if exists funcionario;        
        """;

        String removerSeExisteCliente = """
            drop table if exists cliente;        
        """;

        String removerSeExisteVersao = """
            drop table if exists versao;        
        """;

        String removerSeExisteModelo = """
            drop table if exists modelo;        
        """;

        String removerSeExisteMarca = """
            drop table if exists marca;        
        """;

        String removerSeExisteCategoria_carro = """
            drop table if exists categoria_carro;        
        """;

        String removerSeExisteTransmissao = """
            drop table if exists transmissao;        
        """;

        String removerSeExisteCor = """
            drop table if exists cor;        
        """;

        String removerSeExisteEstadoConservacao = """
            drop table if exists estado_conservacao;        
        """;

        String removerSeExisteTurno = """
            drop table if exists turno;        
        """;

        String removerSeExisteCargo = """
            drop table if exists cargo;        
        """;

        String removerSeExisteForma_pagamento = """
            drop table if exists forma_pagamento;        
        """;

        String removerSeExisteEndereco = """
            drop table if exists endereco;        
        """;

        try (Connection connection = Conexao.getConnection();
            Statement statement = connection.createStatement()) {
            statement.executeUpdate(removerSeExisteCompra);
            statement.executeUpdate(removerSeExisteVenda);
            statement.executeUpdate(removerSeExisteTeste_drive);
            statement.executeUpdate(removerSeExisteUnidade);
            statement.executeUpdate(removerSeExisteAdvertencia);
            statement.executeUpdate(removerSeExisteRegistro_ponto);
            statement.executeUpdate(removerSeExisteFuncionario);
            statement.executeUpdate(removerSeExisteCliente);
            statement.executeUpdate(removerSeExisteVersao);
            statement.executeUpdate(removerSeExisteModelo);
            statement.executeUpdate(removerSeExisteMarca);
            statement.executeUpdate(removerSeExisteCategoria_carro);
            statement.executeUpdate(removerSeExisteTransmissao);
            statement.executeUpdate(removerSeExisteCor);
            statement.executeUpdate(removerSeExisteEstadoConservacao);
            statement.executeUpdate(removerSeExisteTurno);
            statement.executeUpdate(removerSeExisteCargo);
            statement.executeUpdate(removerSeExisteForma_pagamento);
            statement.executeUpdate(removerSeExisteEndereco);            
        }
    }
}
