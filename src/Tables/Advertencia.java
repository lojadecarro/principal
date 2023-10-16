package lojadecarros.Tables;

public class Advertencia {
    private int id;
    private byte gravidade;
    private Funcionario funcionario;
    private String motivo;

    public Advertencia(int id, byte gravidade, Funcionario funcionario, String motivo) {
        this.id = id;
        this.gravidade = gravidade;
        this.funcionario = funcionario;
        this.motivo = motivo;
        funcionario.addAdvertencia(this);
    }

    public Advertencia(byte gravidade, Funcionario funcionario, String motivo) {
        this.gravidade = gravidade;
        this.funcionario = funcionario;
        this.motivo = motivo;
        funcionario.addAdvertencia(this);
    }

    public int getId() {
        return id;
    }

    public byte getGravidade() {
        return gravidade;
    }

    public String getMotivo() {
        return motivo;
    }
}
