package notes.project.logic.validation;

import java.util.UUID;

import io.swagger.annotations.Api;
import notes.project.logic.utils.ApiUtils;
import notes.project.logic.validation.dto.OwningValidationDto;
import notes.project.logic.validation.impl.OwningValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.access.AccessDeniedException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OwningValidatorTest {
    private Validator<OwningValidationDto> validator;

    @BeforeEach
    void init() {
        validator = new OwningValidator();
    }

    @Test
    void validateSuccess() {
        OwningValidationDto validationDto = ApiUtils.owningValidationDto();

        assertDoesNotThrow(() -> validator.validate(validationDto));
    }

    @Test
    void validateThrow() {
        OwningValidationDto validationDto = ApiUtils.owningValidationDto().setObjectOwnerExternalId(UUID.randomUUID());

        assertThrows(
            AccessDeniedException.class,
            () -> validator.validate(validationDto)
        );
    }
}
