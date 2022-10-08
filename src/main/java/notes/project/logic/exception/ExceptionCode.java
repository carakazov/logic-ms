package notes.project.logic.exception;

import lombok.Getter;

public enum ExceptionCode {
    INTERNAL_ERROR("internalError"),
    WRONG_REQUEST_PARAMETERS("wrongRequestParameters"),
    INTEGRATION_ERROR("integrationError"),
    NOT_FOUND("requestedResourceNotFound");
    @Getter
    private final String code;

    ExceptionCode(String code) {
        this.code = code;
    }
}
