package LojaDeCarros.Tables;

import java.util.Date;

public class Compra {
    private int id;
    private Funcionario funcionario;
    private Cliente cliente;
    private Unidade unidade;
    private Date dia_horario;

    public Compra(int id, Funcionario funcionario, Cliente cliente, Unidade unidade, Date dia_horario) {
        this.id = id;
        this.funcionario = funcionario;
        this.cliente = cliente;
        this.unidade = unidade;
        this.dia_horario = dia_horario;
        cliente.addVendaCliente(this);
        funcionario.addCompra(this);
    }

    public Compra(Funcionario funcionario, Cliente cliente, Unidade unidade, Date dia_horario) {
        this.funcionario = funcionario;
        this.cliente = cliente;
        this.unidade = unidade;
        this.dia_horario = dia_horario;
        cliente.addVendaCliente(this);
        funcionario.addCompra(this);
    }

    public int getId() {
        return id;
    }

    public Date getDia_horario() {
        return dia_horario;
    }
}
