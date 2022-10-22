package notes.project.logic.validation.impl;

import notes.project.logic.validation.Validator;
import notes.project.logic.validation.dto.ReadNoteValidationDto;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;

@Component
public class ReadNoteValidator implements Validator<ReadNoteValidationDto> {
    @Override
    public void validate(ReadNoteValidationDto source) {
        if(!source.getNote().getClient().getExternalId().equals(source.getClientExternalId()) && BooleanUtils.isNotTrue(source.getHasAccess())) {
            throw new AccessDeniedException("User " + source.getClientExternalId() + " does not have note " + source.getNote().getExternalId());
        }
    }
}
