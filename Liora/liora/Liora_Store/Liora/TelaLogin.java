import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class TelaLogin extends JFrame {

    private JTextField campoLogin;
    private JPasswordField campoSenha;
    private JButton botaoEntrar;
    private JButton botaoCadastrar;

    public TelaLogin() {
        super("Tela de Login");

        setLayout(new GridLayout(3, 2, 10, 10));

        add(new JLabel("Login:"));
        campoLogin = new JTextField();
        add(campoLogin);

        add(new JLabel("Senha:"));
        campoSenha = new JPasswordField();
        add(campoSenha);

        botaoEntrar = new JButton("Entrar");
        botaoCadastrar = new JButton("Cadastrar");

        add(botaoEntrar);
        add(botaoCadastrar);

        botaoEntrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String login = campoLogin.getText();
                String senha = new String(campoSenha.getPassword());

                // Valida o login com o banco de dados
                usuarioDAO dao = new usuarioDAO();
                Usuario usuario = dao.login(login, senha);

                if (usuario != null) {
                    JOptionPane.showMessageDialog(null, "✅ Bem-vindo, " + usuario.getNome());
                    // Aqui você pode abrir a tela principal do sistema
                } else {
                    JOptionPane.showMessageDialog(null, "❌ Login ou senha inválidos!");
                }
            }
        });

        botaoCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Abre a tela de cadastro
                new TelaCadastroUsuario();
            }
        });

        // Configurações da janela
        setSize(350, 150);
        setLocationRelativeTo(null); // centralizar
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new TelaLogin();
    }
}
