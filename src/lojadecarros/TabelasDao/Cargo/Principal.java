package lojadecarros.TabelasDao.Cargo;

import java.sql.SQLException;

import lojadecarros.Tables.Cargo;

public class Principal{
    public static void main(String[] args) throws SQLException {
        CargoDAO cargoDao = new CargoDAO();
        
        Cargo cargo = new Cargo(0, "Limpeza");

        Cargo cargoCriado = cargoDao.create(cargo);

        System.out.println(cargoCriado.getId());

        System.out.println(cargoDao.findById(2));                
    }
}