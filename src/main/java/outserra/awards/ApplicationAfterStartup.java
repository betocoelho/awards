package outserra.awards;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import outserra.awards.core.movie.application.loadcsv.LoadCSVUseCase;

@AllArgsConstructor

@Component
public class ApplicationAfterStartup {

	private final LoadCSVUseCase loadCSVUseCase;

	@EventListener(ApplicationReadyEvent.class)
	public void doSomethingAfterStartup() {
		loadCSVUseCase.handle(null);
	}
}
