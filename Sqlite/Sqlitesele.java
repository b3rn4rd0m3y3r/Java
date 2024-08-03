import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author sqlitetutorial.net
 */
public class Sqlitesele {
     /**
     * Connect to database
     */
    public static void connect() {
        Connection conn = null;
        try {
            // URL de conexão
            String url = "jdbc:sqlite:Banco.db";
            // Conexão ao Banco de Dados
            conn = DriverManager.getConnection(url);
            System.out.println("Conectou.");
			// Frase SQL
			var sql = "SELECT Id, Descri FROM Teste";
			// Cria cache para o SCHEMA da tabela
			var stmt = conn.createStatement();
			// Cria área para o recordset
			var rs = stmt.executeQuery(sql);
            while (rs.next()) {
                System.out.printf("|%5s|%-30s|\n", rs.getInt("Id"),rs.getString("Descri"));
				}           
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