import java.lang.Math;


public class Item {

    private int id;
    private String nome;
    private String descricao;
    private String tipo;
    private double valor;
    private Jogador dono;
    private int pc;

    public Item(String nome, String descricao, String tipo, double valor, Jogador dono, int pc) {
        this.id = randCode();
        this.nome = nome;
        this.descricao = descricao;
        this.tipo = tipo;
        this.valor = valor;
        this.dono = dono;
        this.pc = pc;
    }


    public static int randCode() {
        int codigo;
        do {
            codigo = (int) (Math.random() * 999999);
        } while (codigo < 100000);
        return codigo;
    }

    public double getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Jogador getDono() {
        return dono;
    }

    public void setDono(Jogador dono) {
        this.dono = dono;
    }

    public int getPC() {
        return pc;
    }

    public void setPC(int pc) {
        this.pc = pc;
    }

    public String toString() {
        return "Nome: "+nome + "\nDescrição: " + descricao + "\nTipo: " + tipo + "\nValor: " + valor;
    }
}