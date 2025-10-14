import java.util.*;

public class Jogar {
    ArrayList<Jogador> jogadores = new ArrayList<>();
    private int numeroRodads;
    private int dificuldade;

    

    public void cadastrarJogadores(Jogador j){
        jogadores.add(j);
    }

    public void iniciar(int numeroRodadas, int dificuldade){
        Scanner scanner = new Scanner(System.in);
        Pergunta p;

        for (int i=0;i<numeroRodadas;i++) {
            for (int a = 0; a < jogadores.size(); a++) {
                switch (dificuldade){
                    case 1: p = new PerguntaFacil();break;
                    case 2: p = new PerguntaMediana();break;
                    case 3: p = new PerguntaDificil();break;
                    default: p = new PerguntaFacil();break;

                }
                Jogador j = jogadores.get(a);
                System.out.println("--- Rodada numero " + i + " ---\nVez do jogador " + j.getId() + ", " + j.getNome() + ": ");

                p.criarPergunta();

                System.out.print(p.mostrarPergunta() + " = ");
                int respJogador = scanner.nextInt();
                if (p.verificarResposta(respJogador)){j.adicionarPontos(5);
                    System.out.println("Acertou!!!");}else {System.out.println("Errou!!!");}
                System.out.println("O jogador "+j.getId()+", "+j.getNome()+" tem "+j.getPontos()+" Pontos!");

            }
        }
    }
    public void pontuacaoFinal(){
        ArrayList<Integer> tabela = new ArrayList<>();
        for (int i=0;i<jogadores.size();i++){
            Jogador j = jogadores.get(i);
            tabela.add(j.getPontos());
        }
        Collections.sort(tabela, Comparator.reverseOrder());
        System.out.println("--- Tabela final ---");
        for (int i=0;i<tabela.size();i++){
            Jogador j = jogadores.get(i);

            System.out.println(i+". Jogador "+j.getId()+", "+j.getNome()+" :"+tabela.get(i));
        }
    }
    public void criarJogadoresAutomaticamente(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("-> Digite quantos jogadores esta partida tera: ");
        int quantJog = scanner.nextInt();

        System.out.println("-> Digite o numero de rodadas que a partida tera: ");
        numeroRodads = scanner.nextInt();
        scanner.nextLine();


        System.out.println("-> Digite a dificuldade da partida;\n1- Facil\n2- Mediana\n3- Dificil\n");
        dificuldade = scanner.nextInt();
        scanner.nextLine();

        for (int i=0; i<quantJog;i++){
            System.out.println("-> Digite o nome do jogador "+i+": ");
            String nomeJogador = scanner.nextLine();
            Jogador j = new Jogador(i + 1, nomeJogador);
            cadastrarJogadores(j);





        }
    }

    public int getNumeroRodads() {return numeroRodads;}
    public int getDificuldade() {return dificuldade;}
}
