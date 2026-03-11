package app.domain.models;

import app.domain.enums.SystemRole;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
public abstract class Person {
    private String name;
    private String identificationId;
    private String email;
    private String phone;
    private LocalDate birthDate;    // Corregido: LocalDate (no java.sql.Date)
    private String address;
    private SystemRole systemRole;
}
