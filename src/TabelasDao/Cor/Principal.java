package TabelasDao.Cor;

import java.sql.SQLException;

import TabelasDao.CategoriaCor.CategoriaCorDAO;
import Tables.CategoriaCor;
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
//Ao rodar receberá um erro dizendo que this.categoriaCor é nula
// logo não se pode usar categoriaCor.setId dentro do construtor
