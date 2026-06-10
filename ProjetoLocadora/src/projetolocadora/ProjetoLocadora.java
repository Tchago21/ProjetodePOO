package projetolocadora;
import java.time.LocalDate;

public class ProjetoLocadora {

    public static void main(String[] args) {

        Cliente cliente = new Cliente(
                1L,
                "Thiago",
                "123.456.789-00",
                "71999999999",
                "thiago@email.com"
        );

        Carro carro = new Carro(
                1L,
                "ABC1234",
                "Onix",
                "Chevrolet",
                2024,
                150.0,
                true,
                4,
                "Flex"
        );

        Locacao locacao = new Locacao(
                1L,
                cliente,
                carro,
                LocalDate.now(),
                LocalDate.now().plusDays(5)
        );

        System.out.println("Cliente: " + cliente.getNome());
        System.out.println("Veiculo: " + carro.getModelo());
        System.out.println("Valor Total: R$ " + locacao.getValorTotal());
    }
}