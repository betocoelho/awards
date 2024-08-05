package outserra.awards.core.movie.application.loadcsv;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import outserra.awards.core.movie.domain.Movie;

@Transactional
@SpringBootTest
@ActiveProfiles({"dev"})
class LoadCSVUseCaseTest {

	@Autowired
	private LoadCSVService usecase;

	@Autowired
	private EntityManager entityManager;

	@Test
	@DisplayName("given correct load CSV")
	void givenCorrectLoadCSV() {

		// when
		int countAll = usecase.handle(null);

		// then
		assertEquals(472, countAll);
	}

	@Test
	@DisplayName("given correct Movie RamboIII")
	void givenCorrectMovieRamboIII() {
		// given

		// when
		var typedQuery = entityManager.createQuery("""
				SELECT m
				  FROM Movie m
				 WHERE m.title = 'Rambo III'
				""", Movie.class);
		var rambo = typedQuery.getSingleResult();

		// then
		assertEquals(1988, rambo.getYear());
		assertEquals("Rambo III", rambo.getTitle());
		assertEquals(false, rambo.isWinner());
	}

}
