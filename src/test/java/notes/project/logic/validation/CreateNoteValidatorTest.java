package notes.project.logic.validation;

import java.util.UUID;

import io.swagger.annotations.Api;
import notes.project.logic.utils.ApiUtils;
import notes.project.logic.validation.dto.CreateNoteValidationDto;
import notes.project.logic.validation.impl.CreateNoteValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.access.AccessDeniedException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CreateNoteValidatorTest {
    private Validator<CreateNoteValidationDto> validator;

    @BeforeEach
    void init() {
        validator = new CreateNoteValidator();
    }

    @Test
    void validateSuccess() {
        CreateNoteValidationDto validationDto = ApiUtils.createNoteValidationDto();

        assertDoesNotThrow(() -> validator.validate(validationDto));
    }

    @Test
    void validateThrow() {
        CreateNoteValidationDto validationDto = ApiUtils.createNoteValidationDto();
        validationDto.setClientExternalId(UUID.randomUUID());

        assertThrows(
            AccessDeniedException.class,
            () -> validator.validate(validationDto)
        );
    }
}
