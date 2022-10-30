package notes.project.logic.validation.impl;

import notes.project.logic.validation.Validator;
import notes.project.logic.validation.dto.OwningValidationDto;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;

@Component
public class OwningValidator implements Validator<OwningValidationDto> {
    @Override
    public void validate(OwningValidationDto source) {
        if(BooleanUtils.isFalse(source.getObjectOwnerExternalId().equals(source.getClientExternalIdFromContext()))) {
            throw new AccessDeniedException("Current user has no access to object");
        }
    }
}
