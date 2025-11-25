package Gemini;

public class Enfermeira {
    private String cpf;
    private String nome;

    // Construtor
    public Enfermeira(String cpf, String nome) {
        this.cpf = cpf;
        this.nome = nome;
    }

    // Getters
    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }
    
    // Método para exibir informações da enfermeira logada
    public String getInfo() {
        return "Enfermeira: " + nome + " (CPF: " + cpf + ")";
    }
}