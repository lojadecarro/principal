package lojadecarros.TabelasDao.CategoriaModelo;

import java.sql.SQLException;
import lojadecarros.Tables.CategoriaModelo;

public class Principal {
    public static void main(String[] args) throws SQLException {
        CategoriaModeloDAO categoriaModeloDao = new CategoriaModeloDAO();
        
        CategoriaModelo categoriaModelo = new CategoriaModelo(0, "Teste categoria_modelo");

        CategoriaModelo categoriaModeloCriada = categoriaModeloDao.create(categoriaModelo);

        System.out.println(categoriaModeloCriada.getId());

        System.out.println(categoriaModeloDao.findById(1));
    }
}
