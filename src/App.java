import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class App {
    private final Scanner scanner = new Scanner(System.in);
    private final CadastroJogadores cadastroJogadores = new CadastroJogadores();
    private final CadastroItens cadastroItens = new CadastroItens();
    private Jogador jogadorLogado;

    public void executar() {
        boolean continuar = true;

        while (continuar) {
            exibirMenuInicial();
            int opcao = lerOpcaoUsuario();

            switch (opcao) {
                case 1:
                    boolean logado = logarJogador();

                    continuar = ! logado; // se conseguiu logar, quebra o while e entra no perfil
                    break;
                case 2:
                    cadastrarJogador();
                    break;
                default:
                    continuar = false;
                    break;
            }
        }

        if (jogadorLogado != null) {
            gerenciarOpcoesJogador();
        }
    }

    private void exibirMenuInicial() {
        System.out.println("==============================================");
        System.out.println("1. Entrar");
        System.out.println("2. Cadastrar");
        System.out.println("Outra opção. Sair");
    }

    private int lerOpcaoUsuario() {
        int opcao = -1;
        try {
            opcao = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida. Por favor, insira um número.");
            scanner.next();
        }
        return opcao;
    }

    private boolean logarJogador() {
        System.out.println("Digite o email do jogador: ");
        String email = scanner.next();
        System.out.println("Digite o pin do jogador: ");
        String pin = scanner.next();

        jogadorLogado = cadastroJogadores.getJogador(email, pin);

        if (jogadorLogado != null) {
            System.out.println("Login bem-sucedido!");
            System.out.println("Logado em " + jogadorLogado.toString());

            return true;
        } else {
            System.out.println("Email ou PIN incorretos!");

            return false;
        }
    }

    private void cadastrarJogador() {
        System.out.println("Digite o nome do jogador: ");
        String nome = scanner.next();
        System.out.println("Digite o email do jogador: ");
        String email = scanner.next();
        System.out.println("Digite o pin do jogador: ");
        String pin = scanner.next();

        jogadorLogado = new Jogador(email, nome, pin);
        cadastroJogadores.addJogador(jogadorLogado);
        System.out.println("Jogador cadastrado com sucesso!");
    }

    private void gerenciarOpcoesJogador() {
        boolean cond = true;

        while (cond) {
            System.out.println("Escolha uma das opções (1-15): ");
            System.out.println("1. Cadastrar item");
            System.out.println("2. Excluir item");
            System.out.println("3. Listar itens do jogador");
            System.out.println("4. Listar itens dos outros jogadores por preço");
            System.out.println("5. Buscar item");
            System.out.println("6. Fazer proposta");
            System.out.println("7. Listar propostas");
            System.out.println("8. Exibir estatísticas gerais");
            System.out.println("9. Exibir carta com mais PC e dono");
            System.out.println("10. Exibir cartas de um tipo");
            System.out.println("11. Obter Lootbox (Item aleatório)");
            System.out.println("12. Abrir Lootbox (Item aleatório)");
            System.out.println("13. Editar item existente");
            System.out.println("14. Checar se o item existe");
            System.out.println("15. Listar seus itens em ordem alfabética: ");
            System.out.println("Qualquer outra opção. Sair");
            int opc = scanner.nextInt();

            switch (opc) {
                case 1:
                    Item i = itemInput();
                    jogadorLogado.addItem(i);
                    cadastroItens.addItem(i); // Atualizado para usar o objeto correto
                    break;

                case 2:
                    System.out.println("Escolha um item para remover:\n");
                    jogadorLogado.printItens();
                    int posi = (scanner.nextInt()) - 1;

                    i = jogadorLogado.getItem(posi);
                    jogadorLogado.removeItem(i);
                    cadastroItens.removeItem(i); // Atualizado para usar o objeto correto
                    break;

                case 3:
                    System.out.println("Itens do Jogador:\n");
                    jogadorLogado.printItens();
                    break;

                case 4:
                    String itensOutrosJogadores = cadastroJogadores.listarItensDeOutrosJogadores(jogadorLogado);
                    if (itensOutrosJogadores.equals("Nenhum item encontrado de outros jogadores.")) {
                        System.out.println(itensOutrosJogadores);
                    } else {
                        System.out.println(itensOutrosJogadores);
                    }
                    break;

                case 5:
                    System.out.println("Digite o nome, descrição ou tipo para encontrar:");
                    String busca = scanner.next();
                    ArrayList<Item> itensEncontrados = cadastroItens.buscarItens(busca);
                    if (itensEncontrados.isEmpty()) {
                        System.out.println("Nenhum item encontrado");
                    } else {
                        System.out.println("Itens buscados:");
                        for (Item itensEncontrado : itensEncontrados) {
                            System.out.println(itensEncontrado.toString());
                        }
                    }
                    break;


                case 6:
                    System.out.println("Selecione um item seu:");
                    cadastroItens.printAllItens();
                    String itemEscolhido = scanner.next();
                    System.out.println("Selecione um item de outro jogador:");
                    cadastroItens.printAllItens();
                    String itemEscolhido2 = scanner.next();

                    Jogador dono = cadastroItens.searchItens(itemEscolhido2).getDono();
                    Item itemOferecido = cadastroItens.searchItens(itemEscolhido);
                    Item itemRecebe = cadastroItens.searchItens(itemEscolhido2);
                    Proposta p1 = new Proposta(jogadorLogado,dono,itemOferecido,itemRecebe);
                    jogadorLogado.addProposta(p1);

                    break;

                case 7:

                    jogadorLogado.listarPropostas();
                    posi = (scanner.nextInt());
                    Proposta p = jogadorLogado.getProposta(posi);
                    System.out.println(p.toString());
                    System.out.println("Deseja aceitar a proposta?");
                    System.out.println("1. Sim");
                    System.out.println("2. Não");
                    System.out.println("Qualquer outra opção. Sair");
                    opc = scanner.nextInt();

                    switch (opc) {
                        case 1:
                            jogadorLogado.trocaAceita(p);
                            break;
                        case 2:
                            jogadorLogado.recusaProp(p);
                            break;
                        default:
                            cond = false;
                            break;
                    }
                    break;

                case 8:
                    estatisticas();
                    System.out.println("Estatísticas gerais ainda não implementadas.");
                    break;

                case 9:

                    System.out.println(cadastroItens.cartaMaisPC().toString());
                    System.out.println(cadastroItens.cartaMaisPC().getDono().toString());
                    break;

                case 10:

                    String tipo = tipoItem();
                    cadastroItens.printItens(tipo);
                    break;

                case 11:

                    Item item = new Item("Lootbox", "Concebe um item aleatório ao abrir", "Lootbox", 100, jogadorLogado, 0);
                    jogadorLogado.addItem(item);

                    System.out.println("Lootbox obtida!");

                    break;

                case 12:

                    ArrayList<Item> inventory = jogadorLogado.getItens();
                    for (Item itemInventario : inventory) {
                        if (!itemInventario.getTipo().equals("Lootbox")) {
                            continue;
                        }

                        Lootbox lootbox = new Lootbox();
                        lootbox.openLootbox();
                        if (lootbox.getItem() == null) {
                            break;
                        } else {
                            System.out.println("Item obtido: " + lootbox.getItem());
                        }
                        jogadorLogado.addItem(lootbox.getItem());

                        jogadorLogado.removeItem(itemInventario);
                        break;
                    }
                    break;

                case 13:
                    System.out.println("Digite o NOME do item a ser editado:");
                    String nome = scanner.next();
                    Item itemAux = cadastroItens.editItem(itemInput(), nome);
                    System.out.println("Item atualizado: " + itemAux);
                    break;

                case 14:
                    System.out.println("Digite o nome do item: ");
                    nome = scanner.next();
                    Item item1 = cadastroItens.getByName(nome);
                    if (item1 == null) {
                        System.out.println("Item de nome: " + nome + " não existe.");
                    } else {
                        System.out.println("O item: \n" + item1 + "\nexiste.");
                    }
                    break;

                case 15:
                    System.out.println("Digite seu pin para gerar a lista de seu itens em ordem alfabética: ");
                    String pin = scanner.next();
                    String itens = cadastroJogadores.listarItensJogadores(pin);
                    System.out.println(itens); // Imprime a lista de itens
                    break;

                default:
                    cond = false;
                    break;
            }
        }

        executar(); //continua fluxo do programa, permite cadastrar outros jogadores e logar em outras contas
    }

    private Item itemInput() {
        System.out.println("Digite o nome do item: ");
        String nome = scanner.next();
        System.out.println("Digite a descrição do item: ");
        String descricao = scanner.next();
        System.out.println("Digite o valor do item: ");
        double valor = scanner.nextDouble();
        String tipo = tipoItem();
        System.out.println("Digite o PC (Pontos de Combate) do item: ");
        int pc = scanner.nextInt();

        return new Item(nome, descricao, tipo, valor, jogadorLogado, pc);
    }

    private String tipoItem() {
        System.out.println("Diga da carta do item: ");
        System.out.println("1. Carta de fogo");
        System.out.println("2. Carta de água");
        System.out.println("3. Carta de planta");
        System.out.println("4. Carta de dragão");
        int opcaux = scanner.nextInt();

        String tipo = "";

        switch (opcaux) {
            case 1:
                tipo = "Carta de fogo";
                break;
            case 2:
                tipo = "Carta de água";
                break;
            case 3:
                tipo = "Carta de dragão";
                break;
            case 4:
                tipo = "Carta de planta";
                break;
            default:
                tipo = "Tipo desconhecido";
                break;
        }
        System.out.println("================================");
        return tipo;
    }


    public void popularCadastroJogadores() {
        int quantJogadores = 20;
        for (int i = 0; i < quantJogadores; i++) {
            Jogador jogador = new Jogador("jogador" + i + "@email", "jogador" + i, Integer.toString(1000 + i));
            cadastroJogadores.addJogador(jogador);
        }
    }

    public void popularCadastroItens() {
        int quantItens = 50;
        for (int i = 0; i < quantItens; i++) {
            Jogador jogador = cadastroJogadores.getRandomPlayer();
            Item item = RandomItem.generateRandomItem(jogador);
            jogador.addItem(item);
            cadastroItens.addItem(item);
        }
    }
  
    private void estatisticas() {
        totalUsuarios();
        System.out.println("===========================");
        totalItens();
        System.out.println("===========================");
        statusPropostas();
    }

    private void totalUsuarios() {
        CadastroJogadores cadJogadores = new CadastroJogadores();
        ArrayList<Jogador> jogadores = cadJogadores.getJogadores();
        System.out.println("O numero total de usuarios eh de: " + jogadores.size());
    }

    private void totalItens(){
        CadastroItens cadItens = new CadastroItens();
        ArrayList<Item> itens = cadItens.getItens();
        double valor = 0;
        for(Item i : itens){
            valor += i.getValor();
        }
        System.out.println("Numero total de itens: " + itens.size());
        System.out.println("Valor total dos itens: " + valor);
    }
    private void statusPropostas(){
        int aceitas = 0,recusadas = 0,pendentes = 0;
        CadastroJogadores cadJogadores = new CadastroJogadores();
        ArrayList<Jogador> jogadores = cadJogadores.getJogadores();
        for(Jogador j : jogadores){
            ArrayList<Proposta> propostas = j.getPropostas();
            for(Proposta p : propostas){
                if(p.status == null){
                    pendentes++;
                }else if(p.status){
                    aceitas++;
                }else{
                    recusadas++;
                }
            }
        }
    }
}