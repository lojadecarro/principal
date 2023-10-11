package LojaDeCarros.Tables;

public class Escala {
    private int id;
    private byte dias_trabalho;
    private byte dias_folga;
    private String tipo;

    public Escala(int id, byte dias_trabalho, byte dias_folga) {
        this.id = id;
        this.dias_trabalho = dias_trabalho;
        this.dias_folga = dias_folga;
        setTipo();
    }

    public Escala(byte dias_trabalho, byte dias_folga) {
        this.dias_trabalho = dias_trabalho;
        this.dias_folga = dias_folga;
        setTipo();
    }

    public int getId() {
        return id;
    }

    public byte getDias_trabalho() {
        return dias_trabalho;
    }

    public byte getDias_folga() {
        return dias_folga;
    }

    public String getTipo() {
        return tipo;
    }

    public void setDias_trabalho(byte dias_trabalho) {
        this.dias_trabalho = dias_trabalho;
        setTipo();
    }

    public void setDias_folga(byte dias_folga) {
        this.dias_folga = dias_folga;
        setTipo();
    }

    public void setTipo() {
        tipo = dias_trabalho + "x" + dias_folga;
    }

}
