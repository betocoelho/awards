package outserra.awards.shared;

import jakarta.validation.Valid;

public interface UsecaseBase<Input, Output> {

	Output handle(@Valid Input command);

}
