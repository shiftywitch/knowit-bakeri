package no.knowit.bakeri.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Mål {
    private Ingrediens ingrediens;
    private Integer gram;
}
