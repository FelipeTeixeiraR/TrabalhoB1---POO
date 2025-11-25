package Gemini;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class ControleVacinacao {
    // Lista onde todos os objetos Cidadao serão armazenados (Requisito 8)
    private List<Cidadao> vacinados;

    // Construtor
    public ControleVacinacao() {
        this.vacinados = new ArrayList<>();
    }

    /**
     * Requisito 4: Verifica se o intervalo entre a dose anterior e a nova dose é de pelo menos 4 meses.
     * @param dataAnterior Data da dose anterior.
     * @param dataNova Data da dose que está sendo registrada.
     * @return true se o intervalo for igual ou superior a 4 meses, false caso contrário.
     */
    public boolean podeVacinar(LocalDate dataAnterior, LocalDate dataNova) {
        // Calcula a diferença em meses entre as duas datas
        long meses = ChronoUnit.MONTHS.between(dataAnterior, dataNova);
        
        // A data nova deve ser posterior à anterior E o intervalo deve ser de 4 meses ou mais.
        return dataNova.isAfter(dataAnterior) && meses >= 4;
    }

    /**
     * Adiciona um cidadão à lista de vacinados.
     * @param cidadao O objeto Cidadao a ser adicionado.
     */
    public void adicionarVacinado(Cidadao cidadao) {
        this.vacinados.add(cidadao);
    }

    /**
     * Retorna a lista completa de cidadãos vacinados (Requisito 5).
     * @return Lista de objetos Cidadao.
     */
    public List<Cidadao> listarVacinados() {
        return this.vacinados;
    }
}