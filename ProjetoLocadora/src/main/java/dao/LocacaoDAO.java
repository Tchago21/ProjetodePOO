package dao;

import connection.Conexao;
import projetolocadora.Carro;
import projetolocadora.Cliente;
import projetolocadora.Locacao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

public class LocacaoDAO {

    public void inserir(Locacao locacao) {

        String sql =
            "INSERT INTO locacao "
            + "(cliente_id, carro_id, quantidade_dias, valor_total, devolvida) "
            + "VALUES (?, ?, ?, ?, ?)";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt =
                 conexao.prepareStatement(sql)) {

            stmt.setInt(
                1,
                locacao.getCliente().getId()
            );

            stmt.setInt(
                2,
                locacao.getCarro().getId()
            );

            stmt.setInt(
                3,
                locacao.getQuantidadeDias()
            );

            stmt.setDouble(
                4,
                locacao.getValorTotal()
            );

            stmt.setBoolean(
                5,
                locacao.isDevolvida()
            );

            stmt.executeUpdate();

            System.out.println(
                "Locação cadastrada com sucesso!"
            );

        } catch (SQLException e) {

            System.out.println(
                "Erro ao cadastrar locação:"
            );

            e.printStackTrace();
        }
    }

    public List<Locacao> listar() {

        List<Locacao> locacoes =
            new ArrayList<>();

        String sql =
            "SELECT * FROM locacao";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt =
                 conexao.prepareStatement(sql);
             ResultSet rs =
                 stmt.executeQuery()) {

            while (rs.next()) {

                Locacao locacao =
                    criarLocacaoAPartirDoResultSet(rs);

                locacoes.add(locacao);
            }

        } catch (SQLException e) {

            System.out.println(
                "Erro ao listar locações:"
            );

            e.printStackTrace();
        }

        return locacoes;
    }

    public Locacao buscarPorId(int id) {

        String sql =
            "SELECT * FROM locacao WHERE id = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt =
                 conexao.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs =
                    stmt.executeQuery()) {

                if (rs.next()) {

                    return criarLocacaoAPartirDoResultSet(rs);
                }
            }

        } catch (SQLException e) {

            System.out.println(
                "Erro ao buscar locação:"
            );

            e.printStackTrace();
        }

        return null;
    }

    public void registrarDevolucao(int idLocacao) {

        String sql =
            "UPDATE locacao "
            + "SET devolvida = true "
            + "WHERE id = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt =
                 conexao.prepareStatement(sql)) {

            stmt.setInt(1, idLocacao);

            stmt.executeUpdate();

            System.out.println(
                "Devolução registrada com sucesso!"
            );

        } catch (SQLException e) {

            System.out.println(
                "Erro ao registrar devolução:"
            );

            e.printStackTrace();
        }
    }

    private Locacao criarLocacaoAPartirDoResultSet(
            ResultSet rs) throws SQLException {

        ClienteDAO clienteDAO =
            new ClienteDAO();

        Cliente cliente =
            clienteDAO.buscarPorId(
                rs.getInt("cliente_id")
            );

        CarroDAO carroDAO =
            new CarroDAO();

        Carro carro =
            carroDAO.buscarPorId(
                rs.getInt("carro_id")
            );

        Locacao locacao =
            new Locacao();

        locacao.setId(
            rs.getInt("id")
        );

        locacao.setCliente(cliente);

        locacao.setCarro(carro);

        locacao.setQuantidadeDias(
            rs.getInt("quantidade_dias")
        );

        locacao.setValorTotal(
            rs.getDouble("valor_total")
        );

        locacao.setDevolvida(
            rs.getBoolean("devolvida")
        );

        return locacao;
    }
}