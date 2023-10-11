package lojadecarros.Tables;

import java.util.List;

public class Cor {
    private int id;
    private String nome;
    private CategoriaCor categoriaCor;
    private List<Unidade> unidades;

    public Cor(int id, String nome, CategoriaCor categoriaCor) {
        this.id = id;
        this.nome = nome;
        this.categoriaCor = categoriaCor;
        categoriaCor.addCor(this);
    }

    public Cor(String nome, CategoriaCor categoriaCor) {
        this.nome = nome;
        this.categoriaCor = categoriaCor;
        categoriaCor.addCor(this);
    }

    public void addUnidade(Unidade unidade){
        unidades.add(unidade);
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
