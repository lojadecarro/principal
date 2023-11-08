package Tables;

public class CategoriaCarro {
    private int id;
    private String nome;

    public CategoriaCarro(int id, String nome) {
        Verificacoes.verificarParametroNull(id, nome);
        this.id = id;
        setNome(nome);
    }

    public CategoriaCarro(String nome) {
        Verificacoes.verificarParametroNull(nome);
        setNome(nome);
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome.length() > 20 || nome.length() < 2 || !nome.matches("[\\p{L}]+")) {
            throw new RuntimeException("Uma categoria de carros deve ter somente uma palavra com um tamanho entre 2 e 20.");
        
        }
        nome = nome.substring(0, 1).toUpperCase() + nome.substring(1).toLowerCase();
        this.nome = nome;
    }

    public void setId(int id) {
        this.id = id;
    }
}
