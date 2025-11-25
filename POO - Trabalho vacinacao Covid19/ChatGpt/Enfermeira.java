package ChatGpt;

public class Enfermeira {

    private String cpf;
    private String nome;

    public Enfermeira(String cpf, String nome) {
        this.cpf = cpf;
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return nome + " (CPF: " + cpf + ")";
    }
}