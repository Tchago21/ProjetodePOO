package dao;

import connection.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import projetolocadora.Carro;

public class CarroDAO {

    public void inserir(Carro carro) {

        String sql = "INSERT INTO carro "
                + "(marca, modelo, placa, ano, valor_diaria, disponivel) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, carro.getMarca());
            stmt.setString(2, carro.getModelo());
            stmt.setString(3, carro.getPlaca());
            stmt.setInt(4, carro.getAno());
            stmt.setDouble(5, carro.getValorDiaria());
            stmt.setBoolean(6, carro.isDisponivel());

            stmt.executeUpdate();

            System.out.println("Carro cadastrado com sucesso!");

        } catch (SQLException e) {

            System.out.println("Erro ao cadastrar carro.");
            e.printStackTrace();
        }
    }
    
    public List<Carro> listar() {

    List<Carro> carros = new ArrayList<>();

    String sql = "SELECT * FROM carro";

    try (Connection conexao = Conexao.conectar();
         PreparedStatement stmt = conexao.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {

            Carro carro = new Carro();

            carro.setId(rs.getInt("id"));
            carro.setMarca(rs.getString("marca"));
            carro.setModelo(rs.getString("modelo"));
            carro.setPlaca(rs.getString("placa"));
            carro.setAno(rs.getInt("ano"));
            carro.setValorDiaria(rs.getDouble("valor_diaria"));
            carro.setDisponivel(rs.getBoolean("disponivel"));

            carros.add(carro);
        }

    } catch (SQLException e) {

        System.out.println("Erro ao listar carros.");
        e.printStackTrace();
    }

    return carros;
}
    public Carro buscarPorId(int id) {

    String sql = "SELECT * FROM carro WHERE id = ?";

    try (Connection conexao = Conexao.conectar();
         PreparedStatement stmt = conexao.prepareStatement(sql)) {

        stmt.setInt(1, id);

        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {

            Carro carro = new Carro();

            carro.setId(rs.getInt("id"));
            carro.setMarca(rs.getString("marca"));
            carro.setModelo(rs.getString("modelo"));
            carro.setPlaca(rs.getString("placa"));
            carro.setAno(rs.getInt("ano"));
            carro.setValorDiaria(rs.getDouble("valor_diaria"));
            carro.setDisponivel(rs.getBoolean("disponivel"));

            return carro;
        }

    } catch (SQLException e) {

        System.out.println("Erro ao buscar carro.");
        e.printStackTrace();
    }

    return null;
}
    public boolean excluir(int id) {

    String sql = "DELETE FROM carro WHERE id = ?";

    try (Connection conexao = Conexao.conectar();
         PreparedStatement stmt = conexao.prepareStatement(sql)) {

        stmt.setInt(1, id);

        stmt.executeUpdate();

        return true;

    } catch (SQLException e) {

        // O carro possui uma locação vinculada
        if (e.getErrorCode() == 1451) {
            return false;
        }

        e.printStackTrace();
        return false;
    }
}
    public void atualizarDisponibilidade(int id, boolean disponivel) {

    String sql = "UPDATE carro SET disponivel = ? WHERE id = ?";

    try (Connection conexao = Conexao.conectar();
         PreparedStatement stmt = conexao.prepareStatement(sql)) {

        stmt.setBoolean(1, disponivel);
        stmt.setInt(2, id);

        stmt.executeUpdate();

    } catch (SQLException e) {
        e.printStackTrace();
    }
}
}