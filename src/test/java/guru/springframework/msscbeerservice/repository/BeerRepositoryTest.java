package guru.springframework.msscbeerservice.repository;

import guru.springframework.msscbeerservice.domain.Beer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DataJpaTest
class BeerRepositoryTest {

    @Autowired
    BeerRepository beerRepository;

    @Test
    void saveBeer() {
        Beer saved = beerRepository.saveAndFlush(buildBeer("0631234200036"));

        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getVersion()).isNotNull();
        assertThat(saved.getCreatedDate()).isNotNull();
    }

    @Test
    void upcUnique() {
        beerRepository.saveAndFlush(buildBeer("0631234300019"));

        assertThatThrownBy(() -> beerRepository.saveAndFlush(buildBeer("0631234300019")))
                .isInstanceOf(DataIntegrityViolationException.class);
    }

    private Beer buildBeer(String upc) {
        return Beer.builder()
                .beerName("Test Beer")
                .beerStyle("IPA")
                .upc(upc)
                .price(new BigDecimal("9.99"))
                .minOnHand(10)
                .quantityToBrew(100)
                .build();
    }
}
