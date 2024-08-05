package outserra.awards.core.movie.domain;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PRIVATE;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import outserra.awards.core.movie.application.loadcsv.LoadCSVUseCase.MovieDTO;

@Getter
@NoArgsConstructor
@Builder(access = PRIVATE)
@AllArgsConstructor(access = PRIVATE)
@ToString

@Entity
public final class Movie {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private long id;
	@Column(name = "year_award")
	private int year;
	private String title;
	private String studios;
	private String producer;
	private boolean winner;

	public static Movie from(MovieDTO dto) {
		return Movie.builder()
				.year(dto.year())
				.title(dto.title())
				.studios(dto.studios())
				.producer(dto.producer())
				.winner(dto.winner())
				.build();

	}

}
