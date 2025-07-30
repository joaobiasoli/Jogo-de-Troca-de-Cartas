import java.util.ArrayList;
import java.util.Comparator;


public class CadastroItens {
    private static ArrayList<Item> itens = new ArrayList<>();
    ArrayList<Item> itensOrdenadosPeloValor;


    public CadastroItens() {
        this.itensOrdenadosPeloValor = new ArrayList<>(itens);
    }

    public void addItem(Item i) {
        itens.add(i);
    }

    public void removeItem(Item i) {
        itens.remove(i);
    }

    public void printItens(Jogador j) {
        int cont = 1;
        for (Item i : itens) {
            if (j.getItem(cont - 1) != i) {
                System.out.println(cont + ". " + i.toString());
                cont++;
            }
        }
    }

    public void printItens(String tipo) {
        int cont = 1;
        for (Item i : itens) {
            if (tipo.equals(i.getTipo())) {
                System.out.println(cont + ". " + i.toString());
                cont++;
            }
        }
    }

    public Item searchItens(String nomeItem) {
        for (Item i : itens) {
            if (nomeItem.equals(i.getNome())) {
                return i;
            }
        } return null;
    }

    public Item cartaMaisPC() {
        Item maisPC = itens.get(0);
        for (Item i : itens) {
            if (i.getPC() > maisPC.getPC()) {
                maisPC = i;
            }
        }
        return maisPC;
    }

    public boolean cadastrarItem(Item item) {
        if (itens.contains(item)) {
            return false;
        } else
            itens.add(item);
        return true;
    }

    public Item getByName(String name){
        for (Item item : itens) {
            if (item.getNome().equals(name)) {
                return item;
            }
        }
        return null;
    }

    public ArrayList<Item> getItens() {
        return itens;
    }
//João Biasoli = busca itens pelo nome, descrição e categoria.
    public ArrayList<Item> buscarItens(String busca) {
        ArrayList<Item> itensEncontrados = new ArrayList<>();

        for (int j = 0; j < itens.size(); j++) {
            Item i = itens.get(j);
            boolean encontrado = false;

            for (int k = 0; k <= i.getNome().length() - busca.length(); k++) {
                if (i.getNome().substring(k, k + busca.length()).equals(busca)) {
                    encontrado = true;
                    break;
                }
            }
            if (!encontrado) {
                for (int k = 0; k <= i.getDescricao().length() - busca.length(); k++) {
                    if (i.getDescricao().substring(k, k + busca.length()).equals(busca)) {
                        encontrado = true;
                        break;
                    }
                }
            }
            if (!encontrado) {
                for (int k = 0; k <= i.getTipo().length() - busca.length(); k++) {
                    if (i.getTipo().substring(k, k + busca.length()).equals(busca)) {
                        encontrado = true;
                        break;
                    }
                }
            }
          
            if (encontrado) {
                itensEncontrados.add(i);
            }
        }
        return itensEncontrados;
    }
  
  public Item editItem(Item newItem, String nome) {
        for (Item i : itens) {
            if (i.getNome().equals(nome)) {
                i.setDescricao(newItem.getDescricao());
                i.setNome(newItem.getNome());
                i.setPC(newItem.getPC());
                i.setTipo(newItem.getTipo());
                i.setValor(newItem.getValor());
                i.setDono(newItem.getDono());
                return i;
            }
        }
        return null;
    }

    public ArrayList<Item> listarItemsPorValor() {
        ArrayList<Item> itensOrdenadosPeloValor = new ArrayList<>(itens);
        itensOrdenadosPeloValor.sort(Comparator.comparing(Item::getValor));
        return itensOrdenadosPeloValor;
    }
    public void printAllItens(){
        for (Item i : itens){
            System.out.println(i.getDono()+" : "+i.getNome()+" : "+i.getValor());
        }
    }
}