package app.domain.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * Bitácora de Operaciones - Se almacena en BD NoSQL (documento).
 * Registra todas las operaciones significativas del sistema para auditoría.
 */
@Setter
@Getter
@NoArgsConstructor
public class LogRecord {
    private String logId;
    private String operationType;
    private LocalDateTime operationDateTime;  // Corregido: era LocalDate
    private int userId;
    private String userRole;
    private String affectedProductId;
    private Map<String, Object> detailData;   // Corregido: documento JSON/Diccionario
}
