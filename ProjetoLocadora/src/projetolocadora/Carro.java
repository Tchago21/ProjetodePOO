package projetolocadora;

public class Carro extends Veiculo {

    private int portas;
    private String combustivel;

    public Carro() {
    }

    public Carro(Long id, String placa, String modelo, String marca,
                 int ano, double valorDiaria, boolean disponivel,
                 int portas, String combustivel) {

        super(id, placa, modelo, marca, ano, valorDiaria, disponivel);
        this.portas = portas;
        this.combustivel = combustivel;
    }

    @Override
    public String getTipo() {
        return "Carro";
    }

    public int getPortas() {
        return portas;
    }

    public void setPortas(int portas) {
        this.portas = portas;
    }

    public String getCombustivel() {
        return combustivel;
    }

    public void setCombustivel(String combustivel) {
        this.combustivel = combustivel;
    }
}
