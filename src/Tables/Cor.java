package Tables;

public class Cor {
    private int id;
    private String nome;

    public Cor(int id, String nome) {
        Verificacoes.verificarParametroNull(id, nome);
        this.id = id;
        setNome(nome);
    }

    public Cor(String nome) {
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
        String[] palavras = nome.split(" ");
        StringBuilder nomeFormatadodo = new StringBuilder();

        if (nome.length() > 30 || nome.length() < 3 || !nome.matches("[a-zA-Z ]+")) {
            throw new RuntimeException("Uma cor deve ter apenas letras sem acentos e possuir um tamanho entre 2 e 30.");
        }
        
        for (String palavra : palavras) {
            if (!palavra.isEmpty()) {
                palavra = palavra.substring(0, 1).toUpperCase() + palavra.substring(1).toLowerCase();
                nomeFormatadodo.append(palavra).append(" ");
            }
        }

        nome = nomeFormatadodo.toString().replaceAll("\\s+", " ").trim();
        this.nome = nome;
    }

    public void setId(int id) {
        this.id = id;
    }
}
