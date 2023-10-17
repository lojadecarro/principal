package Tables;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

public abstract class Pessoa {
    protected int id;
    protected String nome;
    protected String email;
    protected int contato;
    protected int cpf;
    protected LocalDate data_nascimento;
    protected String endereco;
    protected String complemento;
    protected LocalDateTime data_registro;

    public Pessoa(int id, String nome, String email, int contato, int cpf, LocalDate data_nascimento, String endereco) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.contato = contato;
        this.cpf = cpf;
        this.data_nascimento = data_nascimento;
        this.endereco = endereco;
        data_registro = LocalDateTime.now();
    }

    public Pessoa(String nome, String email, int contato, int cpf, LocalDate data_nascimento, String endereco) {
        this.nome = nome;
        this.email = email;
        this.contato = contato;
        this.cpf = cpf;
        this.data_nascimento = data_nascimento;
        this.endereco = endereco;
        data_registro = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public int getContato() {
        return contato;
    }

    public LocalDate getData_nascimento() {
        return data_nascimento;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getComplemento() {
        return complemento;
    }

    public int getCpf() {
        return cpf;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setContato(int contato) {
        this.contato = contato;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }
}
