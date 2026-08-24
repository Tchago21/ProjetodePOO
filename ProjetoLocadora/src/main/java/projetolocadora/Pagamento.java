package projetolocadora;

/**
 *
 * @author Thiago Henrique
 */
public class Pagamento {

    private int id;
    private Locacao locacao;
    private double valor;
    private String formaPagamento;

    public Pagamento() {
    }

    public Pagamento(Locacao locacao, double valor, String formaPagamento) {
        this.locacao = locacao;
        this.valor = valor;
        this.formaPagamento = formaPagamento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Locacao getLocacao() {
        return locacao;
    }

    public void setLocacao(Locacao locacao) {
        this.locacao = locacao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }
}