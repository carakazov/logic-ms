package notes.project.logic.validation;

import java.util.UUID;

import notes.project.logic.utils.ApiUtils;
import notes.project.logic.validation.dto.DeleteNoteValidationDto;
import notes.project.logic.validation.impl.DeleteNoteValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.access.AccessDeniedException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DeleteNoteValidatorTest {
    private Validator<DeleteNoteValidationDto> validator;

    @BeforeEach
    void init() {
        validator = new DeleteNoteValidator();
    }

    @Test
    void validateSuccess() {
        DeleteNoteValidationDto validationDto = ApiUtils.deleteNoteValidationDto();

        assertDoesNotThrow(() -> validator.validate(validationDto));
    }

    @Test
    void validateThrow() {
        DeleteNoteValidationDto validationDto = ApiUtils.deleteNoteValidationDto();
        validationDto.setClientExternalId(UUID.randomUUID());

        assertThrows(
            AccessDeniedException.class,
            () -> validator.validate(validationDto)
        );
    }
}
