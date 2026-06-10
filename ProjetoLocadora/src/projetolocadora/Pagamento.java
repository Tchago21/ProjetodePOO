package projetolocadora;

public class Pagamento {

    private long id;
    private Locacao locacao;
    private String formaPagamento;
    private double valor;
    private String status;

    public Pagamento() {
    }

    public Pagamento(Long id, Locacao locacao,
                      String formaPagamento,
                      double valor,
                      String status) {

        this.id = id;
        this.locacao = locacao;
        this.formaPagamento = formaPagamento;
        this.valor = valor;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public Locacao getLocacao() {
        return locacao;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public double getValor() {
        return valor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}