package Gemini;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class Sistema {
    private static Scanner scanner = new Scanner(System.in);
    private static ControleVacinacao controle = new ControleVacinacao();
    private static Enfermeira enfermeiraLogada = null;
    
    // Formatador para padronizar a entrada/saída de datas
    private static final DateTimeFormatter FORMATADOR_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void main(String[] args) {
        exibirBemVindo();
        
        // Requisito 1
        if (loginEnfermeira()) {
            exibirMenuPrincipal();
        }
        
        System.out.println("\n👋 Sistema de controle de vacinação finalizado. Bom trabalho!");
        scanner.close();
    }

    /**
     * Exibe a mensagem de boas-vindas.
     */
    private static void exibirBemVindo() {
        System.out.println("==================================================");
        System.out.println("💉 Sistema de Controle de Vacinação COVID-19");
        System.out.println("==================================================");
    }
    
    /**
     * Requisito 1: Solicita e armazena os dados da enfermeira.
     * @return true se o login for bem-sucedido.
     */
    private static boolean loginEnfermeira() {
        System.out.println("## 🧑‍⚕️ Login da Enfermeira Responsável ##");
        
        System.out.print("Informe o CPF da Enfermeira: ");
        String cpfEnfermeira = scanner.nextLine();
        
        System.out.print("Informe o Nome da Enfermeira: ");
        String nomeEnfermeira = scanner.nextLine();
        
        enfermeiraLogada = new Enfermeira(cpfEnfermeira, nomeEnfermeira);
        System.out.println("\n✅ Login realizado com sucesso! " + enfermeiraLogada.getInfo());
        System.out.println("--------------------------------------------------\n");
        return true;
    }
    
    /**
     * Requisito 2: Exibe o menu principal e processa as opções.
     */
    private static void exibirMenuPrincipal() {
        int opcao = -1;
        do {
            System.out.println("## 🧭 Menu Principal ##");
            System.out.println("1 - Vacinar Cidadão"); // Requisito 2
            System.out.println("2 - Listar Cidadãos Vacinados"); // Requisito 2
            System.out.println("3 - Sair"); // Requisito 2
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("\n❌ Opção inválida! Digite um número de 1 a 3.");
                continue;
            }

            switch (opcao) {
                case 1:
                    vacinarCidadao();
                    break;
                case 2:
                    listarCidadaosVacinados();
                    break;
                case 3:
                    // A confirmação de saída é feita no loop 'do-while' (Requisito 6)
                    break;
                default:
                    System.out.println("\n❌ Opção inválida! Tente novamente.");
            }
            
            System.out.println("--------------------------------------------------");

        } while (opcao != 3 || !confirmarSaida()); // Requisito 6
    }
    
    /**
     * Requisito 3 e 4: Coleta os dados do cidadão e aplica a regra de intervalo.
     */
    private static void vacinarCidadao() {
        System.out.println("\n## 📝 Registro de Vacinação ##");
        
        System.out.print("CPF do Cidadão: ");
        String cpfCidadao = scanner.nextLine();
        
        System.out.print("Nome do Cidadão: ");
        String nomeCidadao = scanner.nextLine();
        
        Cidadao novoVacinado = new Cidadao(cpfCidadao, nomeCidadao);
        LocalDate dataAnterior = null;
        boolean registroCompleto = true;
        
        // As doses 1 a 4 são solicitadas em sequência
        for (int i = 1; i <= 4; i++) {
            System.out.printf("Informe a data da %dª dose (dd/MM/yyyy): ", i);
            String dataStr = scanner.nextLine();
            
            try {
                LocalDate dataNova = LocalDate.parse(dataStr, FORMATADOR_DATA);
                
                // A regra só se aplica da 2ª dose em diante (i > 1)
                if (i > 1) {
                    // Requisito 4: Verifica o intervalo de 4 meses
                    if (!controle.podeVacinar(dataAnterior, dataNova)) {
                        System.out.printf("\n⚠️ ATENÇÃO: A %dª dose (data %s) deve ter um intervalo de **4 meses ou mais** da dose anterior (data %s).\n", 
                                i, dataStr, dataAnterior.format(FORMATADOR_DATA));
                        System.out.println("❌ Cidadão não elegível para esta dose no momento. Registro cancelado.");
                        registroCompleto = false; // Sinaliza que o registro não deve ser salvo
                        break; // Sai do loop de doses
                    }
                }
                
                // Se passou na verificação OU é a 1ª dose
                novoVacinado.adicionarDose(dataNova);
                dataAnterior = dataNova; // Atualiza a data da dose anterior para a próxima iteração
                
            } catch (DateTimeParseException e) {
                System.out.println("\n❌ Erro de formato de data. Use o formato dd/MM/yyyy.");
                registroCompleto = false;
                break;
            }
        }
        
        // Se todas as doses foram inseridas corretamente e a regra dos 4 meses foi seguida
        if (registroCompleto && novoVacinado.getDatasDoses().size() == 4) {
            controle.adicionarVacinado(novoVacinado); // Requisito 8: Guarda o objeto na memória
            System.out.println("\n✅ Cidadão " + nomeCidadao + " registrado com sucesso no controle de vacinação.");
        }
    }
    
    /**
     * Requisito 5: Exibe a lista de cidadãos vacinados com suas datas.
     */
    private static void listarCidadaosVacinados() {
        System.out.println("\n## 📋 Lista de Cidadãos Vacinados ##");
        List<Cidadao> lista = controle.listarVacinados();
        
        if (lista.isEmpty()) {
            System.out.println("Nenhum cidadão foi registrado até o momento.");
        } else {
            for (Cidadao c : lista) {
                System.out.println(c.toString());
                System.out.println("---");
            }
        }
    }
    
    /**
     * Requisito 6: Solicita confirmação antes de sair.
     * @return true se o usuário confirmar a saída, false caso contrário.
     */
    private static boolean confirmarSaida() {
        System.out.print("\nVocê deseja realmente finalizar o sistema? (S/N): ");
        String resposta = scanner.nextLine().trim().toUpperCase();
        return resposta.equals("S");
    }
}