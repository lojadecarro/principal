package TabelasDao.Turno;

import java.sql.SQLException;
import java.time.LocalTime;

import Tables.Turno;

public class Principal {
    public static void main(String[] args) throws SQLException {
        TurnoDAO turnoDAO = new TurnoDAO();

        Turno novoTurno = new Turno(LocalTime.of(8, 0), LocalTime.of(17, 0));

        Turno turnoCriado = turnoDAO.create(novoTurno);

        System.out.println(turnoCriado.getId());

        System.out.println(turnoDAO.findById(1));
    }
}
