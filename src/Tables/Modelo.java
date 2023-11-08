package Tables;

import java.util.List;

public class Modelo {
    private int id;
    private String nome;
    private Marca marca;
    private List<Versao> versoes;
 
    public Modelo(int id, String nome, Marca marca) {
        Verificacoes.verificarParametroNull(id, nome, marca);
        this.id = id;
        setNome(nome);
        this.marca = marca;
        marca.addModelo(this);
    }

    public Modelo(String nome, Marca marca) {
        Verificacoes.verificarParametroNull(nome, marca);
        setNome(nome);
        this.marca = marca;
        marca.addModelo(this);
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

    public Marca getMarca() {
        return marca;
    }

    public void setNome(String nome) {
        String[] palavras = nome.split(" ");
        StringBuilder nomeFormatadodo = new StringBuilder();

        if (nome.length() > 40 || nome.length() < 2 || nome.matches("^[a-zA-Z\\d ]+$")) {
            throw new RuntimeException("o nome do modelo pode possuir apenas letras e digitos e deve possuir um tamanho entre 2 e 40.");
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
