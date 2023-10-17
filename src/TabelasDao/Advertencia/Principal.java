package TabelasDao.Advertencia;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

import Tables.Advertencia;
import Tables.Cargo;
import Tables.Escala;
import Tables.Funcionario;
import Tables.Turno;

public class Principal {
    public static void main(String[] args) throws SQLException {
        AdvertenciaDAO advertenciaDao = new AdvertenciaDAO();

        Cargo cargo = new Cargo(1, "Gerente");
        Turno turno = new Turno(1, LocalTime.of(7, 0, 0), LocalTime.of(18, 0, 0));
        Escala escala = new Escala(1, (byte) 5, (byte) 2 ,"Normal");

        Funcionario funcionario = new Funcionario(1, cargo, turno, escala, "João", "joao@email.com", "123456789", "12345678901", LocalDate.now(), "Rua ABC", 3000.0);

        Advertencia advertencia = new Advertencia(funcionario, (byte) 2, "Comportamento inadequado");

        Advertencia advertenciaCriada = advertenciaDao.create(advertencia);

        System.out.println(advertenciaCriada.getId());

        System.out.println(advertenciaDao.findById(1));
    }
}
