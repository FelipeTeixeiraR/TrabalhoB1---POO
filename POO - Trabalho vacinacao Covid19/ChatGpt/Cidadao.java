package ChatGpt;

import java.time.LocalDate;

public class Cidadao {

    private String cpf;
    private String nome;
    private LocalDate dataDose1;
    private LocalDate dataDose2;
    private LocalDate dataDose3;
    private LocalDate dataDose4;

    public Cidadao(String cpf, String nome,
                   LocalDate dataDose1,
                   LocalDate dataDose2,
                   LocalDate dataDose3,
                   LocalDate dataDose4) {
        this.cpf = cpf;
        this.nome = nome;
        this.dataDose1 = dataDose1;
        this.dataDose2 = dataDose2;
        this.dataDose3 = dataDose3;
        this.dataDose4 = dataDose4;
    }

    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public LocalDate getDataDose1() {
        return dataDose1;
    }

    public LocalDate getDataDose2() {
        return dataDose2;
    }

    public LocalDate getDataDose3() {
        return dataDose3;
    }

    public LocalDate getDataDose4() {
        return dataDose4;
    }
}