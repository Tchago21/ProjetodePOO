package dao;

public class DAOFactory {

    public static ClienteDAO criarClienteDAO() {
        return new ClienteDAO();
    }

    public static CarroDAO criarCarroDAO() {
        return new CarroDAO();
    }

    public static LocacaoDAO criarLocacaoDAO() {
        return new LocacaoDAO();
    }

    public static PagamentoDAO criarPagamentoDAO() {
        return new PagamentoDAO();
    }
}