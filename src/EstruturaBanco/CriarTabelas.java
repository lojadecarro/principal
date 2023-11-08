package EstruturaBanco;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import Conexao.Conexao;

public class CriarTabelas {
    public void criar() throws SQLException {
        String sqlMarca = """
            create table marca(
                id int auto_increment,
                nome varchar(50) unique,
                primary key(id)
            );
        """;

        String sqlCategoria_carro = """
            create table categoria_carro(
                id int auto_increment,
                nome varchar(20) unique,
                primary key(id)
            );
        """;

        String sqlModelo = """
            create table modelo(
                id int auto_increment,
                id_marca int,
                nome varchar(40) unique,
                foreign key(id_marca) references marca(id),
                primary key(id)
            );
        """;

        String sqlVersao = """
            create table versao(
                id int auto_increment,
                id_modelo int,                
                id_categoria_carro int,
                nome varchar(30),
                unique(id_modelo, nome),
                lancamento date,
                foreign key(id_modelo) references modelo(id),               
                foreign key(id_categoria_carro) references categoria_carro(id),
                primary key(id)
            );
        """;

        String sqlTransmissao = """
            create table transmissao(
                id int auto_increment,
                tipo varchar(40) unique,
                primary key(id)
            );
        """;

        String sqlCor = """
            create table cor(
                id int auto_increment,
                nome varchar(30) unique,
                primary key(id)
            );
        """;

        String sqlEstado_conservacao = """
            create table estado_conservacao(
                id int auto_increment,
                nome varchar(20) unique,
                primary key(id)
            );
        """;

        String sqlUnidade = """
            create table unidade(
                id int auto_increment,
                id_transmissao int,
                id_cor int,
                id_versao int,
                id_estado_conservacao int,
                ano smallint,
                placa varchar(8) unique,
                quilometragem int,
                valor_unitario decimal(9,2),
                disponibilidade boolean,
                foreign key(id_transmissao) references transmissao(id),
                foreign key(id_cor) references cor(id),
                foreign key(id_versao) references versao(id),
                foreign key(id_estado_conservacao) references estado_conservacao(id),
                primary key(id)
            );
        """;

        String sqlEndereco = """
            create table endereco(
                id int auto_increment,
                logradouro varchar(200),
                numero smallint,
                complemento varchar(100),
                unique(logradouro, numero, complemento),
                cep char(9),
                primary key(id)
            );
        """;

        String sqlTurno = """
            create table turno(
                id int auto_increment,
                inicio time,
                fim time,
                unique(inicio, fim),
                primary key(id)
            );
        """;

        String sqlAdvertencia = """
            create table advertencia(
                id int auto_increment, 
                id_funcionario int,
                gravidade tinyint,
                motivo text,
                foreign key(id_funcionario) references funcionario(id),
                primary key(id)
            );
        """;

        String sqlRegistro_ponto = """
            create table registro_ponto(
                id int auto_increment, 
                id_funcionario int,
                entrada datetime,
                saida datetime,
                primary key(id),
                foreign key(id_funcionario) references funcionario(id)
            );
        """;

        String sqlCargo = """
            create table cargo(
                id int auto_increment,
                nome varchar(40) unique,
                primary key(id)
            );
        """;

        String sqlForma_pagamento = """
            create table forma_pagamento(
                id int auto_increment,
                nome varchar(30) unique,
                primary key(id)
            );
        """;

        String sqlFuncionario = """
            create table funcionario(
                id int auto_increment,
                id_endereco int,
                id_cargo int,
                id_turno int,
                nome varchar(100),
                email varchar(256) unique,
                contato char(15) unique,
                cpf char(14) unique,
                data_nascimento date,
                data_registro date,
                dia_pagamento tinyint,
                salario_fixo decimal(10,2),
                comissao decimal(10,2),
                horas_extras decimal(4,2),
                intervalo1 time,
                intervalo2 time,
                duracao_intervalo tinyint,
                disponivel boolean,
                primary key(id),
                foreign key(id_endereco) references endereco(id),
                foreign key(id_cargo) references cargo(id),
                foreign key(id_turno) references turno(id)
            );
        """;

        String sqlCliente = """
            create table cliente(
                id int auto_increment,
                id_endereco int,
                nome varchar(100),
                email varchar(256) unique,
                contato char(15) unique,
                cpf char(14) unique,
                data_nascimento date,
                data_registro date,
                primary key(id),
                foreign key(id_endereco) references endereco(id)
            );
        """;

        String sqlCompra = """
            create table compra(
                id int auto_increment,
                id_funcionario int,
                id_cliente int,
                id_unidade int,
                dia_horario datetime,
                primary key(id),
                foreign key(id_funcionario) references funcionario(id),
                foreign key(id_cliente) references cliente(id),
                foreign key(id_unidade) references unidade(id)
            );
        """;

        String sqlVenda = """
            create table venda(
                id int auto_increment,
                id_funcionario int,
                id_cliente int,
                id_unidade int,
                id_forma_pagamento int,
                dia_horario datetime,
                desconto decimal(9,2),
                parcelas tinyint,
                juros decimal(4,2),
                primary key(id),
                foreign key(id_funcionario) references funcionario(id),
                foreign key(id_cliente) references cliente(id),
                foreign key(id_unidade) references unidade(id)
            );
        """;

        String sqlTeste_drive = """
            create table teste_drive(
                id int auto_increment,
                id_funcionario int,
                id_cliente int,
                id_unidade int,
                inicio datetime,
                fim datetime,
                primary key(id),
                foreign key(id_funcionario) references funcionario(id),
                foreign key(id_cliente) references cliente(id),
                foreign key(id_unidade) references unidade(id)
            );
        """;

        try (Connection connection = Conexao.getConnection();
            Statement statement = connection.createStatement()) {
            statement.executeUpdate(sqlMarca);
            statement.executeUpdate(sqlCategoria_carro);
            statement.executeUpdate(sqlModelo);
            statement.executeUpdate(sqlVersao);
            statement.executeUpdate(sqlTransmissao);
            statement.executeUpdate(sqlCor);
            statement.executeUpdate(sqlEstado_conservacao);
            statement.executeUpdate(sqlUnidade);
            statement.executeUpdate(sqlEndereco);
            statement.executeUpdate(sqlCargo);
            statement.executeUpdate(sqlForma_pagamento);
            statement.executeUpdate(sqlTurno);
            statement.executeUpdate(sqlFuncionario);
            statement.executeUpdate(sqlCliente);
            statement.executeUpdate(sqlTeste_drive);
            statement.executeUpdate(sqlCompra);
            statement.executeUpdate(sqlVenda);
            statement.executeUpdate(sqlAdvertencia);
            statement.executeUpdate(sqlRegistro_ponto);            
        }
    }
}
