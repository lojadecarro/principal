package EstruturaBanco;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import Conexao.Conexao;

/*
Esta classe cria inserts para as tabelas que possuem registros que dificilmente serão mudados
e não possuem relação direta com as compras e vendas da loja. Os inserts são para as tabelas: 
marca, modelo, categoria_carro, versao, transmissao, cor, cargo, forma_pagamento e turno.
A classe será usada para que a aplicação desktop, logo após ser criada já tenha os dados básicos 
para serem relacionados na hora do registro de entidades mais complexas. 
*/

public class CriarInsertsBasicos {
    public void criar() throws SQLException {
        String sqlMarca = """
            insert into marca (nome) values
            ('Chevrolet'),
            ('Volkswagen'),
            ('Fiat'),
            ('Ford'),
            ('Toyota'),
            ('Honda'),
            ('Hyundai'),
            ('Nissan'),
            ('Renault'),
            ('Jeep'),
            ('Mitsubishi'),
            ('Peugeot'),
            ('Citroën'),
            ('Kia'),
            ('BMW'),
            ('Mercedes-Benz'),
            ('Audi'),
            ('Land Rover'),
            ('Subaru'),
            ('Lexus'),
            ('Volvo'),
            ('Jaguar'),
            ('Porsche'),
            ('Mazda'),
            ('Chery'),
            ('Suzuki'),
            ('Ferrari'),
            ('Lamborghini'),
            ('Tesla');            
        """;

        String sqlCategoria_carro = """
            insert into categoria_carro (nome) values 
            ('SUV'),
            ('Hatchback'),
            ('Sedan'),
            ('Picape'),
            ('Cupê'),
            ('Conversível'),
            ('Minivan'),
            ('Crossover'),
            ('Esportivo'),
            ('Compacto'),
            ('Van'),
            ('Wagon'),
            ('Híbrido'),
            ('Elétrico');      
        """;

        String sqlForma_pagamento = """
            insert into forma_pagamento (nome) values 
            ('Dinheiro'),
            ('Cartão de Crédito'),
            ('Cartão de Débito'),
            ('Transferência Bancária'),
            ('Boleto Bancário'),
            ('Financiamento'),
            ('Leasing'),
            ('Cheque'),
            ('Consórcio'),
            ('Pix'),
            ('PayPal'),
            ('Apple Pay'),
            ('Google Pay');      
        """;

        String sqlCargo = """
            insert into cargo (nome) values
            ('Dono'),
            ('Gerente Geral'),            
            ('Gerente de Compras'),
            ('Gerente de Vendas'),
            ('Gerente de Serviços'),
            ('Segurança'),
            ('Estoquista'),
            ('Vendedor'),
            ('Mecânico'),
            ('Recepcionista'),
            ('Assistente Administrativo'),
            ('Analista de Marketing'),
            ('Lavador de Carros'),
            ('Detalhista Automotivo'),
            ('Auxiliar de Limpeza');        
        """;

        String sqlTransmissao = """
            insert into transmissao (tipo) values
            ('Manual'),
            ('Automática'),
            ('Automática de Dupla Embreagem'),
            ('Automatizada'),
            ('Transmissão Continuamente Variável'),
            ('Semi-Automática'),
            ('Automatizada de Dupla Embreagem'),
            ('CVT Multitronic'),
            ('Tiptronic'),
            ('Automática Sequencial');         
        """;

        String sqlCor = """
            insert into cor (nome) values
            ('Preto Sólido'),
            ('Branco Sólido'),
            ('Prata Metálico'),
            ('Azul Metálico'),
            ('Vermelho Sólido'),
            ('Verde Metálico'),
            ('Amarelo Sólido'),
            ('Cinza Metálico'),
            ('Dourado Metálico'),
            ('Laranja Sólido'),
            ('Marrom Sólido'),
            ('Roxo Metálico'),
            ('Bege Sólido'),
            ('Bordô Sólido'),
            ('Bronze Metálico'),
            ('Champagne Metálico'),
            ('Cobre Metálico'),
            ('Turquesa Metálico'),
            ('Púrpura Metálico'),
            ('Rosa Sólido'),
            ('Branco Perolizado'),
            ('Preto Perolizado'),
            ('Azul Perolizado');         
        """;

        String sqlEstadoConservacao = """
            insert into estado_conservacao (nome) values
            ('Novo'),
            ('Seminovo '),
            ('Usado');         
        """;

        String sqlModelo = """
            insert into modelo (id_marca, nome) values
            (1, 'Onix'), (1, 'Cruze'), (1, 'Equinox'),
            (2, 'Gol'), (2, 'Virtus'), (2, 'Tiguan'),
            (3, 'Mobi'), (3, 'Argo'), (3, 'Toro'),
            (4, 'Ka'), (4, 'Focus'), (4, 'EcoSport'),
            (5, 'Corolla'), (5, 'Camry'), (5, 'Hilux'),
            (6, 'Civic'), (6, 'Accord'), (6, 'HR-V'),
            (7, 'HB20'), (7, 'Creta'), (7, 'Azera'),
            (8, 'March'), (8, 'Versa'), (8, 'Kicks'),
            (9, 'Clio'), (9, 'Captur'), (9, 'Duster'),
            (10, 'Renegade'), (10, 'Compass'), (10, 'Cherokee'),
            (11, 'L200 Triton'), (11, 'Outlander'), (11, 'Pajero Sport'),
            (12, '208'), (12, '308'), (12, '3008'),
            (13, 'C3'), (13, 'Aircross'), (13, 'C4 Lounge'),
            (14, 'Sportage'), (14, 'Seltos'), (14, 'Cerato'),
            (15, 'Série 3'), (15, 'X5'), (15, 'X3'),
            (16, 'Classe A'), (16, 'Classe C'), (16, 'GLC'),
            (17, 'A3'), (17, 'A4'), (17, 'Q5'),
            (18, 'Discovery Sport'), (18, 'Range Rover Evoque'), (18, 'Defender'),
            (19, 'Impreza'), (19, 'Outback'), (19, 'Forester'),
            (20, 'IS'), (20, 'RX'), (20, 'NX'),
            (21, 'S60'), (21, 'XC40'), (21, 'XC90'),
            (22, 'XE'), (22, 'F-PACE'), (22, 'I-PACE'),
            (23, '911'), (23, 'Cayenne'), (23, 'Panamera'),
            (24, 'MX-5'), (24, 'CX-5'), (24, 'Mazda3'),
            (25, 'Tiggo 2'), (25, 'Tiggo 5X'), (25, 'Arrizo 6'),
            (26, 'Vitara'), (26, 'S-Cross'), (26, 'Swift'),
            (27, 'Portofino'), (27, '488 GTB'), (27, 'F8 Tributo'),
            (28, 'Huracán'), (28, 'Aventador'), (28, 'Urus'),
            (29, 'Model S'), (29, 'Model 3'), (29, 'Model X');      
        """;

        String sqlVersao = """
            insert into versao (id_modelo, id_categoria_carro, nome, lancamento) values
            (1, 11, 'Versao 1', '1995-01-01'), (1, 7, 'Versao 2', '1996-02-05'),
            (2, 3, 'Versao 1', '1997-03-10'), (2, 14, 'Versao 2', '1998-04-15'),
            (3, 5, 'Versao 1', '1999-05-20'), (3, 8, 'Versao 2', '2000-06-25'),
            (4, 1, 'Versao 1', '2001-07-30'), (4, 12, 'Versao 2', '2002-09-04'),
            (5, 9, 'Versao 1', '2003-10-09'), (5, 4, 'Versao 2', '2004-11-14'),
            (6, 6, 'Versao 1', '2005-12-19'), (6, 2, 'Versao 2', '2006-01-24'),
            (7, 10, 'Versao 1', '2007-02-28'), (7, 13, 'Versao 2', '2008-04-04'),
            (8, 7, 'Versao 1', '2009-05-09'), (8, 11, 'Versao 2', '2010-06-14'),
            (9, 3, 'Versao 1', '2011-07-19'), (9, 8, 'Versao 2', '2012-08-24'),
            (10, 12, 'Versao 1', '2013-09-29'), (10, 1, 'Versao 2', '2014-11-03'),
            (11, 14, 'Versao 1', '2015-12-08'), (11, 5, 'Versao 2', '2017-01-13'),
            (12, 2, 'Versao 1', '2018-02-18'), (12, 9, 'Versao 2', '2019-03-25'),
            (13, 4, 'Versao 1', '2020-04-30'), (13, 1, 'Versao 2', '2021-06-04'),
            (14, 6, 'Versao 1', '2022-07-09'), (14, 10, 'Versao 2', '2023-08-14'),
            (15, 14, 'Versao 1', '1996-09-19'), (15, 9, 'Versao 2', '1997-10-24'),
            (16, 11, 'Versao 1', '1998-11-28'), (16, 7, 'Versao 2', '1999-01-02'),
            (17, 3, 'Versao 1', '2000-02-06'), (17, 8, 'Versao 2', '2001-03-13'),
            (18, 12, 'Versao 1', '2002-04-17'), (18, 5, 'Versao 2', '2003-05-22'),
            (19, 2, 'Versao 1', '2004-06-26'), (19, 9, 'Versao 2', '2005-08-01'),
            (20, 6, 'Versao 1', '2006-09-05'), (20, 13, 'Versao 2', '2007-10-10'),
            (21, 8, 'Versao 1', '2008-11-14'), (21, 4, 'Versao 2', '2009-12-19'),
            (22, 1, 'Versao 1', '2010-01-23'), (22, 10, 'Versao 2', '2011-02-27'),
            (23, 7, 'Versao 1', '2012-04-02'), (23, 11, 'Versao 2', '2013-05-07'),
            (24, 3, 'Versao 1', '2014-06-11'), (24, 8, 'Versao 2', '2015-07-16'),
            (25, 9, 'Versao 1', '2016-08-21'), (25, 4, 'Versao 2', '2017-09-25'),
            (26, 5, 'Versao 1', '2018-11-01'), (26, 10, 'Versao 2', '2019-12-06'),
            (27, 6, 'Versao 1', '2021-01-10'), (27, 11, 'Versao 2', '2022-02-14'),
            (28, 1, 'Versao 1', '2023-03-21'), (28, 12, 'Versao 2', '2023-04-26'),
            (29, 2, 'Versao 1', '1996-10-02'), (29, 7, 'Versao 2', '1997-11-07'),
            (30, 13, 'Versao 1', '1998-12-12'), (30, 1, 'Versao 2', '1999-01-16'),
            (31, 8, 'Versao 1', '2000-02-21'),  (31, 3, 'Versao 2', '2001-03-28'),
            (32, 5, 'Versao 1', '2002-05-02'), (32, 10, 'Versao 2', '2003-06-06'),
            (33, 7, 'Versao 1', '2004-07-11'), (33, 11, 'Versao 2', '2005-08-15'),
            (34, 2, 'Versao 1', '2006-09-19'), (34, 9, 'Versao 2', '2007-10-24'),
            (35, 13, 'Versao 1', '2008-11-28'), (35, 4, 'Versao 2', '2009-01-02'),
            (36, 1, 'Versao 1', '2010-02-06'), (36, 10, 'Versao 2', '2011-03-13'),
            (37, 11, 'Versao 1', '2012-04-17'), (37, 6, 'Versao 2', '2013-05-22'),
            (38, 9, 'Versao 1', '2014-06-26'), (38, 4, 'Versao 2', '2015-08-01'),
            (39, 5, 'Versao 1', '2016-09-05'), (39, 10, 'Versao 2', '2017-10-10'),
            (40, 11, 'Versao 1', '2018-11-14'), (40, 6, 'Versao 2', '2019-12-19'),
            (41, 1, 'Versao 1', '2021-01-23'), (41, 12, 'Versao 2', '2022-03-01'),
            (42, 13, 'Versao 1', '2023-04-05'), (42, 4, 'Versao 2', '2023-05-10'),
            (43, 14, 'Versao 1', '1997-11-30'), (43, 5, 'Versao 2', '1998-01-04'),
            (44, 8, 'Versao 1', '1999-02-08'), (44, 3, 'Versao 2', '2000-03-14'),
            (45, 9, 'Versao 1', '2001-04-18'), (45, 2, 'Versao 2', '2002-05-23'),
            (46, 11, 'Versao 1', '2003-06-27'), (46, 7, 'Versao 2', '2004-08-01'),
            (47, 1, 'Versao 1', '2005-09-05'), (47, 13, 'Versao 2', '2006-10-10'),
            (48, 4, 'Versao 1', '2007-11-14'), (48, 10, 'Versao 2', '2008-12-19'),
            (49, 8, 'Versao 1', '2009-01-23'), (49, 3, 'Versao 2', '2010-02-27'),
            (50, 9, 'Versao 1', '2011-04-02'), (50, 2, 'Versao 2', '2012-05-07'),
            (51, 11, 'Versao 1', '2013-06-11'), (51, 6, 'Versao 2', '2014-07-16'),
            (52, 1, 'Versao 1', '2015-08-21'), (52, 10, 'Versao 2', '2016-09-25'),
            (53, 7, 'Versao 1', '2017-11-01'), (53, 12, 'Versao 2', '2018-12-06'),
            (54, 5, 'Versao 1', '2020-01-10'), (54, 13, 'Versao 2', '2021-02-14'),
            (55, 14, 'Versao 1', '2022-03-21'), (55, 9, 'Versao 2', '2023-04-26'),
            (56, 11, 'Versao 1', '1997-12-01'), (56, 6, 'Versao 2', '1998-01-05'),
            (57, 1, 'Versao 1', '1999-02-09'), (57, 10, 'Versao 2', '2000-03-15'),
            (58, 7, 'Versao 1', '2001-04-19'), (58, 12, 'Versao 2', '2002-05-24'),
            (59, 5, 'Versao 1', '2003-06-28'), (59, 9, 'Versao 2', '2004-08-02'),
            (60, 2, 'Versao 1', '2005-09-06'), (60, 13, 'Versao 2', '2006-10-11'),
            (61, 8, 'Versao 1', '2007-11-15'), (61, 3, 'Versao 2', '2008-12-20'),
            (62, 11, 'Versao 1', '2009-01-24'), (62, 6, 'Versao 2', '2010-02-28'),
            (63, 13, 'Versao 1', '2011-04-03'), (63, 5, 'Versao 2', '2012-05-08'),
            (64, 7, 'Versao 1', '2013-06-12'), (64, 9, 'Versao 2', '2014-07-17'),
            (65, 12, 'Versao 1', '2015-08-22'), (65, 4, 'Versao 2', '2016-09-26'),      
            (66, 6, 'Versao 1', '2005-12-19'), (66, 6, 'Versao 2', '2006-01-24'),
            (67, 7, 'Versao 1', '2007-02-28'), (67, 7, 'Versao 2', '2008-04-04'),
            (68, 8, 'Versao 1', '2009-05-09'), (68, 8, 'Versao 2', '2010-06-14'),
            (69, 9, 'Versao 1', '2011-07-19'), (69, 9, 'Versao 2', '2012-08-24'),
            (70, 10, 'Versao 1', '2013-09-29'), (70, 10, 'Versao 2', '2014-11-03'),
            (71, 11, 'Versao 1', '2015-12-08'), (71, 11, 'Versao 2', '2017-01-13'),
            (72, 12, 'Versao 1', '2018-02-18'), (72, 12, 'Versao 2', '2019-03-25'),
            (73, 13, 'Versao 1', '2020-04-30'), (73, 13, 'Versao 2', '2021-06-04'),
            (74, 14, 'Versao 1', '2022-07-09'), (74, 14, 'Versao 2', '2023-08-14'),
            (75, 1, 'Versao 1', '1996-09-19'), (75, 1, 'Versao 2', '1997-10-24'),
            (76, 2, 'Versao 1', '1998-11-28'), (76, 2, 'Versao 2', '1999-01-02'),
            (77, 3, 'Versao 1', '2000-02-06'), (77, 3, 'Versao 2', '2001-03-13'),
            (78, 4, 'Versao 1', '2002-04-17'), (78, 4, 'Versao 2', '2003-05-22'),
            (79, 9, 'Versao 1', '2004-06-26'), (79, 9, 'Versao 2', '2005-08-01'),
            (80, 9, 'Versao 1', '2006-09-05'), (80, 9, 'Versao 2', '2007-10-10'),
            (81, 9, 'Versao 1', '2008-11-14'), (81, 9, 'Versao 2', '2009-12-19'),
            (82, 9, 'Versao 1', '2010-01-23'), (82, 9, 'Versao 2', '2011-02-27'),
            (83, 9, 'Versao 1', '2012-04-02'), (83, 9, 'Versao 2', '2013-05-07'),
            (84, 9, 'Versao 1', '2014-06-11'), (84, 9, 'Versao 2', '2015-07-16'),
            (85, 11, 'Versao 1', '2016-08-21'), (85, 11, 'Versao 2', '2017-09-25'),
            (86, 12, 'Versao 1', '2018-11-01'), (86, 12, 'Versao 2', '2019-12-06'),
            (87, 13, 'Versao 1', '2021-01-10'), (87, 13, 'Versao 2', '2022-02-14');   
        """;

        String sqlTurno = """
            insert into turno (inicio, fim) values 
            ('00:00', '08:00'),
            ('08:00', '16:00'),
            ('16:00', '00:00'),
            ('20:00', '04:00'),
            ('04:00', '12:00'),
            ('12:00', '20:00'),
            ('08:00', '13:00'),
            ('13:00', '18:00'),
            ('18:00', '23:00');        
        """;

        try (Connection connection = Conexao.getConnection(); 
            Statement statement = connection.createStatement()) {
            statement.executeUpdate(sqlMarca);
            statement.executeUpdate(sqlCategoria_carro);
            statement.executeUpdate(sqlModelo);
            statement.executeUpdate(sqlVersao);
            statement.executeUpdate(sqlTransmissao);
            statement.executeUpdate(sqlCor);
            statement.executeUpdate(sqlEstadoConservacao);
            statement.executeUpdate(sqlCargo);
            statement.executeUpdate(sqlForma_pagamento);
            statement.executeUpdate(sqlTurno);
        }
    }
}
