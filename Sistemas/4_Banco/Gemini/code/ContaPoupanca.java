public class ContaPoupanca extends Conta {
    private double taxaJurosMensal;

    public ContaPoupanca(double taxaJurosMensal) {
        this.taxaJurosMensal = taxaJurosMensal;
    }

    public double getTaxaJurosMensal() {
        return taxaJurosMensal;
    }

    public void setTaxaJurosMensal(double taxaJurosMensal) {
        this.taxaJurosMensal = taxaJurosMensal;
    }

    @Override
    public boolean sacar(double valor) {
        if (valor > 0 && valor <= this.saldo) {
            this.saldo -= valor;
            System.out.println("Saque de R$" + String.format("%.2f", valor) + " realizado na conta poupança " + this.getNumero() + ". Novo saldo: R$" + String.format("%.2f", this.saldo));
            return true;
        } else if (valor <= 0) {
            System.out.println("Valor de saque inválido.");
            return false;
        } else {
            System.out.println("Saldo insuficiente para saque na conta poupança " + this.getNumero() + ".");
            return false;
        }
    }

    public void gerarRendimento() {
        double rendimento = this.saldo * this.taxaJurosMensal;
        this.saldo += rendimento;
        System.out.println("Rendimento de R$" + String.format("%.2f", rendimento) + " gerado na conta poupança " + this.getNumero() + ". Novo saldo: R$" + String.format("%.2f", this.saldo));
    }

    public double simularRendimentoMensal() {
        return this.saldo * this.taxaJurosMensal;
    }

    public double simularRendimentoAnual() {
        // Considera juros simples para simplificar a simulação anual
        return this.saldo * this.taxaJurosMensal * 12;
    }
}