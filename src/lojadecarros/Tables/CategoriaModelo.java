package lojadecarros.Tables;

import java.util.List;

public class CategoriaModelo {
    private int id;
    private int nome;
    private List<Modelo> modelos;

    public CategoriaModelo(int id, int nome) {
        this.id = id;
        this.nome = nome;
    }

    public CategoriaModelo(int nome) {
        this.nome = nome;
    }

    public void addModelo(Modelo modelo){
        modelos.add(modelo);
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
}
