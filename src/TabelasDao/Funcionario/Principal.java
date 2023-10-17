package TabelasDao.Funcionario;

import TabelasDao.Escala.EscalaDAO;

public class Principal {
    public static void main(String[] args) {

        EscalaDAO escalaDAO = new EscalaDAO();
        
        Funcionario fun = new Funcionario(1, 1, escalaDAO.create(new Escala()));
    
}
