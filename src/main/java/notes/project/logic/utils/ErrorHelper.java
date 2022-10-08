package notes.project.logic.utils;

import notes.project.logic.dto.ErrorDto;
import notes.project.logic.dto.ValidationErrorDto;
import notes.project.logic.exception.NotFoundException;
import notes.project.logic.exception.ValidationException;
import org.springframework.web.bind.MethodArgumentNotValidException;

public interface ErrorHelper {
    ValidationErrorDto from(ValidationException validationException);
    ErrorDto from(Exception exception);
    ErrorDto from(MethodArgumentNotValidException exception);
    ErrorDto from(NotFoundException exception);
}
