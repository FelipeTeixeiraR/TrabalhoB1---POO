
import java.util.ArrayList;
import java.util.Scanner;

public class Enfermeira {

    Scanner scanner = new Scanner(System.in);

    private ArrayList<String> cpfEnfermeira = new ArrayList<>();
    private ArrayList<String> nomeEnfermeira = new ArrayList<>();

    public Enfermeira() {
    }

    public void cadastrarEnfermeira() {
        System.out.print("Digite o nome da enfermeira: ");
        nomeEnfermeira.add(scanner.nextLine());

        System.out.print("Digite o CPF da enfermeira: ");
        cpfEnfermeira.add(scanner.nextLine());
    }
}
