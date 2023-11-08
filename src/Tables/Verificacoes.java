package Tables;

public class Verificacoes {
    public static void verificarParametroNull(Object... parametros){
        for (Object parametro : parametros) {
            if (parametro == null) {
                throw new RuntimeException("Os valores não podem ser nulos.");
            }
        }
    }
}
