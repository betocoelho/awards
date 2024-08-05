package outserra.awards.adapter.movie;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import outserra.awards.adapter.movie.MovieRepository.RangeProjection;
import outserra.awards.core.movie.application.lookforwinnerrange.LookForWinnerRangeUseCase;

@AllArgsConstructor

@RestController
@RequestMapping("/api/winners")
public class WinnerController {

	private final LookForWinnerRangeUseCase lookForWinnerRange;

	@GetMapping
	public ResponseEntity<RangeProjection> lookForWinnerRange() {
		return ResponseEntity.ok(lookForWinnerRange.handle(null));
	}
}
