import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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
        // Validação do CPF - apenas números e limite de 11 caracteres
        campoCPF.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) || campoCPF.getText().replaceAll("\\D", "").length() >= 11) {
                    e.consume();
                }
            }

            public void keyReleased(KeyEvent e) {
                String text = campoCPF.getText().replaceAll("\\D", "");
                if (text.length() <= 11) {
                    if (text.length() > 9) {
                        campoCPF.setText(text.replaceAll("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4"));
                    } else if (text.length() > 6) {
                        campoCPF.setText(text.replaceAll("(\\d{3})(\\d{3})(\\d{3})", "$1.$2.$3"));
                    } else if (text.length() > 3) {
                        campoCPF.setText(text.replaceAll("(\\d{3})(\\d{3})", "$1.$2"));
                    }
                }
            }
        });
        add(campoCPF);

        add(new JLabel("Telefone:"));
        campoTelefone = new JTextField();
        // Validação do Telefone - apenas números
        campoTelefone.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c)) {
                    e.consume();
                }
            }

            public void keyReleased(KeyEvent e) {
                String text = campoTelefone.getText().replaceAll("\\D", "");
                if (text.length() <= 11) {
                    if (text.length() >= 11) {
                        campoTelefone.setText(text.replaceAll("(\\d{2})(\\d{5})(\\d{4})", "($1) $2-$3"));
                    } else if (text.length() >= 7) {
                        campoTelefone.setText(text.replaceAll("(\\d{2})(\\d{4})(\\d{0,4})", "($1) $2-$3"));
                    } else if (text.length() >= 3) {
                        campoTelefone.setText(text.replaceAll("(\\d{2})(\\d{0,5})", "($1) $2"));
                    }
                }
            }
        });
        add(campoTelefone);

        add(new JLabel("E-mail:"));
        campoEmail = new JTextField();
        // Validação do Email em tempo real
        campoEmail.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                String email = campoEmail.getText();
                if (!email.isEmpty() && !email.contains("@")) {
                    JOptionPane.showMessageDialog(null, "E-mail deve conter @!");
                    campoEmail.requestFocus();
                }
            }
        });
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

                // Validações finais antes de salvar
                if (!validarDados(nome, cpf, telefone, email)) {
                    return;
                }

                // Formata os dados
                String nomeFormatado = formatarNome(nome);
                String cpfFormatado = formatarCPF(cpf);
                String telefoneFormatado = formatarTelefone(telefone);

                JOptionPane.showMessageDialog(null,
                        "Usuário cadastrado com sucesso!\n\n" +
                                "Nome: " + nomeFormatado +
                                "\nCPF: " + cpfFormatado +
                                "\nTelefone: " + telefoneFormatado +
                                "\nE-mail: " + email +
                                "\nLogin: " + login);

                // Fecha a tela de cadastro
                dispose();

                // Abre a tela de login (se existir)
                // new TelaLogin();
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

    // Método para validar todos os dados
    private boolean validarDados(String nome, String cpf, String telefone, String email) {
        // Valida CPF (deve ter 11 números)
        String cpfNumeros = cpf.replaceAll("\\D", "");
        if (cpfNumeros.length() != 11) {
            JOptionPane.showMessageDialog(null, "CPF deve conter exatamente 11 números!");
            campoCPF.requestFocus();
            return false;
        }

        // Valida Telefone (deve ter 10 ou 11 números)
        String telefoneNumeros = telefone.replaceAll("\\D", "");
        if (telefoneNumeros.length() < 10 || telefoneNumeros.length() > 11) {
            JOptionPane.showMessageDialog(null, "Telefone deve conter 10 ou 11 números!");
            campoTelefone.requestFocus();
            return false;
        }

        // Valida Email (deve conter @)
        if (!email.contains("@")) {
            JOptionPane.showMessageDialog(null, "E-mail deve conter @!");
            campoEmail.requestFocus();
            return false;
        }

        // Valida Nome (não pode estar vazio)
        if (nome.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nome é obrigatório!");
            campoNome.requestFocus();
            return false;
        }

        return true;
    }

    // Método para formatar nome com primeiras letras maiúsculas
    private String formatarNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            return nome;
        }

        String[] palavras = nome.trim().split("\\s+");
        StringBuilder nomeFormatado = new StringBuilder();

        for (String palavra : palavras) {
            if (!palavra.isEmpty()) {
                if (nomeFormatado.length() > 0) {
                    nomeFormatado.append(" ");
                }
                // Converte primeira letra para maiúscula e o restante para minúscula
                nomeFormatado.append(palavra.substring(0, 1).toUpperCase())
                        .append(palavra.substring(1).toLowerCase());
            }
        }

        return nomeFormatado.toString();
    }

    // Método para formatar CPF
    private String formatarCPF(String cpf) {
        String cpfNumeros = cpf.replaceAll("\\D", "");
        return cpfNumeros.replaceAll("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
    }

    // Método para formatar Telefone
    private String formatarTelefone(String telefone) {
        String telefoneNumeros = telefone.replaceAll("\\D", "");
        if (telefoneNumeros.length() == 11) {
            return telefoneNumeros.replaceAll("(\\d{2})(\\d{5})(\\d{4})", "($1) $2-$3");
        } else {
            return telefoneNumeros.replaceAll("(\\d{2})(\\d{4})(\\d{4})", "($1) $2-$3");
        }
    }

    
}