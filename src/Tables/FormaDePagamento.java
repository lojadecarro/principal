package Tables;

public class FormaDePagamento {
    private int id;
    private String nome;
    
    public FormaDePagamento(int id, String nome) {
        Verificacoes.verificarParametroNull(id, nome);
        this.id = id;
        setNome(nome);
    }

    public FormaDePagamento(String nome) {
        Verificacoes.verificarParametroNull(id, nome);
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

        if (nome.length() > 30 || nome.length() < 4 || !nome.matches("[a-zA-Z ]+")) {
            throw new RuntimeException("Uma forma de pagamento deve ter apenas letras sem acentos e possuir um tamanho entre 4 e 30.");
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
