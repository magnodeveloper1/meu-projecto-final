package ao.uan.finalproject.dto.enums;

public enum TipoAvaliacao {
    
    AVALIACAO_CONTINUA("Avaliação Contínua"),
    PF1("Prova de Frequência 1"),
    PF2("Prova de Frequência 2"),
    EXAME("Exame"),
    RECURSO("Recurso"),
    EXAME_ESPECIAL("Exame Especial");

    private final String displayValue;

    private TipoAvaliacao(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}