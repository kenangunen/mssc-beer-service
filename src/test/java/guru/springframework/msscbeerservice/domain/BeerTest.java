package guru.springframework.msscbeerservice.domain;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class BeerTest {

    @Test
    void sameUpcIsEqualAndHasSameHashCode() {
        Beer a = Beer.builder().beerName("A").upc("0631234200036").build();
        Beer b = Beer.builder().beerName("B").upc("0631234200036").build();

        assertThat(a).isNotSameAs(b);
        assertThat(a).isEqualTo(b);
        assertThat(a.hashCode()).isEqualTo(b.hashCode());
    }

    @Test
    void differentUpcIsNotEqual() {
        Beer a = Beer.builder().upc("0631234200036").build();
        Beer b = Beer.builder().upc("0631234300019").build();

        assertThat(a).isNotEqualTo(b);
    }

    @Test
    void twoNewBeersWithNullUpcAreNotEqual() {
        Beer a = Beer.builder().beerName("A").build();
        Beer b = Beer.builder().beerName("B").build();

        assertThat(a).isNotEqualTo(b);
        assertThat(a).isEqualTo(a);
    }

    @Test
    void hashSetKeepsOnlyOneBeerPerUpc() {
        Set<Beer> beers = new HashSet<>();
        beers.add(Beer.builder().beerName("A").upc("0631234200036").build());
        beers.add(Beer.builder().beerName("B").upc("0631234200036").build());

        assertThat(beers).hasSize(1);
    }
}
