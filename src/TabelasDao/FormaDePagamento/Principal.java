package TabelasDao.FormaDePagamento;

import java.sql.SQLException;
import Tables.FormaDePagamento;

public class Principal {
    public static void main(String[] args) throws SQLException {
        FormaDePagamentoDAO formaDePagamentoDao = new FormaDePagamentoDAO();

        FormaDePagamento formaDePagamento = new FormaDePagamento(0, "Crédito");

        FormaDePagamento formaDePagamentoCriada = formaDePagamentoDao.create(formaDePagamento);

        System.out.println(formaDePagamentoCriada.getId());

        System.out.println(formaDePagamentoDao.findById(1));
    }
}

