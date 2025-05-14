package com.pizzariagui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaLogin extends JFrame {

    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;

    public TelaLogin() {
        // Configurações da janela
        setTitle("Login - Pizzaria Sabor da Itália");
        setSize(350, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Painel de fundo
        JPanel backgroundPanel = new JPanel();
        backgroundPanel.setBackground(new Color(255, 235, 238)); // Cor de fundo suave
        add(backgroundPanel);
        backgroundPanel.setLayout(new GridBagLayout());

        // Título da tela de login
        JLabel titleLabel = new JLabel("Bem-vindo à Pizzaria Sabor da Itália");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(new Color(244, 67, 54)); // Cor de texto
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 10, 10, 10);
        backgroundPanel.add(titleLabel, gbc);

        // Campos de login
        JLabel lblUsername = new JLabel("Usuário:");
        lblUsername.setFont(new Font("Arial", Font.PLAIN, 14));
        lblUsername.setForeground(new Color(244, 67, 54)); // Cor de texto
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(5, 10, 5, 10);
        backgroundPanel.add(lblUsername, gbc);

        txtUsername = new JTextField(20);
        txtUsername.setFont(new Font("Arial", Font.PLAIN, 14));
        txtUsername.setBorder(BorderFactory.createLineBorder(new Color(244, 67, 54), 2));
        gbc.gridx = 1;
        gbc.gridy = 1;
        backgroundPanel.add(txtUsername, gbc);

        JLabel lblPassword = new JLabel("Senha:");
        lblPassword.setFont(new Font("Arial", Font.PLAIN, 14));
        lblPassword.setForeground(new Color(244, 67, 54)); // Cor de texto
        gbc.gridx = 0;
        gbc.gridy = 2;
        backgroundPanel.add(lblPassword, gbc);

        txtPassword = new JPasswordField(20);
        txtPassword.setFont(new Font("Arial", Font.PLAIN, 14));
        txtPassword.setBorder(BorderFactory.createLineBorder(new Color(244, 67, 54), 2));
        gbc.gridx = 1;
        gbc.gridy = 2;
        backgroundPanel.add(txtPassword, gbc);

        // Botão de login
        btnLogin = new JButton("Entrar");
        btnLogin.setFont(new Font("Arial", Font.BOLD, 14));
        btnLogin.setBackground(new Color(244, 67, 54));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFocusPainted(false);
        btnLogin.setBorder(BorderFactory.createEmptyBorder());
        btnLogin.setPreferredSize(new Dimension(100, 40));

        // Efeito de hover no botão de login
        btnLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnLogin.setBackground(new Color(255, 152, 0)); // Cor ao passar o mouse
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnLogin.setBackground(new Color(244, 67, 54)); // Cor de fundo original
            }
        });

        // Evento de clique do botão de login
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verificarLogin();
            }
        });

        gbc.gridx = 1;
        gbc.gridy = 3;
        backgroundPanel.add(btnLogin, gbc);
    }

    private void verificarLogin() {
        String usuario = txtUsername.getText();
        String senha = new String(txtPassword.getPassword());

        // Verificação de login
        if (usuario.equals("admin") && senha.equals("123")) {
            JOptionPane.showMessageDialog(this, "Login bem-sucedido!");
            // Redireciona para a tela de cadastro de produto
            new CadastroProduto().setVisible(true);
            dispose(); // Fecha a tela de login
        } else {
            JOptionPane.showMessageDialog(this, "Usuário ou senha inválidos.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TelaLogin().setVisible(true);
            }
        });
    }
}
