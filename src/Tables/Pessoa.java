package Tables;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public abstract class Pessoa {
    protected int id;
    protected String nome;
    protected String email;
    protected String contato;
    protected String cpf;
    protected LocalDate data_nascimento;
    protected Endereco endereco;
    protected LocalDate data_registro;

    public Pessoa(int id, String nome, String email, String contato, String cpf, LocalDate data_nascimento, Endereco endereco) {
        Verificacoes.verificarParametroNull(id, nome, email, contato, cpf, data_nascimento, endereco);
        this.id = id;
        setNome(nome);
        setEmail(email);
        setContato(contato);
        setCPF(cpf);
        verificarIdade(data_nascimento);
        setEndereco(null);
        data_registro = LocalDate.now();
    }

    public Pessoa(String nome, String email, String contato, String cpf, LocalDate data_nascimento, Endereco endereco) {
        Verificacoes.verificarParametroNull(nome, email, contato, cpf, data_nascimento, endereco);
        setNome(nome);
        setEmail(email);
        setContato(contato);
        setCPF(cpf);
        verificarIdade(data_nascimento);
        setEndereco(null);
        data_registro = LocalDate.now();
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getContato() {
        return contato;
    }

    public long getIdade() {
        return ChronoUnit.YEARS.between(data_nascimento, LocalDate.now());
    }

    public String getCpf() {
        return cpf;
    }

    public void setNome(String nome) {
        String[] palavras = nome.split(" ");

        if (palavras.length < 2 || nome.length() > 100) {
            throw new RuntimeException("Um nome completo deve possuir nome e no mínimo um sobrenome, além de não poder ter um tamanho maior que 100.");
        }

        StringBuilder nomeFormatado = new StringBuilder();

        for (String palavra : palavras) {
            if (!palavra.matches("^\\p{L}çÇ+")) {
                throw new RuntimeException("Um nome deve possuir apenas letras.");
            }

            if (!palavra.isEmpty()) {
                palavra = palavra.substring(0, 1).toUpperCase() + palavra.substring(1).toLowerCase();
                nomeFormatado.append(palavra).append(" ");
            }
        }

        nome = nomeFormatado.toString().replaceAll("\\s+", " ").trim();
        this.nome = nome;
    }

    private boolean verificarE(String email){
        for (int i = 0; i < email.length() - 1; i++) {
            char proximoC = email.charAt(i+1);

            if ((email.charAt(i) == '.' || email.charAt(i) == '_' || email.charAt(i) == '-') && email.charAt(i) == proximoC) {
                return false;
            }
        }
        return true;
    }

    public void setEmail(String email) {
        if (!email.matches("^[a-zA-Z\\d][a-zA-Z\\d.-_]+[a-zA-Z\\d]@[a-zA-Z\\d][a-zA-Z\\d-]+[a-zA-Z\\d].[a-z]{2,7}$") || email.length() > 256 ||  verificarE(email) == false) {
            throw new RuntimeException("O email não está num formato adequado.");
        }
        this.email = email;
    }

    public void setContato(String contato) {
        if (!contato.matches("^\\d{2}9\\d{8}$")) {
            throw new RuntimeException("O número de contato deve possuir o DDD e em seguida o número de contato, sem espaços.");
        }
        contato = "(" + contato.substring(0, 2) + ")" + " " + contato.substring(2, 7) + "-" + contato.substring(7);
        this.contato = contato;
    }

    public void setEndereco(Endereco endereco){
       this.endereco = endereco;
    }

    private void setCPF(String cpf){
        if (!cpf.matches("^\\d{11}$")) {
            throw new RuntimeException("O cpf deve ser completo e sem \".\" ou \"-\". ");
        }
        this.cpf = cpf.substring(0, 3) + "." + cpf.substring(3, 6) + "." + cpf.substring(6, 9) + "-" + cpf.substring(9);
    }

    private void verificarIdade(LocalDate data_nascimento){
        if (getIdade() < 18) {
            throw new RuntimeException("Somente maiores de 18 anos podem se registrar.");
        }
        this.data_nascimento = data_nascimento;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getData_nascimento() {
        return data_nascimento;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public LocalDate getData_registro() {
        return data_registro;
    }

    public void data_registroParaResultSet(LocalDate data_registro){
        this.data_registro = data_registro;
    }
}
