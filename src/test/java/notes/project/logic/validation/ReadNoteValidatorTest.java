package notes.project.logic.validation;

import java.util.UUID;

import notes.project.logic.utils.ApiUtils;
import notes.project.logic.utils.DbUtils;
import notes.project.logic.validation.dto.ReadNoteValidationDto;
import notes.project.logic.validation.impl.ReadNoteValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.access.AccessDeniedException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ReadNoteValidatorTest {

    private Validator<ReadNoteValidationDto> validator;

    @BeforeEach
    void init() {
        validator = new ReadNoteValidator();
    }

    @Test
    void validateSuccess() {
        ReadNoteValidationDto dto = ApiUtils.readNoteValidationDto();

        assertDoesNotThrow(() -> validator.validate(dto));
    }

    @Test
    void validateThrow() {
        ReadNoteValidationDto dto = ApiUtils.readNoteValidationDto();
        dto.setClientExternalId(UUID.randomUUID());

        assertThrows(
            AccessDeniedException.class,
            () -> validator.validate(dto)
        );
    }
}
