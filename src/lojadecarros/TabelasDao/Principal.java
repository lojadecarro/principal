package LojaDeCarros.TabelasDao;

import java.sql.SQLException;

import LojaDeCarros.Tables.Marca;

public class Principal{
    public static void main(String[] args) throws SQLException {
        MarcaDAO marcaDao = new MarcaDAO();
        
        Marca marca = new Marca(7, "Ford4");

       // Marca marcaCriada = marcaDao.create(marca);

       // System.out.println(marcaCriada.getId());

        System.out.println(marcaDao.findById(7));

        marcaDao.update(marca);


                
    }
}