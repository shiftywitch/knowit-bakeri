package no.knowit.bakeri.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import no.knowit.bakeri.utils.Database;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class Ingrediens {
    private Integer id;
    private String name;
    private Integer lagerBeholdning;

    public Ingrediens(String name, Integer lagerBeholdning) {
        this.id = Database.genererIngrediensId();
        this.name = name;
        this.lagerBeholdning = lagerBeholdning;
    }

    public void oppdaterLagerBeholdning(Integer gram) {
        lagerBeholdning += gram;
    }
}
