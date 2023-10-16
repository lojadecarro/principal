package Tables;

import java.util.Date;
import java.util.List;

public class Venda {
    private int id;
    private Funcionario funcionario;
    private Cliente cliente;
    private Unidade unidade;
    private Date dia_horario;
    private FormaDePagamento forma_pagamento;
    private double desconto;
    private int parcelas;
    private int juros;

    public Venda(int id, Funcionario funcionario, Cliente cliente, Unidade unidade, Date dia_horario, FormaDePagamento forma_pagamento, double desconto, int parcelas, int juros) {
        this.id = id;
        this.funcionario = funcionario;
        this.cliente = cliente;
        this.unidade = unidade;
        this.dia_horario = dia_horario;
        this.forma_pagamento = forma_pagamento;
        this.desconto = desconto;
        this.parcelas = parcelas;
        this.juros = juros;
        cliente.addCompraCliente(this);
        funcionario.addVenda(this);
    }

    public Venda(Funcionario funcionario, Cliente cliente, Unidade unidade, Date dia_horario, FormaDePagamento forma_pagamento, double desconto, int parcelas, int juros) {
        this.funcionario = funcionario;
        this.cliente = cliente;
        this.unidade = unidade;
        this.dia_horario = dia_horario;
        this.forma_pagamento = forma_pagamento;
        this.desconto = desconto;
        this.parcelas = parcelas;
        this.juros = juros;
        cliente.addCompraCliente(this);
        funcionario.addVenda(this);
    }

    public int getId() {
        return id;
    }

    public Date getDia_horario() {
        return dia_horario;
    }

    public FormaDePagamento getForma_pagamento() {
        return forma_pagamento;
    }

    public double getDesconto() {
        return desconto;
    }

    public int getParcelas() {
        return parcelas;
    }

    public int getJuros() {
        return juros;
    }
}
