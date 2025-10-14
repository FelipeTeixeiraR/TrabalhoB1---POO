import java.util.Random;

public class PerguntaFacil extends Pergunta{



    public PerguntaFacil(String textoConta, int resposta){
        super(textoConta, resposta);
    }

    public PerguntaFacil() {

    }

    @Override
    public void criarPergunta(){
        Random random = new Random();
        int tconta = random.nextInt(4);
        int a, b;

        switch (tconta){
            case 0: a = random.nextInt(50, 200);
                b = random.nextInt(50, 200);
                setResposta(a + b);
                setTextoConta(a + " + " + b);
                break;
                case 1: a = random.nextInt(100, 300);
                b = random.nextInt(50, a);

                setResposta(a - b);
                setTextoConta(a + " - " + b);
                break;
            case 2: a = random.nextInt(10, 20);
                b = random.nextInt(10, 20);
                setResposta(a * b);
                setTextoConta(a + " * " + b);
                break;
            case 3: a = random.nextInt(20, 100);
                do {
                    b = random.nextInt(2, 5);
                } while (a % b != 0);

                setResposta(a / b);
                setTextoConta(a + " / " + b);
                break;
        }
    }
    @Override
    public String mostrarPergunta(){
        return getTextoConta();
    }
}
