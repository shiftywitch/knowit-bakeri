package no.knowit.bakeri.repository;

import no.knowit.bakeri.Klassifisering;
import no.knowit.bakeri.model.Ingrediens;
import no.knowit.bakeri.model.Mål;
import no.knowit.bakeri.model.Oppskrift;
import no.knowit.bakeri.utils.Database;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class BakeriRepository {

    public Integer registrerIngrediens(Ingrediens ingrediens) {

        Database.ingredienser.add(ingrediens);
        return ingrediens.getId();
    }

    public Integer registrerOppskrift(Oppskrift oppskrift) {

        Database.oppskrifter.add(oppskrift);
        return oppskrift.getId();
    }

    public void oppdaterLagerBeholdning(Integer id, Integer gram) {
        Database.ingredienser.stream()
                .filter(ingrediens -> ingrediens.getId().equals(id))
                .findAny()
                .get().oppdaterLagerBeholdning(gram);
    }

    public List<Oppskrift> oppskrifterMedKlassifisering(Klassifisering klassifisering) {
        return Database.oppskrifter.stream()
                .filter(oppskrift -> oppskrift.getKlassifisering().equals(klassifisering))
                .sorted(Comparator.comparing(Oppskrift::getName))
                .collect(Collectors.toList());
    }

    public List<Oppskrift> oppskrifterLagretMellomToDatoer(LocalDate datoFra, LocalDate datoTil) {
        return Database.oppskrifter.stream()
                .filter(oppskrift -> oppskrift.getDato().compareTo(datoFra) > 0 &&
                        oppskrift.getDato().compareTo(datoTil) < 0)
                .collect(Collectors.toList());
    }

    public boolean mulighetForProduksjon(Integer id, Integer antall) {
        Oppskrift oppskrift = getOppskriftById(id);

        for (Mål mål : oppskrift.getMål()) {
            Ingrediens ingrediens = getIngrediensById(mål.getIngrediens().getId());
            Integer gram = mål.getGram() * antall;
            if (ingrediens.getLagerBeholdning() < gram) {
                return false;
            }
        }
        return true;
    }

    public Ingrediens getIngrediensById(Integer id) {
        return Database.ingredienser.stream()
                .filter(ingrediens -> ingrediens.getId().equals(id))
                .findFirst().orElseThrow();
    }

    public Oppskrift getOppskriftById(Integer id) {
        return Database.oppskrifter.stream()
                .filter(oppskrift -> oppskrift.getId().equals(id))
                .findFirst().orElseThrow();
    }
}
