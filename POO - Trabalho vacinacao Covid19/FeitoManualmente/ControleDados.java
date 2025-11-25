
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Scanner;

public class ControleDados {

    Scanner scanner = new Scanner(System.in);

    private ArrayList<String> cpfCidadao = new ArrayList<>();
    private ArrayList<String> nomeCidadao = new ArrayList<>();
    private ArrayList<String> data1dose = new ArrayList<>();
    private ArrayList<String> data2dose = new ArrayList<>();
    private ArrayList<String> data3dose = new ArrayList<>();
    private ArrayList<String> data4dose = new ArrayList<>();

    DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public int escolherOperacao() {
        int opcao = scanner.nextInt();
        scanner.nextLine();

        switch (opcao) {
            case 1:
                vacinarCidadao();
                break;
            case 2:
                listarCidadaos();
                break;
            case 3:
                System.out.println("Deseja realmente sair? (s/n)");
                String resp = scanner.nextLine();
                if (!resp.equalsIgnoreCase("s")) {
                    return 0;
                }
                break;
            default:
                System.out.println("Opção inválida.");
        }
        return opcao;
    }

    private void vacinarCidadao() {
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.println("Digite as datas (dd/MM/yyyy)");
        System.out.print("1ª dose: ");
        LocalDate d1 = LocalDate.parse(scanner.nextLine(), formato);

        System.out.print("2ª dose: ");
        LocalDate d2 = LocalDate.parse(scanner.nextLine(), formato);

        if (!validaIntervalo(d1, d2)) {
            System.out.println("ERRO: 2ª dose antes de 4 meses. Registro cancelado.");
            return;
        }

        System.out.print("3ª dose: ");
        LocalDate d3 = LocalDate.parse(scanner.nextLine(), formato);

        if (!validaIntervalo(d2, d3)) {
            System.out.println("ERRO: 3ª dose antes de 4 meses. Registro cancelado.");
            return;
        }

        System.out.print("4ª dose: ");
        LocalDate d4 = LocalDate.parse(scanner.nextLine(), formato);

        if (!validaIntervalo(d3, d4)) {
            System.out.println("ERRO: 4ª dose antes de 4 meses. Registro cancelado.");
            return;
        }

        cpfCidadao.add(cpf);
        nomeCidadao.add(nome);
        data1dose.add(d1.format(formato));
        data2dose.add(d2.format(formato));
        data3dose.add(d3.format(formato));
        data4dose.add(d4.format(formato));

        System.out.println("Cidadão vacinado e registrado com sucesso!");
    }

    private boolean validaIntervalo(LocalDate anterior, LocalDate proxima) {
        long meses = ChronoUnit.MONTHS.between(anterior, proxima);
        return meses >= 4;
    }

    private void listarCidadaos() {
        if (cpfCidadao.isEmpty()) {
            System.out.println("Nenhum cidadão vacinado ainda.");
            return;
        }

        System.out.println("\nLISTA DE CIDADÃOS VACINADOS:\n");
        for (int i = 0; i < cpfCidadao.size(); i++) {
            System.out.println("CPF: " + cpfCidadao.get(i));
            System.out.println("Nome: " + nomeCidadao.get(i));
            System.out.println("1ª Dose: " + data1dose.get(i));
            System.out.println("2ª Dose: " + data2dose.get(i));
            System.out.println("3ª Dose: " + data3dose.get(i));
            System.out.println("4ª Dose: " + data4dose.get(i));
            System.out.println("----------------------------");
        }
    }
}
