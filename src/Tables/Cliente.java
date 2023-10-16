package Tables;

import java.sql.Date;
import java.util.List;

public class Cliente extends Pessoa{
    private List<Venda> compras;
    private List<Compra> vendas;
    private List<TesteDrive> testes_drive;
    
    public Cliente(int id, String nome, String email, int contato, int cpf, Date data_nascimento, String endereco) {
        super(id, nome, email, contato, cpf, data_nascimento, endereco);
    }

    public Cliente(String nome, String email, int contato, int cpf, Date data_nascimento, String endereco) {
        super(nome, email, contato, cpf, data_nascimento, endereco);
    }

    public void addTesteDrive(TesteDrive teste_drive){
        testes_drive.add(teste_drive);
    }

    public void addVendaCliente(Compra compra){
        vendas.add(compra);
    }
    
    public void addCompraCliente(Venda venda){
        compras.add(venda);
    }
}
