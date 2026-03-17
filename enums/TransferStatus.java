package app.domain.enums;

public enum TransferStatus {
    WAITING_APPROVAL,   // En espera de aprobación (transferencias de alto monto)
    APPROVED,           // Aprobada por Supervisor de Empresa
    EXECUTED,           // Ejecutada (fondos movidos)
    REJECTED,           // Rechazada por Supervisor de Empresa
    EXPIRED             // Vencida por falta de aprobación en 60 minutos
}
