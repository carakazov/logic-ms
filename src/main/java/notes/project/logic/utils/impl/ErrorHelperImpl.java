package notes.project.logic.utils.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import notes.project.logic.config.ApplicationProperties;
import notes.project.logic.exception.NotFoundException;
import notes.project.logic.utils.ErrorHelper;
import notes.project.logic.dto.ErrorDto;
import notes.project.logic.dto.ValidationErrorDto;
import notes.project.logic.exception.ExceptionCode;
import notes.project.logic.exception.ValidationException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class ErrorHelperImpl implements ErrorHelper {
    private final ApplicationProperties properties;
    private static final String COMMON_EXCEPTION_CODE = "internalServerError";
    private static final String COMMON_EXCEPTION_MESSAGE = "Unexpected exception occurred during method execution";

    @Override
    public ValidationErrorDto from(ValidationException validationException) {
        List<ErrorDto> errors = new ArrayList<>();
        validationException.getCodes().forEach(item -> {
            String message = getMessageByCode(item);
            ErrorDto error = new ErrorDto();
            error.setMessage(message);
            error.setCode(item.getCode());
            errors.add(error);
        });
        return new ValidationErrorDto(errors);
    }

    @Override
    public ErrorDto from(Exception exception) {
        logException(exception);
        return new ErrorDto().setCode(COMMON_EXCEPTION_CODE).setMessage(COMMON_EXCEPTION_MESSAGE);
    }

    @Override
    public ErrorDto from(MethodArgumentNotValidException exception) {
        logException(exception);
        ErrorDto errorDto = new ErrorDto();
        errorDto.setMessage(getMessageByCode(ExceptionCode.WRONG_REQUEST_PARAMETERS));
        errorDto.setCode(ExceptionCode.WRONG_REQUEST_PARAMETERS.getCode());
        return errorDto;
    }

    @Override
    public ErrorDto from(NotFoundException exception) {
        logException(exception);
        ErrorDto errorDto = new ErrorDto();
        errorDto.setCode(ExceptionCode.NOT_FOUND.getCode());
        errorDto.setMessage(getMessageByCode(ExceptionCode.NOT_FOUND));
        return errorDto;
    }

    @Override
    public void from(AccessDeniedException exception) {
        logException(exception);
    }

    private String getMessageByCode(ExceptionCode code) {
        String exceptionMessage = properties.getMessage(code.getCode());
        if(StringUtils.isNotEmpty(exceptionMessage)) {
            return exceptionMessage;
        }
        return null;
    }

    private void logException(Exception exception) {
        log.error("Exception occurred. Source message: {}", exception.getMessage());
    }

    private void logException(RuntimeException exception) {
        log.error("Exception occurred. Source message: {}", exception.getMessage());
    }
}
