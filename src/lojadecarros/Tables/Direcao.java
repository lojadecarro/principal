package lojadecarros.Tables;

import java.util.List;

public class Direcao {
    private int id;
    private String tipo;
    private List<Unidade> unidades;
    
    public Direcao(int id, String tipo) {
        this.id = id;
        this.tipo = tipo;
    }

    public Direcao(String tipo) {
        this.tipo = tipo;
    }

    public void addUnidade(Unidade unidade){
        unidades.add(unidade);
    }

    public int getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
