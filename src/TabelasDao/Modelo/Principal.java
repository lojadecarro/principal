package TabelasDao.Modelo;

import java.sql.SQLException;
import TabelasDao.CategoriaModelo.CategoriaModeloDAO;
import TabelasDao.Marca.MarcaDAO;
import Tables.Modelo;

public class Principal {
    public static void main(String[] args) throws SQLException {
        
        ModeloDAO modeloDao = new ModeloDAO();

        MarcaDAO marcaDao = new MarcaDAO();  

        CategoriaModeloDAO categoriaModeloDao = new CategoriaModeloDAO();

        Modelo modelo = new Modelo(marcaDao.findById(3), categoriaModeloDao.findById(5), "testemodelo9");

        Modelo modeloCriado = modeloDao.create(modelo);

        System.out.println(modeloCriado.getId());
    }
}