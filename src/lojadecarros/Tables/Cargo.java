package lojadecarros.Tables;

import java.util.List;

public class Cargo {
    private int id;
    private String nome;
    private List<Funcionario> funcionarios;

    public Cargo(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Cargo(String nome) {
        this.nome = nome;
    }
    
    public void addFuncionario(Funcionario funcionario){
        funcionarios.add(funcionario);
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
