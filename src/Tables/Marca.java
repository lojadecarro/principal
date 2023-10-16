package lojadecarros.Tables;

import java.util.List;

public class Marca {
    private int id;
    private String nome;
    private List<Modelo> modelos;

    public Marca(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Marca(String nome) {
        this.nome = nome;
    }

    public void addModelo(Modelo modelo){
        modelos.add(modelo);
    }

    public int getId() {
        return id;
    }

    public void setId(int id){
        this.id = id;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Marca [id=" + id + ", nome=" + nome + ", modelos=" + modelos + "]";
    }
    
}
