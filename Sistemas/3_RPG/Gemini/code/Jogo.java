public class Jogo {
    private Personagem jogador;
    private Personagem oponente;
    private Scanner scanner = new Scanner(System.in);

    public void iniciarJogo() {
        System.out.println("Bem-vindo ao Jogo de RPG!");
        escolherPersonagem();
        criarOponente();
        loopDeTurnos();
        finalizarJogo();
    }

    private void escolherPersonagem() {
        System.out.println("Escolha seu personagem:");
        System.out.println("1. Guerreiro");
        System.out.println("2. Mago");
        int escolha = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha

        System.out.print("Digite o nome do seu personagem: ");
        String nome = scanner.nextLine();

        switch (escolha) {
            case 1:
                jogador = new Guerreiro(nome);
                System.out.println(jogador.getNome() + " o Guerreiro entrou na arena!");
                break;
            case 2:
                jogador = new Mago(nome);
                System.out.println(jogador.getNome() + " o Mago está pronto para a batalha!");
                break;
            default:
                System.out.println("Escolha inválida. Você será um Guerreiro padrão.");
                jogador = new Guerreiro("Herói");
                break;
        }
    }

    private void criarOponente() {
        // Criando um oponente aleatório
        int escolhaOponente = new Random().nextInt(2) + 1;
        if (escolhaOponente == 1) {
            oponente = new Guerreiro("Goblin");
            System.out.println("Um Goblin Guerreiro selvagem apareceu!");
        } else {
            oponente = new Mago("Esqueleto");
            System.out.println("Um Esqueleto Mago surge das sombras!");
        }
    }

    private void loopDeTurnos() {
        Personagem atacante = jogador;
        Personagem defensor = oponente;

        while (jogador.estaVivo() && oponente.estaVivo()) {
            System.out.println("\n--- Turno de " + atacante.getNome() + " ---");
            System.out.println(jogador.getNome() + " (HP: " + jogador.getHp() + ")");
            if (jogador instanceof Mago) {
                System.out.println("Mana: " + ((Mago) jogador).getMana());
            }
            System.out.println(oponente.getNome() + " (HP: " + oponente.getHp() + ")");
            if (oponente instanceof Mago) {
                System.out.println("Mana: " + ((Mago) oponente).getMana());
            }

            acoes(atacante, defensor);

            // Resetar defesas temporárias
            atacante.resetarDefesa();
            defensor.resetarDefesa();

            // Trocar atacante e defensor
            Personagem temp = atacante;
            atacante = defensor;
            defensor = temp;
        }
    }

    private void acoes(Personagem atacante, Personagem defensor) {
        System.out.println("Escolha sua ação:");
        System.out.println("1. Atacar");
        System.out.println("2. Usar Habilidade Especial");
        System.out.println("3. Defender");
        int escolha = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha

        switch (escolha) {
            case 1:
                atacante.atacar(defensor);
                break;
            case 2:
                atacante.usarHabilidadeEspecial(defensor);
                break;
            case 3:
                atacante.defender();
                break;
            default:
                System.out.println("Ação inválida. Você perdeu o turno.");
                break;
        }
    }

    private void finalizarJogo() {
        if (jogador.estaVivo()) {
            System.out.println("\nParabéns! " + jogador.getNome() + " venceu a batalha!");
        } else {
            System.out.println("\n" + oponente.getNome() + " derrotou " + jogador.getNome() + ".");
        }
        scanner.close();
    }

    public static void main(String[] args) {
        Jogo jogo = new Jogo();
        jogo.iniciarJogo();
    }
}