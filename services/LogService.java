package app.domain.services.implementations;

import app.domain.enums.SystemRole;
import app.domain.models.LogRecord;
import app.domain.models.User;
import app.domain.services.interfaces.IAuthService;
import app.domain.services.interfaces.ILogService;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Implementación de la Bitácora de Operaciones.
 * Simula un almacenamiento NoSQL (documentos con estructura flexible).
 * En producción, esta lista sería una colección de MongoDB o similar.
 */
public class LogService implements ILogService {

    // Simula la colección NoSQL — cada LogRecord es un "documento"
    private final List<LogRecord> logStore = new ArrayList<>();
    private final IAuthService authService;
    private int nextLogId = 1;

    public LogService(IAuthService authService) {
        this.authService = authService;
    }

    @Override
    public void log(String operationType, User user, String affectedProductId, Map<String, Object> detailData) {
        LogRecord record = new LogRecord();
        record.setLogId("LOG-" + nextLogId++);
        record.setOperationType(operationType);
        record.setOperationDateTime(LocalDateTime.now());
        record.setUserId(parseUserId(user));
        record.setUserRole(user.getSystemRole() != null ? user.getSystemRole().toString() : "SYSTEM");
        record.setAffectedProductId(affectedProductId);
        record.setDetailData(detailData != null ? detailData : new HashMap<>());

        logStore.add(record);

        // En un proyecto real aquí se haría el insert a MongoDB/DynamoDB etc.
        System.out.println("[BITÁCORA] " + record.getLogId() + " | " + operationType
                + " | Producto: " + affectedProductId
                + " | Usuario: " + (user.getName() != null ? user.getName() : "SISTEMA"));
    }

    @Override
    public List<LogRecord> getAllLogs(User requestingUser) {
        // Solo el Analista Interno puede ver toda la bitácora
        authService.validateRole(requestingUser, SystemRole.INTERNAL_ANALYST);
        return new ArrayList<>(logStore);
    }

    @Override
    public List<LogRecord> getLogsByProduct(String affectedProductId, User requestingUser) {
        // Los clientes pueden ver los registros de sus propios productos (filtrado externo por rol)
        return logStore.stream()
                .filter(r -> r.getAffectedProductId().equals(affectedProductId))
                .collect(Collectors.toList());
    }

    @Override
    public List<LogRecord> getLogsByUser(int userId, User requestingUser) {
        authService.validateRole(requestingUser, SystemRole.INTERNAL_ANALYST);
        return logStore.stream()
                .filter(r -> r.getUserId() == userId)
                .collect(Collectors.toList());
    }

    // ──────────────────────────────────────────────
    // Métodos privados
    // ──────────────────────────────────────────────

    private int parseUserId(User user) {
        try {
            return Integer.parseInt(user.getIdentificationId());
        } catch (NumberFormatException e) {
            return 0; // Usuario sistema o ID no numérico
        }
    }
}
