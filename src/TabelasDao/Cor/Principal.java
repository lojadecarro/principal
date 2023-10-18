package TabelasDao.Cor;

import java.sql.SQLException;

import TabelasDao.CategoriaCor.CategoriaCorDAO;
import Tables.Cor;

public class Principal {
    public static void main(String[] args) throws SQLException {
        CorDAO corDao = new CorDAO();

        CategoriaCorDAO categoriaCorDAO = new CategoriaCorDAO();
        
        Cor cor = new Cor(0, categoriaCorDAO.findById(4), "Vermelho");

        Cor corCriada = corDao.create(cor);

        System.out.println(corCriada.getId());

        System.out.println(corDao.findById(3));
    }
}
