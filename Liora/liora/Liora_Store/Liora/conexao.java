import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexao {
    private static final String URL = "jdbc:mysql://localhost:3306/sakila?useSSL=false&serverTimezone=UTC\r\n" + //;
    private static final String USER = "root";      // seu usuário do MySQL
    private static final String PASSWORD = "Ianrocha27*"; // sua senha do MySQL

    public static Connection conectar() {
        try {
            // Carrega o driver do MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("✅ Conectado com sucesso!");
            return conn;
        } catch (ClassNotFoundException e) {
            System.out.println("❌ Driver MySQL não encontrado: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("❌ Erro na conexão: " + e.getMessage());
        }
        return null;
    }
}
