package Tables;

import java.time.LocalDate;
import java.util.List;

public class Unidade {
    private int id;
    private short ano;
    private String placa;
    private int quilometragem;
    private EstadoConservacao estado_conservacao;
    private double valor_unitario;
    private boolean disponibilidade;
    private Versao versao;
    private Transmissao transmissao;
    private Cor cor;
    private List<Compra> compras;
    private List<Venda> vendas;

    public Unidade(int id, short ano, String placa, int quilometragem, EstadoConservacao estado_conservacao, double valor_unitario, Versao versao, Transmissao transmissao, Cor cor, Boolean disponibilidade) {
        Verificacoes.verificarParametroNull(id, ano, placa, quilometragem, estado_conservacao, valor_unitario, versao, transmissao, cor, disponibilidade);
        this.id = id;
        setAno(ano);
        setPlaca(placa);
        setQuilometragem(quilometragem);
        setValor_unitario(valor_unitario);
        this.versao = versao;
        setTransmissao(transmissao);
        setCor(cor);
        versao.addUnidade(this);
        this.disponibilidade = disponibilidade;
    }

    public Unidade(short ano, String placa, int quilometragem, EstadoConservacao estado_conservacao, double valor_unitario, Versao versao, Transmissao transmissao, Cor cor) {
        Verificacoes.verificarParametroNull(id, ano, placa, quilometragem, estado_conservacao, valor_unitario, versao, transmissao, cor);
        setAno(ano);
        setPlaca(placa);
        setQuilometragem(quilometragem);
        setValor_unitario(valor_unitario);
        this.versao = versao;
        setTransmissao(transmissao);
        setCor(cor);
        disponibilidade = false;
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

    public Short getAno() {
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

    private void setAno(short ano){
        short anoAtual = (short) LocalDate.now().getYear();
        short anoLancamentoVersao = (short) versao.getLancamento().getYear();

        if (ano > anoAtual || ano < anoLancamentoVersao) {
            throw new RuntimeException("O ano de fabricação de um carro não pode ser posterior ao ano atual e nem ao ano de lançamento de sua versao.");
        }
    }

    public void setPlaca(String placa) {
        if (!placa.matches("^[A-Z]{3}\\d[A-Z]\\d{2}|[A-Z]{3}\\d{4}$")) {
            throw new RuntimeException("A placa deve possui o formato AAA0A00 ou AAA0000");
        }

        if (placa.matches("^[A-Z]{3}\\d{4}$")) {
            placa = placa.substring(0, 3) + "-" + placa.substring(3);
        }
        this.placa = placa;
    }

    public void setQuilometragem(int quilometragem) {
        if (quilometragem < 0) {
            throw new RuntimeException("A quilometragem deve ser maior que zero");
        }

        if (quilometragem < this.quilometragem) {
            throw new RuntimeException("A quilometragem não pode ser menor que a antiga quilometragem do carro. verifique o odômetro do carro caso ele tenha, se mesmo assim a quilometragem for menor que a antiga, possivelmente houve uma manipulação na quilometragem do carro");

        }
        this.quilometragem = quilometragem;
    }

    public void setValor_unitario(double valor_unitario) {
        if (valor_unitario < 10000 || valor_unitario > 9999999.99 || Double.toString(valor_unitario).matches("^\\d+\\.\\d{1,2}$")) {
            throw new RuntimeException("O valor do carro deve ser maior ou igual a 10000 e menor ou igual a 9999999. insira o valor com os centavos.");
        }
        this.valor_unitario = valor_unitario;
    }

    public void setDisponibilidade() {
        disponibilidade = !disponibilidade;
    }

    public void setTransmissao(Transmissao transmissao) {
        this.transmissao = transmissao;
    }

    public void setCor(Cor cor) {
        this.cor = cor;
    }

    public EstadoConservacao getEstado_conservacao() {
        return estado_conservacao;
    }

    public Versao getVersao() {
        return versao;
    }

    public Transmissao getTransmissao() {
        return transmissao;
    }

    public Cor getCor() {
        return cor;
    }

    public void setEstado_conservacao(EstadoConservacao estado_conservacao) {
        this.estado_conservacao = estado_conservacao;
    }

    public void setId(int id) {
        this.id = id;
    }
}
