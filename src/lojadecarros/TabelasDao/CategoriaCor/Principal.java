package lojadecarros.TabelasDao.CategoriaCor;

import java.sql.SQLException;
import lojadecarros.Tables.CategoriaCor;

public class Principal {
    public static void main(String[] args) throws SQLException {
        CategoriaCorDAO categoriaCorDao = new CategoriaCorDAO();
        
        CategoriaCor categoriaCor = new CategoriaCor(0, "Turquesa");

        CategoriaCor categoriaCorCriada = categoriaCorDao.create(categoriaCor);

        System.out.println(categoriaCorCriada.getId());

        System.out.println(categoriaCorDao.findById(1));
    }
}
