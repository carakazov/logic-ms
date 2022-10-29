package notes.project.logic.validation;

import java.util.UUID;

import notes.project.logic.utils.ApiUtils;
import notes.project.logic.validation.dto.DeleteDirectoryValidationDto;
import notes.project.logic.validation.impl.DeleteDirectoryValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.access.AccessDeniedException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DeleteDirectoryValidatorTest {
    private Validator<DeleteDirectoryValidationDto> validator;

    @BeforeEach
    void init() {
        validator = new DeleteDirectoryValidator();
    }

    @Test
    void validateSuccess() {
        DeleteDirectoryValidationDto validationDto = ApiUtils.deleteDirectoryValidationDto();

        assertDoesNotThrow(() -> validator.validate(validationDto));
    }

    @Test
    void validateThrow() {
        DeleteDirectoryValidationDto validationDto = ApiUtils.deleteDirectoryValidationDto().setClientExternalId(UUID.randomUUID());

        assertThrows(
            AccessDeniedException.class,
            () -> validator.validate(validationDto)
        );
    }
}
