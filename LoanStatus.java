package app.domain.enums;

public enum LoanStatus {
    IN_STUDY,       // Estado inicial al crear la solicitud
    APPROVED,       // Aprobado por Analista Interno
    REJECTED,       // Rechazado por Analista Interno
    DISBURSED       // Desembolsado a la cuenta destino
}
