package projetolocadora;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Locacao {

    private long id;
    private Cliente cliente;
    private Veiculo veiculo;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private double valorTotal;

    public void calcularValorTotal() {
        long dias = ChronoUnit.DAYS.between(dataInicio, dataFim);

        if (dias <= 0) {
            dias = 1;
        }

        valorTotal = dias * veiculo.getValorDiaria();
    }    
    
    public Locacao() {
    }

    public Locacao(Long id, Cliente cliente, Veiculo veiculo,
                   LocalDate dataInicio, LocalDate dataFim) {

        this.id = id;
        this.cliente = cliente;
        this.veiculo = veiculo;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;

        calcularValorTotal();
    }

    public Long getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public double getValorTotal() {
        return valorTotal;
    }
}