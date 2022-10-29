package notes.project.logic.validation;

import java.util.UUID;

import notes.project.logic.exception.ValidationException;
import notes.project.logic.utils.ApiUtils;
import notes.project.logic.validation.dto.MoveNoteValidationDto;
import notes.project.logic.validation.impl.MoveNoteValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Component;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Component
class MoveNoteValidatorTest {
    private Validator<MoveNoteValidationDto> validator;

    @BeforeEach
    void init() {
        validator = new MoveNoteValidator();
    }

    @Test
    void validateSuccess() {
        MoveNoteValidationDto validationDto = ApiUtils.moveNoteValidationDto();

        assertDoesNotThrow(() -> validator.validate(validationDto));
    }

    @Test
    void validateThrow() {
        MoveNoteValidationDto validationDto = ApiUtils.moveNoteValidationDto().setClientExternalId(UUID.randomUUID());

        assertThrows(
            ValidationException.class,
            () -> validator.validate(validationDto)
        );
    }
}
