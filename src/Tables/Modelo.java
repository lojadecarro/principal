package lojadecarros.Tables;

import java.util.List;

public class Modelo {
    private int id;
    private String nome;
    private Marca marca;
    private CategoriaModelo categoria_modelo;
    private List<Versao> versoes;
 
    public Modelo(int id, String nome, Marca marca, CategoriaModelo categoriaModelo) {
        this.id = id;
        this.nome = nome;
        this.marca = marca;
        this.categoria_modelo = categoriaModelo;
        marca.addModelo(this);
        categoriaModelo.addModelo(this);
    }

    public Modelo(String nome, Marca marca, CategoriaModelo categoriaModelo) {
        this.nome = nome;
        this.marca = marca;
        this.categoria_modelo = categoriaModelo;
        marca.addModelo(this);
        categoriaModelo.addModelo(this);
    }

    public void addVersao(Versao versao){
        versoes.add(versao);
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
