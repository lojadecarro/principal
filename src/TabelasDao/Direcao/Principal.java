package TabelasDao.Direcao;

import java.sql.SQLException;
import Tables.Direcao;


public class Principal {
    public static void main(String[] args) throws SQLException {
        DirecaoDAO direcaoDao = new DirecaoDAO();
        
        Direcao direcao = new Direcao(0, "Teste direcao");

        Direcao direcaoCriada = direcaoDao.create(direcao);

        System.out.println(direcaoCriada.getId());

        System.out.println(direcaoDao.findById(1));
    }
    
}
