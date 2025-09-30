import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class usuarioDAO {  // nome da classe corrigido

    public void cadastrar(Usuario usuario) {
        String sql = "INSERT INTO usuarios (nome_usuario, cpf, email, login, senha) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getCpf());
            stmt.setString(3, usuario.getEmail());
            stmt.setString(4, usuario.getLogin());
            stmt.setString(5, usuario.getSenha()); // ideal usar hash

            stmt.executeUpdate();
            System.out.println("✅ Usuário cadastrado com sucesso!");

        } catch (SQLException e) {
            System.out.println("❌ Erro ao cadastrar usuário: " + e.getMessage());
        }
    }

    // Novo método para login
    public Usuario login(String login, String senha) {
        String sql = "SELECT * FROM usuarios WHERE login = ? AND senha = ?";

        try (Connection conn = conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, login);
            stmt.setString(2, senha);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Usuario u = new Usuario();
                u.setNome(rs.getString("nome_usuario"));
                u.setCpf(rs.getString("cpf"));
                u.setEmail(rs.getString("email"));
                u.setLogin(rs.getString("login"));
                u.setSenha(rs.getString("senha"));
                return u;
            }

        } catch (SQLException e) {
            System.out.println("❌ Erro no login: " + e.getMessage());
        }

        return null; // não encontrou
    }
}
