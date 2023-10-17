package Tables;

public class Advertencia {
    private int id;
    private Funcionario funcionario;
    private byte gravidade;
    private String motivo;

    public Advertencia(int id, Funcionario funcionario, byte gravidade, String motivo) {
        this.id = id;
        this.funcionario = funcionario;
        this.gravidade = gravidade;
        this.motivo = motivo;
        funcionario.addAdvertencia(this);
    }

    public Advertencia(Funcionario funcionario, byte gravidade, String motivo) {
        this.funcionario = funcionario;
        this.gravidade = gravidade;
        this.motivo = motivo;
        funcionario.addAdvertencia(this);
    }

    public Advertencia(int id, int idfuncionario, byte gravidade, String motivo) {
        this.id = id;
        this.funcionario.setId(idfuncionario);
        this.gravidade = gravidade;
        this.motivo = motivo;
        funcionario.addAdvertencia(this);
    }

    public int getId() {
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public byte getGravidade() {
        return gravidade;
    }

    public String getMotivo() {
        return motivo;
    }

    public int getIdFuncionario(){
        return funcionario.getId();
    }
}
