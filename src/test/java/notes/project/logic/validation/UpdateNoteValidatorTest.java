package notes.project.logic.validation;

import notes.project.logic.model.AccessMode;
import notes.project.logic.utils.ApiUtils;
import notes.project.logic.utils.IntegrationTestUtils;
import notes.project.logic.validation.dto.UpdateNoteValidationDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.access.AccessDeniedException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UpdateNoteValidatorTest {
    private Validator<UpdateNoteValidationDto> validator;

    @BeforeEach
    void init() {
        validator = new UpdateNoteValidator();
    }

    @Test
    void validateSuccess() {
        UpdateNoteValidationDto validationDto = ApiUtils.updateNoteValidationDto();

        assertDoesNotThrow(() -> validator.validate(validationDto));
    }

    @Test
    void validateThrow() {
        UpdateNoteValidationDto validationDto = ApiUtils.updateNoteValidationDto();
        validationDto.getAccess().setAccessMode(AccessMode.READ);

        assertThrows(
            AccessDeniedException.class,
            () -> validator.validate(validationDto)
        );
    }
}
