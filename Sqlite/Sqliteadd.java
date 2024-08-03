import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author sqlitetutorial.net
 */
public class Sqliteadd {
     /**
     * Connect to a sample database
     */
    public static void connect() {
        Connection conn = null;
        try {
            // URL de acesso
            String url = "jdbc:sqlite:Banco.db";
            // Conexão ao Banco
            conn = DriverManager.getConnection(url);
			// Configuração de atualização NÃO automática
			conn.setAutoCommit(false);
            System.out.println("Conexão ao SQLite estabelecida.");
			// Declaração do statement
			var stmt = conn.createStatement();
			// Sentença SQL
			String addqry = "insert into Teste (Id,Descri) values (null, 'eeeee eeeee eeeee eeeee')";
			// Execução
			stmt.executeUpdate(addqry);
			stmt.close();
			conn.commit();
			conn.close();			
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
					}
				} catch (SQLException ex) {
                System.out.println(ex.getMessage());
				}
			}
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        connect();
    }
}