package notes.project.logic.utils;

import java.util.Collections;

import lombok.experimental.UtilityClass;
import notes.project.logic.dto.*;
import notes.project.logic.exception.ExceptionCode;
import notes.project.logic.exception.ValidationException;

import static notes.project.logic.utils.TestDataConstants.*;

@UtilityClass
public class ApiUtils {
    public static ErrorDto errorDto() {
        return new ErrorDto()
                .setCode(EXCEPTION_CODE)
                .setMessage(EXCEPTION_MESSAGE);
    }

    public static ValidationErrorDto validationErrorDto() {
        return new ValidationErrorDto(Collections.singletonList(errorDto()));
    }

    public static ValidationException validationException() {
        ValidationException validationException = new ValidationException();
        validationException.addCode(ExceptionCode.INTERNAL_ERROR);
        return validationException;
    }
}
