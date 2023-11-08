package Tables;

import java.time.LocalDate;
import java.util.List;

public class Cliente extends Pessoa{
    private List<Venda> compras;
    private List<Compra> vendas;
    
    public Cliente(int id, String nome, String email, String contato, String cpf, LocalDate data_nascimento, Endereco endereco) {
        super(id, nome, email, contato, cpf, data_nascimento, endereco);
    }

    public Cliente(String nome, String email, String contato, String cpf, LocalDate data_nascimento, Endereco endereco) {
        super(nome, email, contato, cpf, data_nascimento, endereco);
    }

    public void addVendaCliente(Compra compra){
        vendas.add(compra);
    }
    
    public void addCompraCliente(Venda venda){
        compras.add(venda);
    }
}
