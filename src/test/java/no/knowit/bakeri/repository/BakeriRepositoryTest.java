package no.knowit.bakeri.repository;

import no.knowit.bakeri.Klassifisering;
import no.knowit.bakeri.model.Ingrediens;
import no.knowit.bakeri.model.Oppskrift;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class BakeriRepositoryTest {

    @Autowired
    BakeriRepository bakeriRepository;

    @Test
    void skalRegistrerIngrediens() {
        Ingrediens testIngrediens = new Ingrediens(
                "mel",
                5000
        );

        Integer nyId = bakeriRepository.registrerIngrediens(testIngrediens);
        Ingrediens fraDB = bakeriRepository.getIngrediensById(nyId);

        assertThat(testIngrediens.toString()).isEqualTo(fraDB.toString());
    }

    @Test
    void registrerOppskrift() {}

    @Test
    void oppdaterLagerBeholdning() {
        Ingrediens testIngrediens = new Ingrediens(
                "mel",
                5000
        );

        Integer lagerBeholdningFoer = testIngrediens.getLagerBeholdning();
        Integer id = bakeriRepository.registrerIngrediens(testIngrediens);
        bakeriRepository.oppdaterLagerBeholdning(id, 5000);
        Ingrediens fraDb = bakeriRepository.getIngrediensById(id);

        assertThat(lagerBeholdningFoer).isLessThan(fraDb.getLagerBeholdning());
        assertThat(fraDb.getLagerBeholdning()).isEqualTo(10000);
    }

    @Test
    void SkalHenteoppskrifterMedKlassifiseringEtterAlfabetiskOrder() {
        List<Oppskrift> oppskrifter = bakeriRepository.oppskrifterMedKlassifisering(Klassifisering.SMÅKAKE);
        oppskrifter.forEach(oppskrift -> {
            int index = oppskrifter.indexOf(oppskrift);
            System.out.println(oppskrift.getName());
            if (index != 0) {
                assertThat(oppskrift.getName())
                        .isGreaterThanOrEqualTo(oppskrifter.get(index - 1).getName());
            }
        });
    }

    @Test
    void skalHenteOppskrifterLagretMellomToDatoer() {
        LocalDate date1 = LocalDate.of(2018, 1, 1);
        LocalDate date2 = LocalDate.of(2024, 1, 1);
        List<Oppskrift> oppskrifterMellomDatoer = bakeriRepository.oppskrifterLagretMellomToDatoer(
                date1,
                date2
        );

        oppskrifterMellomDatoer.forEach(oppskrift -> {
            assertThat(oppskrift.getDato()).isBetween(date1, date2);
        });
    }

    @Test
    void muligHetForProduksjon() {
        boolean willReturnTrue = bakeriRepository.mulighetForProduksjon(
                1,
                19
        );

        boolean willReturnFalse = bakeriRepository.mulighetForProduksjon(
                1,
                21
        );

        assertThat(willReturnTrue).isTrue();
        assertThat(willReturnFalse).isFalse();

    }

    @Test
    void getIngrediensById() {
        Integer id = 1;
        Ingrediens ingrediens = bakeriRepository.getIngrediensById(id);

        assertThat(ingrediens.getId()).isEqualTo(id);
    }

    @Test
    void getOppskriftById() {
        Integer id = 1;
        Oppskrift oppskrift = bakeriRepository.getOppskriftById(id);

        assertThat(oppskrift.getId()).isEqualTo(id);
    }
}