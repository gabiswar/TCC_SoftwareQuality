import java.util.ArrayList;
import java.util.List;

public class Estacionamento {
    private int totalVagas;
    private List<String> veiculosEstacionados;

    public Estacionamento(int totalVagas) {
        this.totalVagas = totalVagas;
        this.veiculosEstacionados = new ArrayList<>();
    }

    public boolean estacionarVeiculo(String placa) {
        if (veiculosEstacionados.size() < totalVagas) {
            veiculosEstacionados.add(placa);
            return true;
        }
        return false;
    }

    public boolean removerVeiculo(String placa) {
        return veiculosEstacionados.remove(placa);
    }

    public int vagasDisponiveis() {
        return totalVagas - veiculosEstacionados.size();
    }

    public void setTotalVagas(int totalVagas) {
        this.totalVagas = totalVagas;
    }

    public int getTotalVagas() {
        return totalVagas;
    }
}
