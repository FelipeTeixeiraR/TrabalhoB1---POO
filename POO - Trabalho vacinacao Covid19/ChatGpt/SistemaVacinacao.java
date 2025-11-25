package ChatGpt;

import java.util.Scanner;

public class SistemaVacinacao {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("==========================================");
        System.out.println("  Sistema de Controle de Vacinação COVID  ");
        System.out.println("==========================================\n");

        // 1 - Dados da enfermeira
        System.out.print("Informe o CPF da enfermeira: ");
        String cpfEnfermeira = scanner.nextLine().trim();

        System.out.print("Informe o nome da enfermeira: ");
        String nomeEnfermeira = scanner.nextLine().trim();

        Enfermeira enfermeira = new Enfermeira(cpfEnfermeira, nomeEnfermeira);
        RegistroVacinacao registro = new RegistroVacinacao(enfermeira);

        boolean sair = false;

        while (!sair) {
            // 2 - Menu de opções
            System.out.println("\n===== MENU PRINCIPAL =====");
            System.out.println("Enfermeira logada: " + enfermeira);
            System.out.println("1 - Vacinar cidadão");
            System.out.println("2 - Listar cidadãos vacinados");
            System.out.println("3 - Sair");
            System.out.print("Escolha uma opção: ");

            String opcaoTexto = scanner.nextLine();
            int opcao;

            try {
                opcao = Integer.parseInt(opcaoTexto);
            } catch (NumberFormatException e) {
                opcao = -1;
            }

            switch (opcao) {
                case 1:
                    // 3 e 4 - Vacinar cidadão e validar datas
                    registro.vacinarCidadao(scanner);
                    break;
                case 2:
                    // 5 - Listar cidadãos
                    registro.listarCidadaosVacinados();
                    break;
                case 3:
                    // 6 - Confirmar saída
                    System.out.print("Deseja realmente sair? (S/N): ");
                    String resposta = scanner.nextLine().trim();
                    if (resposta.equalsIgnoreCase("S")) {
                        sair = true;
                        System.out.println("Finalizando o sistema. Até logo!");
                    } else {
                        System.out.println("Operação cancelada. Retornando ao menu.");
                    }
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }

        scanner.close();
    }
}