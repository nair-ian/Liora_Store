import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class TelaCadastroUsuario extends JFrame {

    private JTextField campoNome;
    private JTextField campoCPF;
    private JTextField campoTelefone;
    private JTextField campoEmail;
    private JTextField campoLogin;
    private JPasswordField campoSenha;

    private JButton botaoSalvar;
    private JButton botaoLimpar;

    public TelaCadastroUsuario() {
        super("Cadastro de Usuário");

        setLayout(new GridLayout(7, 2, 10, 10));

        add(new JLabel("Nome:"));
        campoNome = new JTextField();
        add(campoNome);

        add(new JLabel("CPF:"));
        campoCPF = new JTextField();
        add(campoCPF);

        add(new JLabel("Telefone:"));
        campoTelefone = new JTextField();
        add(campoTelefone);

        add(new JLabel("E-mail:"));
        campoEmail = new JTextField();
        add(campoEmail);

        add(new JLabel("Login:"));
        campoLogin = new JTextField();
        add(campoLogin);

        add(new JLabel("Senha:"));
        campoSenha = new JPasswordField();
        add(campoSenha);

        botaoSalvar = new JButton("Salvar");
        botaoLimpar = new JButton("Limpar");

        add(botaoSalvar);
        add(botaoLimpar);

        botaoSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = campoNome.getText();
                String cpf = campoCPF.getText();
                String telefone = campoTelefone.getText();
                String email = campoEmail.getText();
                String login = campoLogin.getText();
                String senha = new String(campoSenha.getPassword());

                JOptionPane.showMessageDialog(null,
                        "Usuário cadastrado com sucesso!\n\n" +
                                "Nome: " + nome +
                                "\nCPF: " + cpf +
                                "\nTelefone: " + telefone +
                                "\nE-mail: " + email +
                                "\nLogin: " + login);

                // Fecha a tela de cadastro
                dispose();

                // Abre a tela de login
                new TelaLogin();
            }
        });

        botaoLimpar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                campoNome.setText("");
                campoCPF.setText("");
                campoTelefone.setText("");
                campoEmail.setText("");
                campoLogin.setText("");
                campoSenha.setText("");
            }
        });

        // Configurações da janela
        setSize(400, 300);
        setLocationRelativeTo(null); // centralizar na tela
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Fecha só esta janela
        setVisible(true);
    }

    // Remover o método main daqui (opcional)
}
