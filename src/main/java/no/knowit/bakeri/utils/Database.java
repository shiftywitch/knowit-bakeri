package no.knowit.bakeri.utils;

import lombok.Getter;
import lombok.Setter;
import no.knowit.bakeri.model.Ingrediens;
import no.knowit.bakeri.model.Oppskrift;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
@Setter
public class Database {
    private static AtomicInteger ingrediensId = new AtomicInteger(0);
    private static AtomicInteger oppskriftId = new AtomicInteger(0);
    public static List<Ingrediens> ingredienser = new ArrayList<>();
    public static List<Oppskrift> oppskrifter = new ArrayList<>();

    public static Integer genererIngrediensId() {
        return ingrediensId.addAndGet(1);
    }

    public static Integer genererOppskriftId() {
        return oppskriftId.addAndGet(1);
    }
}
