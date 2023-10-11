package LojaDeCarros.Tables;

import java.time.LocalTime;
import java.util.List;

public class Turno {
    private int id;
    private LocalTime inicio;
    private LocalTime fim;
    private List<Funcionario> funcionarios;

    public Turno(int id, LocalTime inicio, LocalTime fim) {
        this.id = id;
        this.inicio = inicio;
        this.fim = fim;
    }

    public Turno(LocalTime inicio, LocalTime fim) {
        this.inicio = inicio;
        this.fim = fim;
    }

    public void addFuncionario(Funcionario funcionario){
        funcionarios.add(funcionario);
    }

    public int getId() {
        return id;
    }

    public LocalTime getInicio() {
        return inicio;
    }

    public LocalTime getFim() {
        return fim;
    }

    public void setInicio(LocalTime inicio) {
        this.inicio = inicio;
    }

    public void setFim(LocalTime fim) {
        this.fim = fim;
    }
}
