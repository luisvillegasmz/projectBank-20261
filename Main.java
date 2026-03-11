package app;

import app.domain.services.implementations.*;
import app.domain.services.interfaces.*;

/**
 * Punto de entrada de la aplicación.
 * Aquí se "cablea" la inyección de dependencias manualmente
 * (sin Spring — igual al patrón de la guía del profe).
 */
public class Main {

    public static void main(String[] args) {

        // ── 1. Crear el almacén compartido de usuarios ──────────────────
        // (AuthService y UserService comparten la misma lista)
        java.util.List<app.domain.models.User> usersStorage = new java.util.ArrayList<>();

        // ── 2. Instanciar servicios en el orden correcto (dependencias primero) ──
        IAuthService authService    = new AuthService(usersStorage);
        ILogService  logService     = new LogService(authService);

        UserService  userService    = new UserService(authService, usersStorage, logService);
        AccountService accountService = new AccountService(authService, userService, logService);
        ILoanService loanService    = new LoanService(authService, userService, logService, accountService);
        ITransferService transferService = new TransferService(authService, logService, accountService);

        // ── 3. Ya puedes usar los servicios ────────────────────────────
        System.out.println("=== Banco App iniciada ===");
        System.out.println("Servicios listos: Auth, User, Account, Loan, Transfer, Log");

        // Ejemplo rápido de uso:
        // IndividualCustomer customer = new IndividualCustomer();
        // customer.setName("Juan Pérez");
        // customer.setIdentificationId("123456789");
        // customer.setEmail("juan@email.com");
        // customer.setPhone("3001234567");
        // customer.setAddress("Calle 123");
        // customer.setBirthDate(LocalDate.of(1990, 5, 15));
        // userService.registerIndividualCustomer(customer, "juan123", "pass1234");
        //
        // User loggedUser = authService.login("juan123", "pass1234");
        // System.out.println("Login exitoso: " + loggedUser.getName());
    }
}
