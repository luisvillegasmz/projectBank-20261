package app.domain.models;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public abstract class Customer extends Person {
    // Corregido: el campo se llamaba igual que la clase (GeneralBankProduct)
    private List<GeneralBankProduct> products = new ArrayList<>();
}
