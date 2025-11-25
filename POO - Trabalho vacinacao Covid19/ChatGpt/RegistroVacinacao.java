package ChatGpt;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RegistroVacinacao {

    private Enfermeira enfermeira;
    private List<Cidadao> cidadaos;
    private DateTimeFormatter formatter;

    public RegistroVacinacao(Enfermeira enfermeira) {
        this.enfermeira = enfermeira;
        this.cidadaos = new ArrayList<>();
        this.formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    }

    /**
     * Fluxo completo para vacinar um cidadão:
     * - Ler dados
     * - Validar intervalos entre doses
     * - Se ok, salvar na lista
     */
    public void vacinarCidadao(Scanner scanner) {
        System.out.println("\n=== Vacinar cidadão ===");

        System.out.print("Informe o CPF do cidadão: ");
        String cpf = scanner.nextLine().trim();

        System.out.print("Informe o nome do cidadão: ");
        String nome = scanner.nextLine().trim();

        LocalDate dose1 = lerData(scanner, "Informe a data da 1ª dose (dd/MM/yyyy): ");
        if (dose1 == null) return;

        LocalDate dose2 = lerData(scanner, "Informe a data da 2ª dose (dd/MM/yyyy): ");
        if (dose2 == null) return;

        LocalDate dose3 = lerData(scanner, "Informe a data da 3ª dose (dd/MM/yyyy): ");
        if (dose3 == null) return;

        LocalDate dose4 = lerData(scanner, "Informe a data da 4ª dose (dd/MM/yyyy): ");
        if (dose4 == null) return;

        if (!validarIntervalos(dose1, dose2, dose3, dose4)) {
            System.out.println("O cidadão ainda não tem direito à nova dose.");
            System.out.println("Registro NÃO foi salvo.\n");
            return;
        }

        Cidadao cidadao = new Cidadao(cpf, nome, dose1, dose2, dose3, dose4);
        cidadaos.add(cidadao);

        System.out.println("Cidadão vacinado e cadastrado com sucesso!\n");
    }

    /**
     * Lista todos os cidadãos já cadastrados com as datas das doses.
     */
    public void listarCidadaosVacinados() {
        System.out.println("\n=== Lista de cidadãos vacinados ===");

        if (cidadaos.isEmpty()) {
            System.out.println("Nenhum cidadão vacinado cadastrado.\n");
            return;
        }

        System.out.println("Enfermeira responsável: " + enfermeira);
        System.out.println("----------------------------------------");

        for (Cidadao c : cidadaos) {
            System.out.println("Nome: " + c.getNome());
            System.out.println("CPF : " + c.getCpf());
            System.out.println("1ª dose: " + c.getDataDose1().format(formatter));
            System.out.println("2ª dose: " + c.getDataDose2().format(formatter));
            System.out.println("3ª dose: " + c.getDataDose3().format(formatter));
            System.out.println("4ª dose: " + c.getDataDose4().format(formatter));
            System.out.println("----------------------------------------");
        }
        System.out.println();
    }

    /**
     * Lê uma data do teclado no formato dd/MM/yyyy.
     * Se for inválida, volta ao menu principal (retorna null).
     */
    private LocalDate lerData(Scanner scanner, String mensagem) {
        System.out.print(mensagem);
        String textoData = scanner.nextLine().trim();
        try {
            return LocalDate.parse(textoData, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Data inválida! Operação cancelada, voltando ao menu.\n");
            return null;
        }
    }

    /**
     * Valida se as doses 2, 3 e 4 respeitam o intervalo mínimo de 4 meses
     * em relação à dose anterior.
     *
     * Inferior a 4 meses => inválido.
     * Igual ou superior a 4 meses => válido.
     */
    private boolean validarIntervalos(LocalDate d1, LocalDate d2, LocalDate d3, LocalDate d4) {

        if (d2.isBefore(d1.plusMonths(4))) {
            System.out.println("A 2ª dose está com menos de 4 meses de intervalo em relação à 1ª dose.");
            return false;
        }

        if (d3.isBefore(d2.plusMonths(4))) {
            System.out.println("A 3ª dose está com menos de 4 meses de intervalo em relação à 2ª dose.");
            return false;
        }

        if (d4.isBefore(d3.plusMonths(4))) {
            System.out.println("A 4ª dose está com menos de 4 meses de intervalo em relação à 3ª dose.");
            return false;
        }

        return true;
    }
}