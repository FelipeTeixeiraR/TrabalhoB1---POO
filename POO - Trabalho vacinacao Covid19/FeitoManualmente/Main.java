
public class Main {

    public static void main(String[] args) {

        Enfermeira enfermeira = new Enfermeira();
        ControleDados cont = new ControleDados();

        enfermeira.cadastrarEnfermeira();

        int opcao = 0;
        do {
            System.out.println("\n( 1 ) Vacinar cidadão");
            System.out.println("( 2 ) Listar cidadãos vacinados");
            System.out.println("( 3 ) Sair");
            System.out.print("Escolha uma opção: ");

            opcao = cont.escolherOperacao();

        } while (opcao != 3);

        System.out.println("Sistema finalizado.");
    }
}
