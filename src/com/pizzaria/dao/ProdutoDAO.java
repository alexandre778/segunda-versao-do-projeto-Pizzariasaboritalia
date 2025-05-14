package com.pizzaria.dao;

import com.pizzaria.model.Produto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    private Connection connection;

    public ProdutoDAO(Connection connection) {
        this.connection = connection;
    }

    // Método para inserir um produto no banco de dados
    public void inserir(Produto produto) throws SQLException {
        String sql = "INSERT INTO produtos (nome, preco, categoria, descricao) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            preencherDadosProduto(statement, produto);
            statement.executeUpdate();
        }
    }

    // Método para buscar um produto pelo ID
    public Produto buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM produtos WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next() ? criarProduto(resultSet) : null;
        }
    }

    // Método para listar todos os produtos
    public List<Produto> listarTodos() throws SQLException {
        String sql = "SELECT * FROM produtos";
        List<Produto> produtos = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                produtos.add(criarProduto(resultSet));
            }
        }
        return produtos;
    }

    // Método para atualizar os dados de um produto
    public void atualizar(Produto produto) throws SQLException {
        String sql = "UPDATE produtos SET nome = ?, preco = ?, categoria = ?, descricao = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            preencherDadosProduto(statement, produto);
            statement.setInt(5, produto.getId());
            statement.executeUpdate();
        }
    }

    // Método para excluir um produto
    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM produtos WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }

    // Método auxiliar para preencher os dados de um produto no PreparedStatement
    private void preencherDadosProduto(PreparedStatement statement, Produto produto) throws SQLException {
        statement.setString(1, produto.getNome());
        statement.setDouble(2, produto.getPreco());
        statement.setString(3, produto.getCategoria());
        statement.setString(4, produto.getDescricao());
    }

    // Método auxiliar para criar um Produto a partir de um ResultSet
    private Produto criarProduto(ResultSet resultSet) throws SQLException {
        Produto produto = new Produto();
        produto.setId(resultSet.getInt("id"));
        produto.setNome(resultSet.getString("nome"));
        produto.setPreco(resultSet.getDouble("preco"));
        produto.setCategoria(resultSet.getString("categoria"));
        produto.setDescricao(resultSet.getString("descricao"));
        return produto;
    }
}
