public class Mago extends Personagem {
    private int mana;
    private int manaMaxima = 100;

    public Mago(String nome) {
        super(nome, 100, 5, 20, 5); // HP, Força, Inteligência, Defesa
        this.mana = manaMaxima;
    }

    public int getMana() {
        return mana;
    }

    public void usarMana(int custo) {
        this.mana -= custo;
    }

    @Override
    public void atacar(Personagem alvo) {
        int danoMagico = getInteligencia() + random.nextInt(15);
        System.out.println(getNome() + " lançou um ataque mágico em " + alvo.getNome() + ", causando " + danoMagico + " de dano.");
        alvo.receberDano(danoMagico);
    }

    @Override
    public void usarHabilidadeEspecial(Personagem alvo) {
        System.out.println("Escolha a habilidade especial do Mago:");
        System.out.println("1. Bola de Fogo (causa dano mágico alto)");
        System.out.println("2. Escudo Arcano (aumenta temporariamente a defesa)");
        int escolha = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha

        switch (escolha) {
            case 1:
                if (mana >= 30) {
                    int danoBolaDeFogo = getInteligencia() + 25 + random.nextInt(10);
                    System.out.println(getNome() + " lançou uma Bola de Fogo, causando " + danoBolaDeFogo + " de dano em " + alvo.getNome() + "!");
                    alvo.receberDano(danoBolaDeFogo);
                    usarMana(30);
                } else {
                    System.out.println("Mana insuficiente para Bola de Fogo.");
                }
                break;
            case 2:
                if (mana >= 20) {
                    int defesaTemporaria = 15 + random.nextInt(10);
                    this.defesa += defesaTemporaria;
                    System.out.println(getNome() + " conjurou um Escudo Arcano, aumentando sua defesa em " + defesaTemporaria + " por um turno.");
                    usarMana(20);
                } else {
                    System.out.println("Mana insuficiente para Escudo Arcano.");
                }
                break;
            default:
                System.out.println("Opção inválida.");
                break;
        }
    }

    @Override
    public void resetarDefesa() {
        // O Escudo Arcano dura apenas um turno
        this.defesa = 5; // Reseta para a defesa base
    }
}