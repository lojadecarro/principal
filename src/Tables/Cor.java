package Tables;

import java.util.List;

public class Cor {
    private int id;
    private CategoriaCor categoriaCor;
    private String nome;
    private List<Unidade> unidades;

    public Cor(int id, CategoriaCor categoriaCor, String nome) {
        this.id = id;
        this.categoriaCor = categoriaCor;
        this.nome = nome;
        categoriaCor.addCor(this);
    }

    public Cor(CategoriaCor categoriaCor, String nome) {
        this.categoriaCor = categoriaCor;
        this.nome = nome;
        categoriaCor.addCor(this);
    }

    //Esse construtor foi criado apenas para a utilização
    // do método ResultSetToCor na classe CorDAO
    public Cor(int id, int idCategoriaCor, String nome) {
        this.id = id;
        this.categoriaCor.setId(idCategoriaCor);
        this.nome = nome;
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public CategoriaCor getCategoriaCor() {
        return categoriaCor;
    }

    public void setCategoriaCor(CategoriaCor categoriaCor) {
        this.categoriaCor = categoriaCor;
    }

    public int getIdCategoriaCor(){
        return categoriaCor.getId();
    }
    
}
