import java.util.Random;

public abstract class Conta {
    private int numero;
    protected double saldo;

    public Conta() {
        this.numero = new Random().nextInt(90000) + 10000; // Gera um número de conta aleatório
        this.saldo = 0.0;
    }

    public int getNumero() {
        return numero;
    }

    public double getSaldo() {
        return saldo;
    }

    public void depositar(double valor) {
        if (valor > 0) {
            this.saldo += valor;
            System.out.println("Depósito de R$" + String.format("%.2f", valor) + " realizado na conta " + this.numero + ". Novo saldo: R$" + String.format("%.2f", this.saldo));
        } else {
            System.out.println("Valor de depósito inválido.");
        }
    }

    public abstract boolean sacar(double valor);

    @Override
    public String toString() {
        return "Número: " + numero + ", Saldo: R$" + String.format("%.2f", saldo);
    }
}