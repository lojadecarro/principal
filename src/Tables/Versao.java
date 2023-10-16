package Tables;

import java.time.LocalDate;
import java.util.List;

public class Versao {
    private int id;
    private int nome;
    private LocalDate lancamento;
    private Modelo modelo;
    private List<Unidade> unidades;
  
    public Versao(int id, int nome, LocalDate lancamento, Modelo modelo) {
        this.id = id;
        this.nome = nome;
        setLancamento(lancamento);
        this.modelo = modelo;
        modelo.addVersao(this);
    }

    public Versao(int nome, LocalDate lancamento, Modelo modelo) {
        this.nome = nome;
        setLancamento(lancamento);
        this.modelo = modelo;
        modelo.addVersao(this);
    }

    public void addUnidade(Unidade unidade){
        unidades.add(unidade);
    }

    public int getId() {
        return id;
    }

    public int getNome() {
        return nome;
    }

    public void setNome(int nome) {
        this.nome = nome;
    }

    public LocalDate getLancamento() {
        return lancamento;
    }

    private void setLancamento(LocalDate lancamento){
        if (lancamento.isAfter(LocalDate.now()) || lancamento.getYear() < 1950) {
            throw new RuntimeException("A data de lançamento da versão não pode ser posterior a data atual e o ano não pode ser menor que 1950.");
        }
        this.lancamento = lancamento;
    }
}
