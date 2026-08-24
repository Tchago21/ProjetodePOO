package dao;

import connection.Conexao;
import projetolocadora.Locacao;
import projetolocadora.Pagamento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

public class PagamentoDAO {

    public void inserir(Pagamento pagamento) {

        String sql =
                "INSERT INTO pagamento "
                + "(locacao_id, valor, forma_pagamento) "
                + "VALUES (?, ?, ?)";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt =
                     conexao.prepareStatement(sql)) {

            stmt.setInt(
                    1,
                    pagamento.getLocacao().getId()
            );

            stmt.setDouble(
                    2,
                    pagamento.getValor()
            );

            stmt.setString(
                    3,
                    pagamento.getFormaPagamento()
            );

            stmt.executeUpdate();

            System.out.println(
                    "Pagamento cadastrado com sucesso!"
            );

        } catch (SQLException e) {

            System.out.println(
                    "Erro ao cadastrar pagamento:"
            );

            e.printStackTrace();
        }
    }

    public List<Pagamento> listar() {

        List<Pagamento> pagamentos =
                new ArrayList<>();

        String sql =
                "SELECT * FROM pagamento";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt =
                     conexao.prepareStatement(sql);
             ResultSet rs =
                     stmt.executeQuery()) {

            while (rs.next()) {

                Pagamento pagamento =
                        criarPagamentoAPartirDoResultSet(rs);

                pagamentos.add(pagamento);
            }

        } catch (SQLException e) {

            System.out.println(
                    "Erro ao listar pagamentos:"
            );

            e.printStackTrace();
        }

        return pagamentos;
    }

    public Pagamento buscarPorId(int id) {

        String sql =
                "SELECT * FROM pagamento WHERE id = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt =
                     conexao.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs =
                    stmt.executeQuery()) {

                if (rs.next()) {

                    return criarPagamentoAPartirDoResultSet(rs);
                }
            }

        } catch (SQLException e) {

            System.out.println(
                    "Erro ao buscar pagamento:"
            );

            e.printStackTrace();
        }

        return null;
    }

    public boolean existePagamento(int locacaoId) {

        String sql =
                "SELECT COUNT(*) "
                + "FROM pagamento "
                + "WHERE locacao_id = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt =
                     conexao.prepareStatement(sql)) {

            stmt.setInt(1, locacaoId);

            try (ResultSet rs =
                    stmt.executeQuery()) {

                if (rs.next()) {

                    return rs.getInt(1) > 0;
                }
            }

        } catch (SQLException e) {

            System.out.println(
                    "Erro ao verificar pagamento:"
            );

            e.printStackTrace();
        }

        return false;
    }

    public Pagamento buscarPorLocacao(int locacaoId) {

        String sql =
                "SELECT * FROM pagamento "
                + "WHERE locacao_id = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt =
                     conexao.prepareStatement(sql)) {

            stmt.setInt(1, locacaoId);

            try (ResultSet rs =
                    stmt.executeQuery()) {

                if (rs.next()) {

                    return criarPagamentoAPartirDoResultSet(rs);
                }
            }

        } catch (SQLException e) {

            System.out.println(
                    "Erro ao buscar pagamento da locação:"
            );

            e.printStackTrace();
        }

        return null;
    }

    private Pagamento criarPagamentoAPartirDoResultSet(
            ResultSet rs) throws SQLException {

        LocacaoDAO locacaoDAO =
                new LocacaoDAO();

        Locacao locacao =
                locacaoDAO.buscarPorId(
                        rs.getInt("locacao_id")
                );

        Pagamento pagamento =
                new Pagamento();

        pagamento.setId(
                rs.getInt("id")
        );

        pagamento.setLocacao(
                locacao
        );

        pagamento.setValor(
                rs.getDouble("valor")
        );

        pagamento.setFormaPagamento(
                rs.getString("forma_pagamento")
        );

        return pagamento;
    }
}