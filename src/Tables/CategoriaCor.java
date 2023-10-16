package Tables;

import java.util.List;

public class CategoriaCor {
    private int id;
    private String nome;
    private List<Cor> cores;

    public CategoriaCor(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public CategoriaCor(String nome) {
        this.nome = nome;
    }

    public void addCor(Cor cor){
        cores.add(cor);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
