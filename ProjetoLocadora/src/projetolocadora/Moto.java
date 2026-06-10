package projetolocadora;

public class Moto extends Veiculo {

    private int cilindradas;

    public Moto() {
    }

    public Moto(Long id, String placa, String modelo, String marca,
                int ano, double valorDiaria, boolean disponivel,
                int cilindradas) {

        super(id, placa, modelo, marca, ano, valorDiaria, disponivel);
        this.cilindradas = cilindradas;
    }

    @Override
    public String getTipo() {
        return "Moto";
    }

    public int getCilindradas() {
        return cilindradas;
    }

    public void setCilindradas(int cilindradas) {
        this.cilindradas = cilindradas;
    }
}
