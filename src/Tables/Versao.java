package Tables;

import java.time.LocalDate;
import java.util.List;

public class Versao {
    private int id;
    private String nome;
    private LocalDate lancamento;
    private Modelo modelo;
    private CategoriaCarro categoriaCarro;
    private List<Unidade> unidades;
  
    public Versao(int id, String nome, LocalDate lancamento, Modelo modelo, CategoriaCarro categoriaCarro) {
        Verificacoes.verificarParametroNull(id, nome, lancamento, modelo, categoriaCarro);
        this.id = id;
        setNome(nome);
        setLancamento(lancamento);
        this.modelo = modelo;
        this.categoriaCarro = categoriaCarro;
        modelo.addVersao(this);
    }

    public Versao(String nome, LocalDate lancamento, Modelo modelo, CategoriaCarro categoriaCarro) {
        Verificacoes.verificarParametroNull(nome, lancamento, modelo, categoriaCarro);
        setNome(nome);
        setLancamento(lancamento);
        this.modelo = modelo;
        this.categoriaCarro = categoriaCarro;
        modelo.addVersao(this);
    }

    public void addUnidade(Unidade unidade){
        unidades.add(unidade);
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Modelo getModelo() {
        return modelo;
    }

    public CategoriaCarro getCategoriaCarro() {
        return categoriaCarro;
    }

    public void setNome(String nome) {
        String[] palavras = nome.split(" ");
        StringBuilder nomeFormatadodo = new StringBuilder();

        if (nome.length() > 30 || nome.length() < 2 || nome.matches("^[a-zA-Z\\d ]+$")) {
            throw new RuntimeException("o nome do modelo pode possuir apenas letras e digitos e deve possuir um tamanho entre 2 e 30.");
        }
        
        for (String palavra : palavras) {
            if (!palavra.isEmpty()) {
                palavra = palavra.substring(0, 1).toUpperCase() + palavra.substring(1).toLowerCase();
                nomeFormatadodo.append(palavra).append(" ");
            }
        }

        nome = nomeFormatadodo.toString().replaceAll("\\s+", " ").trim();
        this.nome = nome;
        this.nome = nome;
    }

    public LocalDate getLancamento() {
        return lancamento;
    }

    private void setLancamento(LocalDate lancamento){
        if (lancamento.isAfter(LocalDate.now()) || lancamento.getYear() < 1950) {
            throw new RuntimeException("A data de lançamento da versão não pode ser posterior a data atual e o ano não pode ter sido antes que 1950.");
        }

        this.lancamento = lancamento;
    }

    public void setId(int id) {
        this.id = id;
    }
}
