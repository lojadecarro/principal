package TabelasDao.Pessoa;


import java.sql.SQLException;
import java.time.LocalDate;

import Tables.Pessoa;

public class Principal {
    public static void main(String[] args) throws SQLException {
        PessoaDAO pessoaDao = new PessoaDAO();

        Pessoa pessoa = new Pessoa("Tro", "troarmen@email.com", "11964785277", "43223446512", LocalDate.of(2005, 9, 20), "Av Cruzeiro do Sul");
        
        Pessoa pessoaCriada = pessoaDao.create(pessoa);

        System.out.println(pessoaCriada.getId());
    }
}
