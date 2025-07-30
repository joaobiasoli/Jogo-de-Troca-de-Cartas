import java.util.ArrayList;
import java.util.Scanner;

public class Jogador {
    Scanner scanner = new Scanner(System.in);
    private String email;
    private String nome;
    private String pin; //IDENTIFICADOR, CADA UM TEM O SEU!
    private ArrayList<Item> itens = new ArrayList<>();
    private ArrayList<Proposta> propostas = new ArrayList<>();

    public Jogador(String email, String nome, String pin) {
        this.email = email;
        this.nome = nome;
        this.pin = pin;
    }

    public void addItem(Item i) {
        itens.add(i);
    }

    public void removeItem(int posi) {
        itens.remove(posi);
    }

    public void removeItem(Item i) {
        itens.remove(i);
    }

    public void printItens() {
        int cont = 1;
        for (Item i : itens) {
            System.out.println(cont + ". " + i.toString());
            cont++;
        }
    }

    public Item getItem(int posi) {
        return itens.get(posi);
    }

    public Item getItem(Item item) {
        for (Item i : itens) {
            if (i.equals(item)) {
                return i;
            }
        }
        return null;
    }


    // Método para retornar os itens do jogador - Lucas Simao
    public ArrayList<Item> getItens() {
        return itens;
    }

    public void setPin(String pin) {
        if (pin.length() != 6){
            System.out.println("Digite o pin do jogador: (deve possuir 6 digitos)");
            pin = scanner.next();
            setPin(pin);
        } else {
            this.pin = pin;
        }
    }
    public void addProposta(Proposta proposta) {
        propostas.add(proposta);
    }

    public Proposta getProposta(int posi) {
        return propostas.get(posi);
    }

    public void recusaProp(Proposta proposta) {proposta.status = false; }


    //lista proposta se o jogador possuir
    //printa o index da proposta no array para o jogador poder integir com a proposta posteriormente
    public void listarPropostas() {
        if (!propostas.isEmpty()) {
            for (Proposta proposta : propostas) {
                System.out.println(propostas.indexOf(proposta));
                System.out.println(proposta.toString());
            }
        }
        else
        {
            System.out.println("Jogador não possui propostas.");
        }
    }

    public void trocaAceita(Proposta proposta) {
        proposta.status = true;
        proposta.propAceita(proposta);
        recusaProp(proposta);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPin() {
        return pin;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String toString() {
        return "Nome: " + nome + "\nEmail: " + email + "\nPin: " + pin;
    }

    public ArrayList<Proposta> getPropostas() { return propostas;}
}