package com.pizzaria.dao;

import com.pizzaria.model.Cliente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    private Connection connection;

    public ClienteDAO(Connection connection) {
        this.connection = connection;
    }

    // Método para inserir um cliente no banco de dados
    public void inserir(Cliente cliente) throws SQLException {
        String sql = "INSERT INTO clientes (nome, telefone) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            preencherDadosCliente(statement, cliente);
            statement.executeUpdate();
        }
    }

    // Método para buscar um cliente pelo ID
    public Cliente buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM clientes WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next() ? criarCliente(resultSet) : null;
        }
    }

    // Método para listar todos os clientes
    public List<Cliente> listarTodos() throws SQLException {
        String sql = "SELECT * FROM clientes";
        List<Cliente> clientes = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                clientes.add(criarCliente(resultSet));
            }
        }
        return clientes;
    }

    // Método para atualizar os dados de um cliente
    public void atualizar(Cliente cliente) throws SQLException {
        String sql = "UPDATE clientes SET nome = ?, telefone = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            preencherDadosCliente(statement, cliente);
            statement.setInt(3, cliente.getId());
            statement.executeUpdate();
        }
    }

    // Método para excluir um cliente
    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM clientes WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }

    // Método auxiliar para preencher os dados de um cliente no PreparedStatement
    private void preencherDadosCliente(PreparedStatement statement, Cliente cliente) throws SQLException {
        statement.setString(1, cliente.getNome());
        statement.setString(2, cliente.getTelefone());
    }

    // Método auxiliar para criar um Cliente a partir de um ResultSet
    private Cliente criarCliente(ResultSet resultSet) throws SQLException {
        Cliente cliente = new Cliente();
        cliente.setId(resultSet.getInt("id"));
        cliente.setNome(resultSet.getString("nome"));
        cliente.setTelefone(resultSet.getString("telefone"));
        return cliente;
    }
}
