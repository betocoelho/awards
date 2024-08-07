package outserra.awards.core.movie.application.lookforwinnerrange;

import org.hibernate.query.Query;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import outserra.awards.adapter.movie.MovieRepository.RangeProjection;
import outserra.awards.adapter.movie.MovieRepository.RangeProjection.WinProjection;

@AllArgsConstructor
@Service
public class LookForWinnerRangeService implements LookForWinnerRangeUseCase {

	private final EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public RangeProjection handle(Void command) {

		return new RangeProjection(

				entityManager.createNativeQuery(VIEW + """
						SELECT *
						  FROM win_view
						 LIMIT 1
						""")
						.unwrap(Query.class)

						.setTupleTransformer((tuple, alias) -> WinProjection.from(tuple))
						.getResultList(),

				entityManager.createNativeQuery(VIEW + """
							SELECT d.*
							  FROM win_view d
							 ORDER BY diff DESC
						 	 LIMIT 1
						""")
						.unwrap(Query.class)

						.setTupleTransformer((tuple, alias) -> WinProjection.from(tuple))
						.getResultList()

		);
	}

	private static String VIEW = """
						WITH wins_order AS (
			 SELECT t1.producer
			      , t1.year_award previous_win
			      , max(t2.year_award) following_win
			   FROM movie t1
			  CROSS JOIN movie t2
			  WHERE t1.producer = t2.producer
			    AND t1.ID <> t2.ID
			    AND t1.year_award > t2.year_award -1
			    AND t1.winner
			    AND t2.winner
			  GROUP BY t1.producer, previous_win
			  ORDER BY t1.producer, previous_win DESC, following_win DESC
			), win_view as (
			SELECT *, previous_win - following_win AS diff
			  FROM wins_order
			 ORDER BY diff ASC, producer DESC)
						""";
}
