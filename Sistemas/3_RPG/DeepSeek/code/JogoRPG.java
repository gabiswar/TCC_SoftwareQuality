import java.util.Scanner;

abstract class Personagem {
    protected String nome;
    protected int hp;
    protected int hpMax;
    protected int forca;
    protected int inteligencia;
    protected int defesa;
    protected int defesaBase;
    protected boolean defendendo;

    public Personagem(String nome, int hp, int forca, int inteligencia, int defesa) {
        this.nome = nome;
        this.hp = hp;
        this.hpMax = hp;
        this.forca = forca;
        this.inteligencia = inteligencia;
        this.defesa = defesa;
        this.defesaBase = defesa;
        this.defendendo = false;
    }

    public void receberDano(int dano) {
        int danoReduzido = Math.max(1, dano - defesa);
        hp -= danoReduzido;
        System.out.println(nome + " recebeu " + danoReduzido + " de dano!");
        if (hp <= 0) {
            hp = 0;
            System.out.println(nome + " foi derrotado!");
        }
    }

    public void defender() {
        defendendo = true;
        defesa = defesaBase + (int)(defesaBase * 0.5);
        System.out.println(nome + " está defendendo! Defesa aumentada para " + defesa);
    }

    public void resetDefesa() {
        defendendo = false;
        defesa = defesaBase;
    }

    public boolean estaVivo() {
        return hp > 0;
    }

    public abstract void atacar(Personagem alvo);
    public abstract void usarHabilidadeEspecial(Personagem alvo);

    public void mostrarStatus() {
        System.out.println("\n" + nome + " - HP: " + hp + "/" + hpMax + 
                         " | Força: " + forca + " | Inteligência: " + inteligencia + 
                         " | Defesa: " + defesa + (defendendo ? " (Defendendo)" : ""));
    }
}

class Guerreiro extends Personagem {
    public Guerreiro() {
        super("Guerreiro", 120, 15, 5, 10);
    }

    @Override
    public void atacar(Personagem alvo) {
        int dano = forca;
        System.out.println(nome + " ataca com sua espada causando " + dano + " de dano!");
        alvo.receberDano(dano);
    }

    @Override
    public void usarHabilidadeEspecial(Personagem alvo) {
        int dano = (int)(forca * 1.8);
        System.out.println(nome + " usa Golpe Poderoso causando " + dano + " de dano!");
        alvo.receberDano(dano);
    }
}

class Mago extends Personagem {
    private boolean defesaMagicaAtiva;

    public Mago() {
        super("Mago", 80, 5, 18, 5);
        defesaMagicaAtiva = false;
    }

    @Override
    public void atacar(Personagem alvo) {
        int dano = inteligencia / 2;
        System.out.println(nome + " lança um feixe mágico causando " + dano + " de dano!");
        alvo.receberDano(dano);
    }

    @Override
    public void usarHabilidadeEspecial(Personagem alvo) {
        // O mago pode escolher entre atacar ou se defender na habilidade especial
        Scanner scanner = new Scanner(System.in);
        System.out.println("Escolha a habilidade especial:");
        System.out.println("1 - Bola de Fogo (causa dano mágico)");
        System.out.println("2 - Campo de Força (aumenta defesa)");
        System.out.print("Opção: ");
        int escolha = scanner.nextInt();

        if (escolha == 1) {
            int dano = inteligencia * 2;
            System.out.println(nome + " lança uma Bola de Fogo causando " + dano + " de dano!");
            alvo.receberDano(dano);
        } else if (escolha == 2) {
            defesaMagicaAtiva = true;
            defesa = defesaBase * 3;
            System.out.println(nome + " conjura um Campo de Força! Defesa aumentada para " + defesa);
        } else {
            System.out.println("Opção inválida! Habilidade especial falhou.");
        }
    }

    @Override
    public void resetDefesa() {
        super.resetDefesa();
        if (defesaMagicaAtiva) {
            defesaMagicaAtiva = false;
            defesa = defesaBase;
        }
    }
}

public class JogoRPG {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Bem-vindo ao RPG Turn-Based!");
        System.out.println("Escolha seu personagem:");
        System.out.println("1 - Guerreiro");
        System.out.println("2 - Mago");
        System.out.print("Opção: ");
        int escolha = scanner.nextInt();
        
        Personagem jogador, inimigo;
        
        if (escolha == 1) {
            jogador = new Guerreiro();
            inimigo = new Mago();
        } else {
            jogador = new Mago();
            inimigo = new Guerreiro();
        }
        
        System.out.println("\nVocê escolheu jogar com " + jogador.nome + "!");
        System.out.println("Seu oponente será " + inimigo.nome + ".\n");
        
        // Loop principal do jogo
        while (jogador.estaVivo() && inimigo.estaVivo()) {
            // Turno do jogador
            System.out.println("\n=== Seu turno ===");
            jogador.mostrarStatus();
            inimigo.mostrarStatus();
            
            System.out.println("\nEscolha sua ação:");
            System.out.println("1 - Atacar");
            System.out.println("2 - Usar Habilidade Especial");
            System.out.println("3 - Defender");
            System.out.print("Opção: ");
            int acao = scanner.nextInt();
            
            switch (acao) {
                case 1:
                    jogador.atacar(inimigo);
                    break;
                case 2:
                    jogador.usarHabilidadeEspecial(inimigo);
                    break;
                case 3:
                    jogador.defender();
                    break;
                default:
                    System.out.println("Opção inválida! Você perdeu o turno.");
            }
            
            // Verifica se o inimigo foi derrotado
            if (!inimigo.estaVivo()) {
                break;
            }
            
            // Turno do inimigo (IA simples)
            System.out.println("\n=== Turno do inimigo ===");
            int acaoInimigo = (int)(Math.random() * 3) + 1;
            
            switch (acaoInimigo) {
                case 1:
                    inimigo.atacar(jogador);
                    break;
                case 2:
                    inimigo.usarHabilidadeEspecial(jogador);
                    break;
                case 3:
                    inimigo.defender();
                    break;
            }
            
            // Reseta defesas para o próximo turno
            jogador.resetDefesa();
            inimigo.resetDefesa();
        }
        
        // Fim do jogo
        System.out.println("\n=== Fim do jogo ===");
        if (jogador.estaVivo()) {
            System.out.println("Parabéns! Você venceu!");
        } else {
            System.out.println("Você foi derrotado...");
        }
        
        scanner.close();
    }
}