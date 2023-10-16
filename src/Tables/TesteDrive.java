package lojadecarros.Tables;

import java.time.LocalDateTime;

public class TesteDrive {
    private int id;
    private LocalDateTime inicio;
    private LocalDateTime fim;
    private Cliente cliente;
    private Funcionario funcionario;
    private Unidade unidade;

    public TesteDrive(int id, LocalDateTime inicio, LocalDateTime fim, Cliente cliente, Funcionario funcionario, Unidade unidade) {
        this.id = id;
        this.inicio = inicio;
        this.fim = fim;
        this.cliente = cliente;
        this.funcionario = funcionario;
        this.unidade = unidade;
        cliente.addTesteDrive(this);
    }

    public TesteDrive(LocalDateTime inicio, LocalDateTime fim, Cliente cliente, Funcionario funcionario, Unidade unidade) {
        this.inicio = inicio;
        this.fim = fim;
        this.cliente = cliente;
        this.funcionario = funcionario;
        this.unidade = unidade;
        cliente.addTesteDrive(this);
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getInicio() {
        return inicio;
    }

    public LocalDateTime getFim() {
        return fim;
    }
}
