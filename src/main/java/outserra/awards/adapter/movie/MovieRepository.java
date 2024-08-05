package outserra.awards.adapter.movie;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import outserra.awards.core.movie.domain.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

	public record RangeProjection(List<WinProjection> min, List<WinProjection> max) {

		public record WinProjection(String producer, int interval, int previousWin, int followingWin) {

			public static WinProjection from(Object[] tuple) {
				return new WinProjection((String) tuple[0], (Integer) tuple[3], (Integer) tuple[1], (Integer) tuple[2]);
			}
		}
	}
}
