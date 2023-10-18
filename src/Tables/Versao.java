package Tables;

import java.time.LocalDate;
import java.util.List;

public class Versao {
    private int id;
    private int nome;
    private LocalDate lancamento;
    private Modelo modelo;
    private List<Unidade> unidades;
  
    public Versao(int id, Modelo modelo, int nome, LocalDate lancamento) {
        this.id = id;
        this.modelo = modelo;
        this.nome = nome;
        setLancamento(lancamento);
        modelo.addVersao(this);
    }

    public Versao(int nome, Modelo modelo, LocalDate lancamento) {
        this.nome = nome;
        this.modelo = modelo;
        setLancamento(lancamento);
        modelo.addVersao(this);
    }

    public Versao(int nome, int idmodelo, LocalDate lancamento) {
        this.nome = nome;
        this.modelo.setId(idmodelo);
        setLancamento(lancamento);
        modelo.addVersao(this);
    }

    public void addUnidade(Unidade unidade){
        unidades.add(unidade);
    }

    public int getId() {
        return id;
    }

    public void setId(int id){
        this.id = id;
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

    public int getIdModelo(){
        return this.modelo.getId();
    }

    private void setLancamento(LocalDate lancamento){
        if (lancamento.isAfter(LocalDate.now()) || lancamento.getYear() < 1950) {
            throw new RuntimeException("A data de lançamento da versão não pode ser posterior a data atual e o ano não pode ser menor que 1950.");
        }
        this.lancamento = lancamento;
    }
}
