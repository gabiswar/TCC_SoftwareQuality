import java.util.Scanner;

abstract class Character {
    String name;
    int hp, strength, intelligence, defense;

    public Character(String name, int hp, int strength, int intelligence, int defense) {
        this.name = name;
        this.hp = hp;
        this.strength = strength;
        this.intelligence = intelligence;
        this.defense = defense;
    }

    public boolean isAlive() {
        return hp > 0;
    }

    public void takeDamage(int damage) {
        hp -= Math.max(0, damage - defense);
        if (hp < 0) hp = 0;
    }

    public abstract void specialAbility(Character opponent);

    public void defend() {
        defense += 5; // Defesa temporária
        System.out.println(name + " está se defendendo e aumentou a defesa temporariamente!");
    }

    public void attack(Character opponent) {
        int damage = strength;
        System.out.println(name + " atacou " + opponent.name + " causando " + damage + " de dano!");
        opponent.takeDamage(damage);
    }
}

class Warrior extends Character {
    public Warrior() {
        super("Guerreiro", 100, 15, 5, 10);
    }

    @Override
    public void specialAbility(Character opponent) {
        int damage = strength * 2;
        System.out.println(name + " usou Golpe Poderoso causando " + damage + " de dano!");
        opponent.takeDamage(damage);
    }
}

class Mage extends Character {
    public Mage() {
        super("Mago", 80, 5, 15, 5);
    }

    @Override
    public void specialAbility(Character opponent) {
        System.out.println(name + " usou Magia de Fogo causando " + intelligence * 2 + " de dano!");
        opponent.takeDamage(intelligence * 2);
    }

    @Override
    public void defend() {
        defense += 10; // Defesa temporária maior para o Mago
        System.out.println(name + " usou Escudo Mágico e aumentou a defesa temporariamente!");
    }
}

public class TurnBasedRPG {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Escolha seu personagem:");
        System.out.println("1. Guerreiro");
        System.out.println("2. Mago");
        int choice = scanner.nextInt();

        Character player;
        Character opponent;

        if (choice == 1) {
            player = new Warrior();
            opponent = new Mage();
        } else {
            player = new Mage();
            opponent = new Warrior();
        }

        System.out.println("Você escolheu: " + player.name);
        System.out.println("Seu oponente é: " + opponent.name);

        while (player.isAlive() && opponent.isAlive()) {
            System.out.println("\nSua vez! Escolha uma ação:");
            System.out.println("1. Atacar");
            System.out.println("2. Usar Habilidade Especial");
            System.out.println("3. Defender");
            int action = scanner.nextInt();

            switch (action) {
                case 1 -> player.attack(opponent);
                case 2 -> player.specialAbility(opponent);
                case 3 -> player.defend();
                default -> System.out.println("Ação inválida!");
            }

            if (!opponent.isAlive()) {
                System.out.println(opponent.name + " foi derrotado! Você venceu!");
                break;
            }

            System.out.println("\nTurno do oponente!");
            int opponentAction = (int) (Math.random() * 3) + 1;
            switch (opponentAction) {
                case 1 -> opponent.attack(player);
                case 2 -> opponent.specialAbility(player);
                case 3 -> opponent.defend();
            }

            if (!player.isAlive()) {
                System.out.println(player.name + " foi derrotado! Você perdeu!");
            }

            // Resetar defesa temporária
            player.defense = Math.max(player.defense - 5, 0);
            opponent.defense = Math.max(opponent.defense - 5, 0);
        }

        scanner.close();
    }
}