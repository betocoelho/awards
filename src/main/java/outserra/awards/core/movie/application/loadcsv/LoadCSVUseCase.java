package outserra.awards.core.movie.application.loadcsv;

import static java.util.stream.Collectors.toSet;

import java.util.Set;
import java.util.stream.Stream;

import lombok.Builder;
import outserra.awards.shared.UsecaseBase;

public interface LoadCSVUseCase extends UsecaseBase<Void, Integer> {

	@Builder
	public record MovieDTO(int year, String title, String studios, String producer, boolean winner) {

		private static final String PRODUCER_DELIMITER = ", | and ";

		public static Set<MovieDTO> from(String[] line) {
			return extractProducers(line).map(p -> MovieDTO.builder()
					.year(Integer.valueOf(line[0]))
					.title(line[1])
					.studios(line[2])
					.producer(p.toLowerCase().strip())
					.winner(isWinner(line))
					.build()).collect(toSet());
		}

		private static Stream<String> extractProducers(String[] line) {
			return Stream.of(line[3].split(PRODUCER_DELIMITER));
		}

		private static boolean isWinner(String[] line) {
			return line.length == 5;
		}
	}

}
