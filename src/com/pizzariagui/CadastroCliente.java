package com.pizzariagui;

import com.pizzaria.db.DBConnection;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CadastroCliente extends JFrame {

    private JTextField txtNome;
    private JTextField txtTelefone;
    private JButton btnSalvar, btnEditar, btnExcluir, btnVoltar;

    public CadastroCliente() {
        // Configurações da janela principal
        setTitle("Cadastro de Cliente");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(240, 240, 240));

        // Painel de fundo com um gradiente suave
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                GradientPaint gradient = new GradientPaint(0, 0, new Color(255, 67, 54), 600, 0, new Color(255, 193, 7));
                ((Graphics2D) g).setPaint(gradient);
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        backgroundPanel.setLayout(new GridBagLayout());
        add(backgroundPanel, BorderLayout.CENTER);

        // Título da tela
        JLabel titleLabel = new JLabel("Cadastro de Cliente");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
        titleLabel.setForeground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 20, 10, 20);
        backgroundPanel.add(titleLabel, gbc);

        // Campos de cadastro - Nome
        JLabel lblNome = new JLabel("Nome:");
        lblNome.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblNome.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        backgroundPanel.add(lblNome, gbc);

        txtNome = new JTextField(20);
        txtNome.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtNome.setBorder(BorderFactory.createLineBorder(new Color(244, 67, 54), 2));
        gbc.gridx = 1;
        gbc.gridy = 1;
        backgroundPanel.add(txtNome, gbc);

        // Campos de cadastro - Telefone
        JLabel lblTelefone = new JLabel("Telefone:");
        lblTelefone.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblTelefone.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 2;
        backgroundPanel.add(lblTelefone, gbc);

        txtTelefone = new JTextField(20);
        txtTelefone.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtTelefone.setBorder(BorderFactory.createLineBorder(new Color(244, 67, 54), 2));
        gbc.gridx = 1;
        gbc.gridy = 2;
        backgroundPanel.add(txtTelefone, gbc);

        // Painel de Botões
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        buttonPanel.setBackground(new Color(240, 240, 240));
        add(buttonPanel, BorderLayout.SOUTH);

        // Botão Salvar
        btnSalvar = new JButton("Salvar");
        btnSalvar.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnSalvar.setBackground(new Color(244, 67, 54));
        btnSalvar.setForeground(Color.WHITE);
        btnSalvar.setPreferredSize(new Dimension(120, 40));
        btnSalvar.setFocusPainted(false);
        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvarCliente();
            }
        });
        buttonPanel.add(btnSalvar);

        // Botão Editar
        btnEditar = new JButton("Editar");
        btnEditar.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnEditar.setBackground(new Color(255, 193, 7));
        btnEditar.setForeground(Color.WHITE);
        btnEditar.setPreferredSize(new Dimension(120, 40));
        btnEditar.setFocusPainted(false);
        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editarCliente();
            }
        });
        buttonPanel.add(btnEditar);

        // Botão Excluir
        btnExcluir = new JButton("Excluir");
        btnExcluir.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnExcluir.setBackground(new Color(255, 87, 34));
        btnExcluir.setForeground(Color.WHITE);
        btnExcluir.setPreferredSize(new Dimension(120, 40));
        btnExcluir.setFocusPainted(false);
        btnExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                excluirCliente();
            }
        });
        buttonPanel.add(btnExcluir);

        // Botão Voltar
        btnVoltar = new JButton("Voltar ao Login");
        btnVoltar.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnVoltar.setBackground(new Color(0, 150, 136));
        btnVoltar.setForeground(Color.WHITE);
        btnVoltar.setPreferredSize(new Dimension(160, 40));
        btnVoltar.setFocusPainted(false);
        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                voltarLogin();
            }
        });
        buttonPanel.add(btnVoltar);
    }

    private void salvarCliente() {
        String nome = txtNome.getText();
        String telefone = txtTelefone.getText();
        try (Connection connection = DBConnection.getConnection()) {
            String sql = "INSERT INTO cliente (nome, telefone) VALUES (?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, nome);
                statement.setString(2, telefone);
                statement.executeUpdate();
                JOptionPane.showMessageDialog(this, "Cliente cadastrado com sucesso!");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao cadastrar cliente: " + e.getMessage());
        }
    }

    private void editarCliente() {
        // Lógica para editar o cliente
        String nome = txtNome.getText();
        String telefone = txtTelefone.getText();
        JOptionPane.showMessageDialog(this, "Cliente editado com sucesso!");
    }

    private void excluirCliente() {
        String nome = txtNome.getText();
        try (Connection connection = DBConnection.getConnection()) {
            String sql = "DELETE FROM cliente WHERE nome = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, nome);
                statement.executeUpdate();
                JOptionPane.showMessageDialog(this, "Cliente excluído com sucesso!");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao excluir cliente: " + e.getMessage());
        }
    }

    private void voltarLogin() {
        this.dispose();
        new TelaLogin().setVisible(true);  // Aqui você pode redirecionar para qualquer outra tela, como Login.
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CadastroCliente().setVisible(true); // Exibe a tela de cadastro de cliente
            }
        });
    }
}
