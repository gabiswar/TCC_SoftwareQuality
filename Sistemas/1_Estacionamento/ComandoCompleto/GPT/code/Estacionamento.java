import java.util.ArrayList;
import java.util.List;

public class Estacionamento {
    private int totalVagas;
    private List<Veiculo> veiculosEstacionados;

    public Estacionamento(int totalVagas) {
        this.totalVagas = totalVagas;
        this.veiculosEstacionados = new ArrayList<>();
    }

    public boolean estacionarVeiculo(Veiculo veiculo) {
        if (veiculosEstacionados.size() < totalVagas) {
            veiculosEstacionados.add(veiculo);
            return true;
        } else {
            return false;
        }
    }

    public boolean removerVeiculo(String placa) {
        for (Veiculo v : veiculosEstacionados) {
            if (v.getPlaca().equalsIgnoreCase(placa)) {
                veiculosEstacionados.remove(v);
                return true;
            }
        }
        return false;
    }

    public int vagasDisponiveis() {
        return totalVagas - veiculosEstacionados.size();
    }

    public void setTotalVagas(int totalVagas) {
        this.totalVagas = totalVagas;
    }

    public void listarVeiculos() {
        if (veiculosEstacionados.isEmpty()) {
            System.out.println("Nenhum veículo estacionado.");
        } else {
            System.out.println("Veículos estacionados:");
            for (Veiculo v : veiculosEstacionados) {
                System.out.println("- " + v.getPlaca());
            }
        }
    }
}
