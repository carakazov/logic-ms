package notes.project.logic.exception;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class IntegrationException extends RuntimeException {
    @Getter
    private final int originHttpStatus;
    @Getter
    private final Map<?, ?> originBody;
}
