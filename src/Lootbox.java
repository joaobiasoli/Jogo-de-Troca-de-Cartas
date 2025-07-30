import java.util.*;

public class Lootbox {
    private CadastroItens cadastroItens = new CadastroItens();
    private boolean isOpen;
    private Item item;

    public Lootbox() {
        this.isOpen = false;
    }

    public void openLootbox() {
        if (isOpen) { //cant open twice
            return;
        }

        this.isOpen = true;
        this.item = getRandomItem();
    }

    private Item getRandomItem() {
        ArrayList<Item> itens = cadastroItens.getItens();
        
        if (itens.size() == 0) {
            System.out.println("Lootboxes só podem ser abertas com pelo menos um item registrado");

            return null;
        }

        return itens.get((int) Math.random() * itens.size());
    }

    public Item getItem() {
        return this.item;
    }
}
