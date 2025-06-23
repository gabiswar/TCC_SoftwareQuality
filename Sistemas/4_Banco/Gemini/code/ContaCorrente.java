public class ContaCorrente extends Conta {
    @Override
    public boolean sacar(double valor) {
        if (valor > 0 && valor <= this.saldo) {
            this.saldo -= valor;
            System.out.println("Saque de R$" + String.format("%.2f", valor) + " realizado na conta corrente " + this.getNumero() + ". Novo saldo: R$" + String.format("%.2f", this.saldo));
            return true;
        } else if (valor <= 0) {
            System.out.println("Valor de saque inválido.");
            return false;
        } else {
            System.out.println("Saldo insuficiente para saque na conta corrente " + this.getNumero() + ".");
            return false;
        }
    }

    public boolean transferir(Conta destino, double valor) {
        if (this.sacar(valor)) {
            destino.depositar(valor);
            System.out.println("Transferência de R$" + String.format("%.2f", valor) + " da conta " + this.getNumero() + " para a conta " + destino.getNumero() + " realizada com sucesso.");
            return true;
        } else {
            System.out.println("Falha na transferência da conta " + this.getNumero() + " para a conta " + destino.getNumero() + ".");
            return false;
        }
    }
}