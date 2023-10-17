package Tables;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Pessoa {
    protected int id;
    protected String nome;
    protected String email;
    protected String contato;
    protected String cpf;
    protected LocalDate data_nascimento;
    protected String endereco;
    protected String complemento;
    protected LocalDateTime data_registro;

    public Pessoa(int id, String nome, String email, String contato, String cpf, LocalDate data_nascimento, String endereco) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.contato = contato;
        this.cpf = cpf;
        this.data_nascimento = data_nascimento;
        this.endereco = endereco;
        data_registro = LocalDateTime.now();
    }

    public Pessoa(String nome, String email, String contato, String cpf, LocalDate data_nascimento, String endereco) {
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

    public String getContato() {
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

    public String getCpf() {
        return cpf;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }
}
