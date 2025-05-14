package com.pizzaria.dao;

import com.pizzaria.model.Pedido;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAO {

    private Connection connection;

    public PedidoDAO(Connection connection) {
        this.connection = connection;
    }

    // Método para inserir um pedido no banco de dados
    public void inserir(Pedido pedido) throws SQLException {
        String sql = "INSERT INTO pedidos (cliente_id, produto_id, quantidade, status) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            preencherDadosPedido(statement, pedido);
            statement.executeUpdate();
        }
    }

    // Método para buscar um pedido pelo ID
    public Pedido buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM pedidos WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next() ? criarPedido(resultSet) : null;
        }
    }

    // Método para listar todos os pedidos
    public List<Pedido> listarTodos() throws SQLException {
        String sql = "SELECT * FROM pedidos";
        List<Pedido> pedidos = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                pedidos.add(criarPedido(resultSet));
            }
        }
        return pedidos;
    }

    // Método para atualizar os dados de um pedido
    public void atualizar(Pedido pedido) throws SQLException {
        String sql = "UPDATE pedidos SET cliente_id = ?, produto_id = ?, quantidade = ?, status = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            preencherDadosPedido(statement, pedido);
            statement.setInt(5, pedido.getId());
            statement.executeUpdate();
        }
    }

    // Método para excluir um pedido
    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM pedidos WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }

    // Método auxiliar para preencher os dados de um pedido no PreparedStatement
    private void preencherDadosPedido(PreparedStatement statement, Pedido pedido) throws SQLException {
        statement.setInt(1, pedido.getClienteId());
        statement.setInt(2, pedido.getProdutoId());
        statement.setInt(3, pedido.getQuantidade());
        statement.setString(4, pedido.getStatus());
    }

    // Método auxiliar para criar um Pedido a partir de um ResultSet
    private Pedido criarPedido(ResultSet resultSet) throws SQLException {
        Pedido pedido = new Pedido();
        pedido.setId(resultSet.getInt("id"));
        pedido.setClienteId(resultSet.getInt("cliente_id"));
        pedido.setProdutoId(resultSet.getInt("produto_id"));
        pedido.setQuantidade(resultSet.getInt("quantidade"));
        pedido.setStatus(resultSet.getString("status"));
        return pedido;
    }
}
