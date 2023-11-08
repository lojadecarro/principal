package Tables;

public class Transmissao {
    private int id;
    private String tipo;
    
    public Transmissao(int id, String tipo) {
        Verificacoes.verificarParametroNull(id, tipo);
        this.id = id;
        setTipo(tipo);
    }

    public Transmissao(String tipo) {
        Verificacoes.verificarParametroNull(tipo);
        setTipo(tipo);
    }

    public int getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        String[] palavras = tipo.split(" ");
        StringBuilder nomeFormatadodo = new StringBuilder();

        if (tipo.length() > 40 || tipo.length() < 4 || !tipo.matches("[a-zA-Z ]+")) {
            throw new RuntimeException("Um tipo de transmissao deve ter apenas letras sem acentos e possuir um tamanho entre 4 e 40.");
        }
        
        for (String palavra : palavras) {
            if (!palavra.isEmpty()) {
                palavra = palavra.substring(0, 1).toUpperCase() + palavra.substring(1).toLowerCase();
                nomeFormatadodo.append(palavra).append(" ");
            }
        }

        tipo = nomeFormatadodo.toString().replaceAll("\\s+", " ").trim();
        this.tipo = tipo;
    }

    public void setId(int id) {
        this.id = id;
    }
}
