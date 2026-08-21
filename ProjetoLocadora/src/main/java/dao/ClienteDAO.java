package dao;

import connection.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import projetolocadora.Cliente;

public class ClienteDAO {

    public void inserir(Cliente cliente) {

        String sql = "INSERT INTO cliente (nome, cpf, telefone) VALUES (?, ?, ?)";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpf());
            stmt.setString(3, cliente.getTelefone());

            stmt.executeUpdate();

            System.out.println("Cliente cadastrado com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar cliente.");
            e.printStackTrace();
        }
    }

    public List<Cliente> listar() {

        List<Cliente> clientes = new ArrayList<>();

        String sql = "SELECT * FROM cliente";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                Cliente cliente = new Cliente();

                cliente.setId(rs.getInt("id"));
                cliente.setNome(rs.getString("nome"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setTelefone(rs.getString("telefone"));

                clientes.add(cliente);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar clientes.");
            e.printStackTrace();
        }

        return clientes;
    }

    public Cliente buscarPorId(int id) {

        String sql = "SELECT * FROM cliente WHERE id = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                Cliente cliente = new Cliente();

                cliente.setId(rs.getInt("id"));
                cliente.setNome(rs.getString("nome"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setTelefone(rs.getString("telefone"));

                return cliente;
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar cliente.");
            e.printStackTrace();
        }

        return null;
    }

    public boolean excluir(int id) {

    String sql = "DELETE FROM cliente WHERE id = ?";

    try (Connection conexao = Conexao.conectar();
         PreparedStatement stmt = conexao.prepareStatement(sql)) {

        stmt.setInt(1, id);

        stmt.executeUpdate();

        return true;

    } catch (SQLException e) {

        // Cliente possui uma locação associada
        if (e.getErrorCode() == 1451) {
            return false;
        }

        e.printStackTrace();
        return false;
    }
}
}