import java.util.Random;

public class Pergunta {
    private int n1;
    private int n2;
    private String textoConta;
    private int resposta;

    public Pergunta(){}

    public Pergunta(int n1, int n2, String textoConta, int respota){
        this.n1 = n1;
        this.n2 = n2;
        this.textoConta = textoConta;
        this.resposta = resposta;
    }

    public Pergunta(String textoConta, int resposta) {
    }


    public void criarPergunta(){}

    public String mostrarPergunta(){
        return textoConta;
    }
    public Boolean verificarResposta(int respJogador){
        if (resposta == respJogador){
            return true;
        }
        else {
            return false;
        }
    }

    public int getResposta() {return this.resposta;}
    public String getTextoConta() {return this.textoConta;}

    public void setResposta(int resposta) {this.resposta = resposta;}
    public void setTextoConta(String textoConta) {this.textoConta = textoConta;}
}
