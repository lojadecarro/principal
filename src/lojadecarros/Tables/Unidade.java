package LojaDeCarros.Tables;

import java.time.LocalDate;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Unidade {
    private int id;
    private int ano;
    private String placa;
    private int quilometragem;
    private double valor_unitario;
    private boolean disponibilidade;
    private Versao versao;
    private Direcao direcao;
    private Cor cor;
    private List<Compra> compras;
    private List<Venda> vendas;

    public Unidade(int id, int ano, String placa, int quilometragem, double valor_unitario, Versao versao, Direcao direcao, Cor cor) {
        if (id < 1) {
            throw new RuntimeException("O id deve ser positivo");
        }
        this.id = id;
        setAno(ano);
        setPlaca(placa);
        setQuilometragem(quilometragem);
        setValor_unitario(valor_unitario);
        this.versao = versao;
        this.direcao = direcao;
        this.cor = cor;
        disponibilidade = true;
        versao.addUnidade(this);
    }

    public Unidade(int ano, String placa, int quilometragem, double valor_unitario, Versao versao, Direcao direcao, Cor cor) {
        setAno(ano);
        setPlaca(placa);
        setQuilometragem(quilometragem);
        setValor_unitario(valor_unitario);
        this.versao = versao;
        this.direcao = direcao;
        this.cor = cor;
        disponibilidade = true;
        versao.addUnidade(this);
    }

    public void addVenda(Venda venda){
        vendas.add(venda);
    }

    public void addCompra(Compra compra){
        compras.add(compra);
    }

    public int getId() {
        return id;
    }

    public int getAno() {
        return ano;
    }

    public String getPlaca() {
        return placa;
    }

    public int getQuilometragem() {
        return quilometragem;
    }

    public double getValor_unitario() {
        return valor_unitario;
    }

    public boolean getDisponibilidade() {
        return disponibilidade;
    }

    private void setAno(int ano){
        int anoAtual = LocalDate.now().getYear();
        int anoLancamentoVersao = versao.getLancamento().getYear();

        if (ano < 1950 || ano > anoAtual || ano < anoLancamentoVersao) {
            throw new RuntimeException("O ano de fabricação de um carro não pode ser posterior ao ano atual e nem ao ano de lançamento de sua versao. A loja também não aceita carros que foram fabricadas antes do ano de 1950.");
        }
    }

    public void setPlaca(String placa) {
        String regex = "[A-Z]{3}[0-9][A-Z][0-9]{2}|[A-Z]{3}[0-9]{4}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(placa);
        boolean plc = matcher.matches();

        if (plc = false) {
            throw new RuntimeException("A placa deve possui o formato AAA0A00 ou AAA0000");
        }
        this.placa = placa;
    }

    public void setQuilometragem(int quilometragem) {
        if (quilometragem < 0) {
            throw new RuntimeException("A quilometragem deve ser maior que zero");
        }
        this.quilometragem = quilometragem;
    }

    public void setValor_unitario(double valor_unitario) {
        if (valor_unitario < 10000 || valor_unitario > 9999999.99) {
            throw new RuntimeException("O valor do carro deve ser maior ou igual a 10000 e menor ou igual a 9999999.");
        }
        this.valor_unitario = valor_unitario;
    }

    public void setDisponibilidade() {
        if (disponibilidade = true) {
            disponibilidade = false;
        }
        disponibilidade = true;
    }

    public void setDirecao(Direcao direcao) {
        this.direcao = direcao;
    }

    public void setCor(Cor cor) {
        this.cor = cor;
    }
}
