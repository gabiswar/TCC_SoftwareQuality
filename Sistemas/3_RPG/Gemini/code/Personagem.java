import java.util.Random;
import java.util.Scanner;

public abstract class Personagem {
    protected String nome;
    protected int hp;
    protected int forca;
    protected int inteligencia;
    protected int defesa;
    protected Random random = new Random();
    protected Scanner scanner = new Scanner(System.in);

    public Personagem(String nome, int hp, int forca, int inteligencia, int defesa) {
        this.nome = nome;
        this.hp = hp;
        this.forca = forca;
        this.inteligencia = inteligencia;
        this.defesa = defesa;
    }

    public String getNome() {
        return nome;
    }

    public int getHp() {
        return hp;
    }

    public int getForca() {
        return forca;
    }

    public int getInteligencia() {
        return inteligencia;
    }

    public int getDefesa() {
        return defesa;
    }

    public void receberDano(int dano) {
        int danoReduzido = Math.max(0, dano - defesa);
        this.hp -= danoReduzido;
        System.out.println(nome + " recebeu " + danoReduzido + " de dano.");
        if (this.hp <= 0) {
            System.out.println(nome + " foi derrotado!");
        }
    }

    public boolean estaVivo() {
        return hp > 0;
    }

    public abstract void atacar(Personagem alvo);
    public abstract void usarHabilidadeEspecial(Personagem alvo);
    public void defender() {
        int defesaTemporaria = 5 + random.nextInt(5); // Defesa aumenta aleatoriamente
        this.defesa += defesaTemporaria;
        System.out.println(nome + " defendeu, aumentando sua defesa em " + defesaTemporaria + ".");
    }

    public void resetarDefesa() {
        // Método para ser sobrescrito pelas subclasses, caso a defesa seja temporária
    }
}