package no.knowit.bakeri.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import no.knowit.bakeri.Klassifisering;
import no.knowit.bakeri.utils.Database;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Getter
@Setter
public class Oppskrift {
    private Integer id;
    private String name;
    private LocalDate dato;
    private Klassifisering klassifisering;
    private List<Mål> mål;

    public Oppskrift(String name,
                     Klassifisering klassifisering,
                     List<Mål> mål) {

        this.id = Database.genererOppskriftId();
        this.name = name;
        this.dato = LocalDate.now();
        this.klassifisering = klassifisering;
        this.mål = mål;
    }
}


