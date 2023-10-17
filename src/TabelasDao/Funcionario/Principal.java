package TabelasDao.Funcionario;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

import Tables.Cargo;
import Tables.Escala;
import Tables.Funcionario;
import Tables.Turno;

public class Principal {
    public static void main(String[] args) throws SQLException {
        FuncionarioDAO funcionarioDao = new FuncionarioDAO();

        Cargo cargo = new Cargo(1, "Gerente");
        Turno turno = new Turno(1, LocalTime.of(7, 0, 0), LocalTime.of(18, 0, 0));
        Escala escala = new Escala(1, (byte) 5, (byte) 2 ,"Normal");

        Funcionario funcionario = new Funcionario(cargo, turno, escala, "João", "joao@email.com", "123456789", "12345678901", LocalDate.now(), "Rua ABC", 3000.0);

        Funcionario funcionarioCriado = funcionarioDao.create(funcionario);

        System.out.println(funcionarioCriado.getId());

        System.out.println(funcionarioDao.findById(1));
    }
}
