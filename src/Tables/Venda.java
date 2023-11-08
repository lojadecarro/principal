package Tables;

import java.time.LocalDateTime;

public class Venda {
    private int id;
    private Funcionario funcionario;
    private Cliente cliente;
    private Unidade unidade;
    private LocalDateTime dia_horario;
    private FormaDePagamento forma_pagamento;
    private double desconto;
    private int parcelas;
    private double juros;

    public Venda(int id, Funcionario funcionario, Cliente cliente, Unidade unidade, FormaDePagamento forma_pagamento, double desconto, int parcelas, double juros) {
        Verificacoes.verificarParametroNull(id, funcionario, cliente, unidade, forma_pagamento, desconto, parcelas, juros);
        this.id = id;
        verificarFuncionarioEUnidade();
        this.funcionario = funcionario;
        this.cliente = cliente;
        this.unidade = unidade;
        dia_horario = LocalDateTime.now();
        this.forma_pagamento = forma_pagamento;
        verificarDescontoParcelasEjuros();
        this.desconto = desconto;
        this.parcelas = parcelas;
        this.juros = juros;
        cliente.addCompraCliente(this);
        funcionario.addVenda(this);
        unidade.setDisponibilidade();
    }

    public Venda(Funcionario funcionario, Cliente cliente, Unidade unidade, FormaDePagamento forma_pagamento, double desconto, int parcelas, double juros) {
        Verificacoes.verificarParametroNull(id, funcionario, cliente, unidade, forma_pagamento, desconto, parcelas, juros);
        verificarFuncionarioEUnidade();
        this.funcionario = funcionario;
        this.cliente = cliente;
        this.unidade = unidade;
        dia_horario = LocalDateTime.now();
        this.forma_pagamento = forma_pagamento;
        verificarDescontoParcelasEjuros();
        this.desconto = desconto;
        this.parcelas = parcelas;
        this.juros = juros;
        cliente.addCompraCliente(this);
        funcionario.addVenda(this);
        unidade.setDisponibilidade();
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getDia_horario() {
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

    public double getJuros() {
        return juros;
    }

    public double calcularValorTotalVenda(){
        return unidade.getValor_unitario() + (unidade.getValor_unitario()*(juros/100)) - desconto;
    }

    public double calcularValorParcelas(){
        return calcularValorTotalVenda()/parcelas;
    }

    private void verificarDescontoParcelasEjuros(){
        if (parcelas < 0 || parcelas == 1 || desconto < 0 || juros < 0) {
            throw new RuntimeException("Somente valores positivos.");
        }

        if (desconto > unidade.getValor_unitario()*(1/5)) {
            throw new RuntimeException("O desconto não pode ser maior que 20% do valor unitário da unidade.");
        }

        if (juros > 40) {
            throw new RuntimeException("Coloque somente o valor da porcentagem do juros, estando entre 0 e 40.");
        }

        if (parcelas > 100) {
            throw new RuntimeException("O máximo de parcelas possiveis é 100.");
        }

        if (juros > 0 && parcelas == 0) {
            throw new RuntimeException("Não pode javer juros numa venda à vista.");
        }
    }

    private void verificarFuncionarioEUnidade(){
        if (funcionario.getDisponivel() == false || unidade.getDisponibilidade() == false) {
            throw new RuntimeException("O funcionário ou a unidade não estao disponiveis no momento.");
        }
    }
}
