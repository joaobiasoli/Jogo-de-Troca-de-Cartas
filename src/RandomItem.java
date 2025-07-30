import java.util.*;

public class RandomItem {
    private static String[] nomes = {"Charmander", "Squirtle", "Bulbasaur", "Dragonite", "Mewtwo", "Gyarados", "Venusaur", "Blastoise", "Charizard", "Pikachu"};
    private static String[] tipos = {"Carta de fogo", "Carta de água", "Carta de planta", "Carta de dragão"};

    public static Item generateRandomItem(Jogador owner) {
        Random random = new Random();

        String nome = nomes[random.nextInt(nomes.length)];
        String tipo = tipos[random.nextInt(tipos.length)];
        int pc = random.nextInt(100);
        int valor = random.nextInt(1000);

        return new Item(nome, "Item gerado aleatoriamente", tipo, (double) valor, owner, pc);
    }
}