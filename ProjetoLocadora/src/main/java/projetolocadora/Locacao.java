package projetolocadora;

public class Locacao {

    private int id;
    private Cliente cliente;
    private Carro carro;
    private int quantidadeDias;
    private double valorTotal;
    private boolean devolvida;

    // Construtor vazio
    public Locacao() {
    }

    // Construtor para criar uma nova locação
    public Locacao(Cliente cliente, Carro carro, int quantidadeDias) {

        this.cliente = cliente;
        this.carro = carro;
        this.quantidadeDias = quantidadeDias;

        this.valorTotal =
                carro.getValorDiaria() * quantidadeDias;

        this.devolvida = false;
    }

    // Construtor completo
    public Locacao(
            int id,
            Cliente cliente,
            Carro carro,
            int quantidadeDias,
            double valorTotal,
            boolean devolvida) {

        this.id = id;
        this.cliente = cliente;
        this.carro = carro;
        this.quantidadeDias = quantidadeDias;
        this.valorTotal = valorTotal;
        this.devolvida = devolvida;
    }

    // ID
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Cliente
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    // Carro
    public Carro getCarro() {
        return carro;
    }

    public void setCarro(Carro carro) {
        this.carro = carro;
    }

    // Quantidade de dias
    public int getQuantidadeDias() {
        return quantidadeDias;
    }

    public void setQuantidadeDias(int quantidadeDias) {
        this.quantidadeDias = quantidadeDias;
    }

    // Valor total
    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    // Devolvida
    public boolean isDevolvida() {
        return devolvida;
    }

    public void setDevolvida(boolean devolvida) {
        this.devolvida = devolvida;
    }
}