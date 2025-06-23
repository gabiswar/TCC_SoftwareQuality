public class Cliente {
    private String nome;
    private String cpf;
    private Conta conta; // Uma conta por cliente para simplificar

    public Cliente(String nome, String cpf, Conta conta) {
        this.nome = nome;
        this.cpf = cpf;
        this.conta = conta;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public Conta getConta() {
        return conta;
    }

    @Override
    public String toString() {
        return "Nome: " + nome + ", CPF: " + cpf + ", Conta: " + conta.getClass().getSimpleName() + " (Número: " + conta.getNumero() + ")";
    }
}