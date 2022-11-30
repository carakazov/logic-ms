package notes.project.logic.validation;

import java.util.Collections;
import java.util.List;

import notes.project.logic.exception.ValidationException;
import notes.project.logic.validation.impl.DenyAccessValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DenyAccessValidatorTest {
    private Validator<List<Boolean>> validator;

    @BeforeEach
    void init() {
        validator = new DenyAccessValidator();
    }

    @Test
    void validateSuccess() {
        assertDoesNotThrow(() -> validator.validate(Collections.singletonList(Boolean.TRUE)));
    }

    @Test
    void validateThrow() {
        assertThrows(
            ValidationException.class,
            () -> validator.validate(List.of(Boolean.TRUE, Boolean.FALSE))
        );
    }
}
