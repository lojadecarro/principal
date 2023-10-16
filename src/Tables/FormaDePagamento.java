package Tables;

public class FormaDePagamento {
    private int id;
    private String nome;
    
    public FormaDePagamento(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public FormaDePagamento(String nome) {
        this.nome = nome;
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
