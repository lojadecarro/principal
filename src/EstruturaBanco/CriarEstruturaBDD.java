package EstruturaBanco;

import java.sql.SQLException;

/*
Esta classe prepara o workbench para criar a estrutura de banco da loja de carros.
Cria as tabelas, views e inserts basicos.  
*/

public class CriarEstruturaBDD {
    public static void main(String[] args) throws SQLException {
        
        RemoverTodasTabelas removerTabelas = new RemoverTodasTabelas();
        removerTabelas.remover();

        CriarTabelas criarTabelas = new CriarTabelas();
        criarTabelas.criar();

        RemoverTodasViews removerViews = new RemoverTodasViews();
        removerViews.remover();
        
        CriarViews criarViews = new CriarViews();
        criarViews.criar();

        CriarInsertsBasicos criarInsertsBasicos = new CriarInsertsBasicos();
        criarInsertsBasicos.criar();
    }
}
