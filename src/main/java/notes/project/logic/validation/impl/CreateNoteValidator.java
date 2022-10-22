package notes.project.logic.validation.impl;

import notes.project.logic.validation.Validator;
import notes.project.logic.validation.dto.CreateNoteValidationDto;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;

@Component
public class CreateNoteValidator implements Validator<CreateNoteValidationDto> {
    @Override
    public void validate(CreateNoteValidationDto source) {
        if(!source.getDirectory().getClient().getExternalId().equals(source.getClientExternalId())) {
            throw new AccessDeniedException("Directory " + source.getDirectory().getExternalId() + " is not owned by user " + source.getClientExternalId());
        }
    }
}
