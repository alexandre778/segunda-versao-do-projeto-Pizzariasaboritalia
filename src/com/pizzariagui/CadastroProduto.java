package com.pizzariagui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CadastroProduto extends JFrame {

    private JTextField txtNome;
    private JTextField txtPreco;
    private JTextField txtCategoria;
    private JTextArea txtDescricao;
    private JButton btnSalvar;
    private JButton btnVoltar;
    private JButton btnNovoCadastro; // Novo botão

    public CadastroProduto() {
        setTitle("Cadastro de Pedido");
        setLayout(new BorderLayout(10, 10));

        // Painel principal
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setBackground(new Color(255, 235, 238)); // Cor de fundo suave

        // Definir GridBagConstraints para os componentes
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Título da tela
        JLabel titleLabel = new JLabel("Cadastro de Pedido");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(new Color(244, 67, 54));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        mainPanel.add(titleLabel, gbc);

        // Nome
        JLabel lblNome = new JLabel("Nome:");
        lblNome.setFont(new Font("Arial", Font.PLAIN, 14));
        lblNome.setForeground(new Color(244, 67, 54));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        mainPanel.add(lblNome, gbc);

        txtNome = new JTextField(20);
        txtNome.setFont(new Font("Arial", Font.PLAIN, 14));
        txtNome.setBorder(BorderFactory.createLineBorder(new Color(244, 67, 54), 2));
        gbc.gridx = 1;
        gbc.gridy = 1;
        mainPanel.add(txtNome, gbc);

        // Preço
        JLabel lblPreco = new JLabel("Preço:");
        lblPreco.setFont(new Font("Arial", Font.PLAIN, 14));
        lblPreco.setForeground(new Color(244, 67, 54));
        gbc.gridx = 0;
        gbc.gridy = 2;
        mainPanel.add(lblPreco, gbc);

        txtPreco = new JTextField(20);
        txtPreco.setFont(new Font("Arial", Font.PLAIN, 14));
        txtPreco.setBorder(BorderFactory.createLineBorder(new Color(244, 67, 54), 2));
        gbc.gridx = 1;
        gbc.gridy = 2;
        mainPanel.add(txtPreco, gbc);

        // Categoria
        JLabel lblCategoria = new JLabel("Categoria:");
        lblCategoria.setFont(new Font("Arial", Font.PLAIN, 14));
        lblCategoria.setForeground(new Color(244, 67, 54));
        gbc.gridx = 0;
        gbc.gridy = 3;
        mainPanel.add(lblCategoria, gbc);

        txtCategoria = new JTextField(20);
        txtCategoria.setFont(new Font("Arial", Font.PLAIN, 14));
        txtCategoria.setBorder(BorderFactory.createLineBorder(new Color(244, 67, 54), 2));
        gbc.gridx = 1;
        gbc.gridy = 3;
        mainPanel.add(txtCategoria, gbc);

        // Descrição
        JLabel lblDescricao = new JLabel("Descrição:");
        lblDescricao.setFont(new Font("Arial", Font.PLAIN, 14));
        lblDescricao.setForeground(new Color(244, 67, 54));
        gbc.gridx = 0;
        gbc.gridy = 4;
        mainPanel.add(lblDescricao, gbc);

        txtDescricao = new JTextArea(5, 20);
        txtDescricao.setFont(new Font("Arial", Font.PLAIN, 14));
        txtDescricao.setBorder(BorderFactory.createLineBorder(new Color(244, 67, 54), 2));
        gbc.gridx = 1;
        gbc.gridy = 4;
        mainPanel.add(new JScrollPane(txtDescricao), gbc);

        // Botão salvar
        btnSalvar = new JButton("Salvar");
        btnSalvar.setFont(new Font("Arial", Font.BOLD, 14));
        btnSalvar.setBackground(new Color(244, 67, 54));
        btnSalvar.setForeground(Color.WHITE);
        btnSalvar.setFocusPainted(false);
        btnSalvar.setPreferredSize(new Dimension(100, 40));
        btnSalvar.setBorder(BorderFactory.createEmptyBorder());

        // Evento de clique no botão salvar
        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvarProduto();
            }
        });
        gbc.gridx = 1;
        gbc.gridy = 5;
        mainPanel.add(btnSalvar, gbc);

        // Botão voltar
        btnVoltar = new JButton("Voltar");
        btnVoltar.setFont(new Font("Arial", Font.BOLD, 14));
        btnVoltar.setBackground(new Color(255, 87, 34));
        btnVoltar.setForeground(Color.WHITE);
        btnVoltar.setFocusPainted(false);
        btnVoltar.setPreferredSize(new Dimension(100, 40));
        btnVoltar.setBorder(BorderFactory.createEmptyBorder());

        // Evento de clique no botão voltar
        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                voltarLogin();
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 5;
        mainPanel.add(btnVoltar, gbc);

        // Novo botão para novo cadastro
        btnNovoCadastro = new JButton(" Cadastro de Cliente");
        btnNovoCadastro.setFont(new Font("Arial", Font.BOLD, 14));
        btnNovoCadastro.setBackground(new Color(33, 150, 243));
        btnNovoCadastro.setForeground(Color.WHITE);
        btnNovoCadastro.setFocusPainted(false);
        btnNovoCadastro.setPreferredSize(new Dimension(150, 40));
        btnNovoCadastro.setBorder(BorderFactory.createEmptyBorder());

        // Evento de clique no botão novo cadastro
        btnNovoCadastro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                novoCadastro();
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        mainPanel.add(btnNovoCadastro, gbc);

        // Adicionar o painel principal à janela
        add(mainPanel, BorderLayout.CENTER);

        setSize(500, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void salvarProduto() {
        String nome = txtNome.getText();
        String preco = txtPreco.getText();
        String categoria = txtCategoria.getText();
        String descricao = txtDescricao.getText();

        // Salve o produto no banco de dados
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/pizzariasaboritalia", "root", "81225573");
            String query = "INSERT INTO produtos (nome, preco, categoria, descricao) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, nome);
            stmt.setString(2, preco);
            stmt.setString(3, categoria);
            stmt.setString(4, descricao);

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                JOptionPane.showMessageDialog(this, "Produto salvo com sucesso!");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar produto: " + e.getMessage());
        }
    }

    private void voltarLogin() {
        // Fechar a tela atual
        dispose();
        // Abrir a tela de login
        new TelaLogin().setVisible(true);
    }

    private void novoCadastro() {
        // Limpar os campos de texto para novo cadastro
        txtNome.setText("");
        txtPreco.setText("");
        txtCategoria.setText("");
        txtDescricao.setText("");

        new CadastroCliente().setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CadastroProduto().setVisible(true);
            }
        });
    }
}
