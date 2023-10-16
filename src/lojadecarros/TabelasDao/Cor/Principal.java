package lojadecarros.TabelasDao.Cor;

import java.sql.SQLException;
import lojadecarros.Tables.Cor;

public class Principal {
    public static void main(String[] args) throws SQLException {
        CorDAO corDao = new CorDAO();
        
        Cor cor = new Cor(0, 1, "Teste cor");

        Cor corCriada = corDao.create(cor);

        System.out.println(corCriada.getId());

        System.out.println(corDao.findById(1));
    }
}
//Ao rodar receberá um erro dizendo que this.categoriaCor é nula
// logo não se pode usar categoriaCor.setId dentro do construtor
