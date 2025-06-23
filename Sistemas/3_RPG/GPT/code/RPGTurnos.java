import java.util.Scanner;
import java.util.Random;

abstract class Personagem {
    String nome;
    int hp;
    int forca;
    int inteligencia;
    int defesa;

    public Personagem(String nome, int hp, int forca, int inteligencia, int defesa) {
        this.nome = nome;
        this.hp = hp;
        this.forca = forca;
        this.inteligencia = inteligencia;
        this.defesa = defesa;
    }

    public boolean estaVivo() {
        return hp > 0;
    }

    public void atacar(Personagem alvo) {
        int dano = this.forca - alvo.defesa;
        if (dano < 0) dano = 0;
        alvo.hp -= dano;
        System.out.println(this.nome + " ataca " + alvo.nome + " causando " + dano + " de dano!");
    }

    public void defender() {
        this.defesa += 5;
        System.out.println(this.nome + " está defendendo! Defesa aumentada temporariamente.");
    }

    public abstract void habilidadeEspecial(Personagem alvo);
}

class Guerreiro extends Personagem {
    public Guerreiro(String nome) {
        super(nome, 120, 25, 10, 10);
    }

    @Override
    public void habilidadeEspecial(Personagem alvo) {
        int dano = (this.forca * 2) - alvo.defesa;
        if (dano < 0) dano = 0;
        alvo.hp -= dano;
        System.out.println(this.nome + " usa Golpe Poderoso em " + alvo.nome + " causando " + dano + " de dano!");
    }
}

class Mago extends Personagem {
    public Mago(String nome) {
        super(nome, 100, 10, 30, 5);
    }

    @Override
    public void habilidadeEspecial(Personagem alvo) {
        int dano = (this.inteligencia * 2) - alvo.defesa;
        if (dano < 0) dano = 0;
        alvo.hp -= dano;
        this.defesa += 5;
        System.out.println(this.nome + " lança Bola de Fogo em " + alvo.nome + " causando " + dano + " de dano!");
        System.out.println(this.nome + " aumenta sua defesa temporariamente.");
    }
}

public class RPGTurnos {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.println("=== Bem-vindo ao RPG de Turnos ===");
        System.out.println("Escolha seu personagem:");
        System.out.println("1. Guerreiro");
        System.out.println("2. Mago");
        int escolha = scanner.nextInt();
        scanner.nextLine(); // consumir linha

        Personagem jogador;
        if (escolha == 1) {
            jogador = new Guerreiro("Você");
        } else {
            jogador = new Mago("Você");
        }

        // Inimigo sempre será o outro personagem
        Personagem inimigo = (jogador instanceof Guerreiro)
                ? new Mago("Inimigo Mago")
                : new Guerreiro("Inimigo Guerreiro");

        System.out.println("Você enfrentará: " + inimigo.nome);

        // Loop de turnos
        while (jogador.estaVivo() && inimigo.estaVivo()) {
            System.out.println("\nSeu HP: " + jogador.hp + " | HP do " + inimigo.nome + ": " + inimigo.hp);
            System.out.println("Escolha sua ação:");
            System.out.println("1. Atacar");
            System.out.println("2. Usar Habilidade Especial");
            System.out.println("3. Defender");

            int acao = scanner.nextInt();

            // Turno do jogador
            switch (acao) {
                case 1:
                    jogador.atacar(inimigo);
                    break;
                case 2:
                    jogador.habilidadeEspecial(inimigo);
                    break;
                case 3:
                    jogador.defender();
                    break;
                default:
                    System.out.println("Ação inválida.");
            }

            // Verifica se inimigo morreu antes de agir
            if (!inimigo.estaVivo()) {
                System.out.println("\nVocê venceu!");
                break;
            }

            // Turno do inimigo (simples aleatório)
            int acaoInimigo = random.nextInt(3) + 1;
            switch (acaoInimigo) {
                case 1:
                    inimigo.atacar(jogador);
                    break;
                case 2:
                    inimigo.habilidadeEspecial(jogador);
                    break;
                case 3:
                    inimigo.defender();
                    break;
            }

            // Verifica se jogador morreu após ação do inimigo
            if (!jogador.estaVivo()) {
                System.out.println("\nVocê foi derrotado!");
            }
        }

        System.out.println("=== Fim de jogo ===");
    }
}
