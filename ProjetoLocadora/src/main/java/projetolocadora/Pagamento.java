package projetolocadora;

public class Pagamento {

    private int id;
    private Locacao locacao;
    private double valor;
    private String formaPagamento;

    // Construtor vazio
    public Pagamento() {
    }

    // Construtor completo
    public Pagamento(Locacao locacao, double valor, String formaPagamento) {
        this.locacao = locacao;
        this.valor = valor;
        this.formaPagamento = formaPagamento;
    }

    // ID
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Locação
    public Locacao getLocacao() {
        return locacao;
    }

    public void setLocacao(Locacao locacao) {
        this.locacao = locacao;
    }

    // Valor
    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    // Forma de pagamento
    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }
}