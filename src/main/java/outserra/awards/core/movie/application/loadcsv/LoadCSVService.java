package outserra.awards.core.movie.application.loadcsv;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import outserra.awards.adapter.movie.MovieRepository;
import outserra.awards.core.movie.domain.Movie;

@AllArgsConstructor
@Log4j2

@Service
@Validated
@Transactional
public class LoadCSVService implements LoadCSVUseCase {

	private static final String MOVIELIST_CSV = "classpath:movielist (7).csv";
	private static final String CMD_DELIMITER = ";";

	private final ResourceLoader resourceLoader;

	private final MovieRepository repository;

	@Override
	public Integer handle(@Valid Void command) {

		var is = getFileAsIOStream();
		var movies = convertCSVtoMovie(is);

		for (MovieDTO dto : movies) {
			repository.save(Movie.from(dto));
		}
		return movies.size();
	}

	private InputStreamReader getFileAsIOStream() {
		try {

			Resource resource = resourceLoader.getResource(MOVIELIST_CSV);
			return new InputStreamReader(resource.getInputStream());
		} catch (IOException e) {
			log.error("CSV reader cound not be initialized. ", e);
		}
		return null;
	}

	private Set<MovieDTO> convertCSVtoMovie(InputStreamReader is) {
		var movies = new HashSet<MovieDTO>();
		try {
			BufferedReader reader = new BufferedReader(is);
			String line;
			reader.readLine();
			while ((line = reader.readLine()) != null)
				movies.addAll(MovieDTO.from(line.split(CMD_DELIMITER)));

			is.close();
		} catch (IOException e) {
			log.error("CSV reader cound not be reader. ", e);
		}
		return movies;
	}

}
