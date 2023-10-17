package TabelasDao.Advertencia;

import java.time.LocalDate;

import TabelasDao.Cargo.CargoDAO;
import TabelasDao.CategoriaCor.CategoriaCorDAO;
import TabelasDao.Cor.CorDAO;
import TabelasDao.Escala.EscalaDAO;
import Tables.Advertencia;
import Tables.Funcionario;

public class Principal {
    public static void main(String[] args) {
        AdvertenciaDAO advertenciaDao = new AdvertenciaDAO();

        CargoDAO cargoDao = new CargoDAO();
        EscalaDAO escalaDao = new EscalaDAO();

        LocalDate dataNascimento = LocalDate.of(2007, 9, 28);
        Funcionario funcionario = new Funcionario(1, "Tro", "troarmen@gmail.com", 1194785277, 45224336446, dataNascimento, "Rua Jaragua", 1.200, cargoDao.findById(1), null, escalaDao.findById(1));

        //FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
        
        Advertencia advertencia = new Advertencia(funcionario, (byte) 2, "Atraso");

        Advertencia advertenciaCriada = advertenciaDao.create(advertencia);

        
    }
}
