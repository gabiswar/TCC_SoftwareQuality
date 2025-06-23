public class Guerreiro extends Personagem {
    public Guerreiro(String nome) {
        super(nome, 150, 20, 5, 15); // HP, Força, Inteligência, Defesa
    }

    @Override
    public void atacar(Personagem alvo) {
        int danoBase = getForca() + random.nextInt(10);
        System.out.println(getNome() + " atacou " + alvo.getNome() + " com um golpe poderoso, causando " + danoBase + " de dano.");
        alvo.receberDano(danoBase);
    }

    @Override
    public void usarHabilidadeEspecial(Personagem alvo) {
        System.out.println("Escolha a habilidade especial do Guerreiro:");
        System.out.println("1. Golpe Brutal (causa mais dano)");
        System.out.println("2. Postura Defensiva (reduz dano recebido no próximo turno)");
        int escolha = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha

        switch (escolha) {
            case 1:
                int danoBrutal = getForca() + 15 + random.nextInt(10);
                System.out.println(getNome() + " usou Golpe Brutal, causando " + danoBrutal + " de dano em " + alvo.getNome() + "!");
                alvo.receberDano(danoBrutal);
                break;
            case 2:
                int defesaAdicional = 10 + random.nextInt(5);
                this.defesa += defesaAdicional;
                System.out.println(getNome() + " adotou uma Postura Defensiva, aumentando sua defesa em " + defesaAdicional " para o próximo turno.");
                break;
            default:
                System.out.println("Opção inválida.");
                break;
        }
    }

    @Override
    public void resetarDefesa() {
        // A Postura Defensiva dura apenas um turno
        this.defesa = 15; // Reseta para a defesa base
    }
}