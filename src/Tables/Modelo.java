package Tables;

import java.util.List;

public class Modelo {
    private int id;
    private String nome;
    private Marca marca;
    private CategoriaModelo categoria_modelo;
    private List<Versao> versoes;
 
    public Modelo(int id, Marca marca, CategoriaModelo categoriaModelo, String nome) {
        this.id = id;
        this.marca = marca;
        this.categoria_modelo = categoriaModelo;
        this.nome = nome;
        marca.addModelo(this);
        categoriaModelo.addModelo(this);
    }

    public Modelo(Marca marca, CategoriaModelo categoriaModelo, String nome) {
        this.marca = marca;
        this.categoria_modelo = categoriaModelo;
        this.nome = nome;
        marca.addModelo(this);
        categoriaModelo.addModelo(this);
    }

    //Construtor criado para o ResultSet do ModeloDAO
    public Modelo(int id, int idmarca, int idcategoriaModelo, String nome) {
        this.id = id;
        this.marca.setId(idmarca);
        this.categoria_modelo.setId(idcategoriaModelo);
        this.nome = nome;
    }

    public void addVersao(Versao versao){
        versoes.add(versao);
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

    public int getIdMarca(){
        return marca.getId();
    }
    public int getIdCategoriaModelo(){
        return categoria_modelo.getId();
    }
}
