package Tables;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class Turno {
    private int id;
    private LocalTime inicio;
    private LocalTime fim;
    private short duracaoMinutos;

    public Turno(int id, LocalTime inicio, LocalTime fim) {
        Verificacoes.verificarParametroNull(id, inicio, fim);
        this.id = id;
        verificarInicioFim(inicio, fim);
        this.inicio = inicio;
        this.fim = fim;
        duracaoMinutos = calcularDuracaoMinutos(inicio, fim);
    }

    public Turno(LocalTime inicio, LocalTime fim) {
        Verificacoes.verificarParametroNull(inicio, fim);
        verificarInicioFim(inicio, fim);
        this.inicio = inicio;
        this.fim = fim;
        duracaoMinutos = calcularDuracaoMinutos(inicio, fim);
    }

    public int getId() {
        return id;
    }

    public LocalTime getInicio() {
        return inicio;
    }

    public short getDuracaoMinutos() {
        return duracaoMinutos;
    }

    public LocalTime getFim() {
        return fim;
    }

    private void verificarInicioFim(LocalTime inicio, LocalTime fim){
        if (calcularDuracaoMinutos(inicio, fim) > 10*60 || calcularDuracaoMinutos(inicio, fim) < 5*60) {
            throw new RuntimeException("A duração de um turno tem que ter entre 5 e 10 horas.");
        }
    }

    private short calcularDuracaoMinutos(LocalTime inicio, LocalTime fim){
        if (fim.isBefore(inicio)) 
            return (short) (24*60 - ChronoUnit.MINUTES.between(fim, inicio)); 
            
            return (short) (ChronoUnit.MINUTES.between(inicio, fim)); 
    }

    public void setId(int id) {
        this.id = id;
    }
}
