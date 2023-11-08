package Tables;

import java.time.LocalDateTime;

public class Compra {
    private int id;
    private Funcionario funcionario;
    private Cliente cliente;
    private Unidade unidade;
    private LocalDateTime dia_horario;

    public Compra(int id, Funcionario funcionario, Cliente cliente, Unidade unidade) {
        Verificacoes.verificarParametroNull(id, funcionario, cliente, unidade);
        this.id = id;
        verificarFuncionarioEUnidade();
        this.funcionario = funcionario;
        this.cliente = cliente;
        this.unidade = unidade;
        dia_horario = LocalDateTime.now();
        cliente.addVendaCliente(this);
        funcionario.addCompra(this);
        unidade.setDisponibilidade();
    }

    public Compra(Funcionario funcionario, Cliente cliente, Unidade unidade) {
        Verificacoes.verificarParametroNull(id, funcionario, cliente, unidade);
        verificarFuncionarioEUnidade();
        this.funcionario = funcionario;
        this.cliente = cliente;
        this.unidade = unidade;
        dia_horario = LocalDateTime.now();
        cliente.addVendaCliente(this);
        funcionario.addCompra(this);
        unidade.setDisponibilidade();
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getDia_horario() {
        return dia_horario;
    }

    public void verificarFuncionarioEUnidade(){
        if (funcionario.getDisponivel() == false || unidade.getDisponibilidade() == true) {
            throw new RuntimeException("O funcionário não está disponivel no momento ou a unidade vinculada à compra já está em posse da loja");
        }
    }
}
