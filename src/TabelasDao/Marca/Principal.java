package TabelasDao.Marca;

import java.sql.SQLException;

import Tables.Marca;

public class Principal{
    public static void main(String[] args) throws SQLException {
        MarcaDAO marcaDao = new MarcaDAO();
        
        Marca marca = new Marca(0, "Ford");

        Marca marcaCriada = marcaDao.create(marca);

        System.out.println(marcaCriada.getId());

        System.out.println(marcaDao.findById(7));                
    }
}