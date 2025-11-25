package Gemini;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Cidadao {
    private String cpf;
    private String nome;
    // Lista para armazenar as datas das doses.
    // O índice corresponde à dose (0 -> 1ª dose, 1 -> 2ª dose, etc.)
    private List<LocalDate> datasDoses;

    // Construtor
    public Cidadao(String cpf, String nome) {
        this.cpf = cpf;
        this.nome = nome;
        this.datasDoses = new ArrayList<>();
    }

    // Método para adicionar uma nova dose
    public void adicionarDose(LocalDate data) {
        this.datasDoses.add(data);
    }

    // Getters
    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public List<LocalDate> getDatasDoses() {
        return datasDoses;
    }

    // Método de impressão formatada para a lista de cidadãos
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("CPF: ").append(cpf).append(", Nome: ").append(nome);
        
        // Loop para listar as doses
        for (int i = 0; i < datasDoses.size(); i++) {
            sb.append("\n  - ").append(i + 1).append("ª Dose: ").append(datasDoses.get(i));
        }
        
        return sb.toString();
    }
}