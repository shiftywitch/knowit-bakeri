package no.knowit.bakeri.dummyData;

import lombok.AllArgsConstructor;
import no.knowit.bakeri.Klassifisering;
import no.knowit.bakeri.model.Ingrediens;
import no.knowit.bakeri.model.Mål;
import no.knowit.bakeri.model.Oppskrift;
import no.knowit.bakeri.repository.BakeriRepository;
import no.knowit.bakeri.utils.Database;
import org.apache.tomcat.jni.Local;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Configuration
public class DummyData {
    private final BakeriRepository bakeriRepository;

    @Bean
    protected void populateDatabase() {
        Ingrediens mel = new Ingrediens(
                "mel",
                10000
        );
        Ingrediens sukker = new Ingrediens(
                "sukker",
                10000
        );
        Ingrediens egg = new Ingrediens(
                "egg",
                10000
        );

        bakeriRepository.registrerIngrediens(mel);
        bakeriRepository.registrerIngrediens(sukker);

        Mål mål1 = new Mål(mel, 100);
        Mål mål2 = new Mål(sukker, 100);
        Mål mål3 = new Mål(egg, 100);
        Mål mål4 = new Mål(mel, 500);
        Mål mål5 = new Mål(sukker, 500);
        Mål mål6 = new Mål(egg, 1000);

        Oppskrift oppskrift1 = new Oppskrift(
                Database.genererOppskriftId(),
                "Melkake",
                LocalDate.of(2018, 12, 22),
                Klassifisering.SMÅKAKE,
                List.of(mål4, mål2)
        );


        Oppskrift oppskrift2 = new Oppskrift(
                Database.genererOppskriftId(),
                "SukkerKake",
                LocalDate.of(2019, 7, 15),
                Klassifisering.SMÅKAKE,
                List.of(mål5, mål1, mål3)
        );

        Oppskrift oppskrift3 = new Oppskrift(
                Database.genererOppskriftId(),
                "AppelsinKake",
                LocalDate.of(2015, 11, 19),
                Klassifisering.SMÅKAKE,
                List.of(mål1, mål2, mål3)
        );

        Oppskrift oppskrift4 = new Oppskrift(
                Database.genererOppskriftId(),
                "Sandkake",
                LocalDate.of(2025, 10, 10),
                Klassifisering.SMÅKAKE,
                List.of(mål4, mål3)
        );

        bakeriRepository.registrerOppskrift(oppskrift1);
        bakeriRepository.registrerOppskrift(oppskrift2);
        bakeriRepository.registrerOppskrift(oppskrift3);
        bakeriRepository.registrerOppskrift(oppskrift4);

    }
}
