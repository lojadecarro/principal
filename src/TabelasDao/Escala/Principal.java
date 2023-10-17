package TabelasDao.Escala;

import java.sql.SQLException;
import Tables.Escala;

public class Principal {
    public static void main(String[] args) throws SQLException {
        EscalaDAO escalaDao = new EscalaDAO();

        Escala escala = new Escala((byte) 5, (byte) 1, "tipo da escala");

        Escala escalaCriada = escalaDao.create(escala);

        System.out.println(escalaCriada.getId());

        System.out.println(escalaDao.findById(1));
    }
}
