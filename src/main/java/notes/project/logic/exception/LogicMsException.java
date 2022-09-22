package notes.project.logic.exception;

import lombok.Getter;

public class LogicMsException extends RuntimeException {
    @Getter
    private final ExceptionCode code;
    @Getter
    private final String message;

    public LogicMsException(ExceptionCode code,String message) {
        this.code = code;
        this.message = message;
    }
}
