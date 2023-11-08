package Tables;

public class EstadoConservacao {
    private int id;
    private String nome;
    
    public EstadoConservacao(int id, String nome) {
        Verificacoes.verificarParametroNull(id, nome);
        this.id = id;
        setNome(nome);
    }

    public EstadoConservacao(String nome) {
        Verificacoes.verificarParametroNull(id, nome);
        setNome(nome);
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
        if (!nome.matches("^[a-zA-Z\\-]+$") || nome.length() > 20) {
            throw new RuntimeException("O estado de conservação não pode passar de 20 caracteres, podendo ter somente letras e \"-\".");

        }
        nome = nome.substring(0, 1).toUpperCase() + nome.substring(1).toLowerCase();
        this.nome = nome;
    }

    
}
