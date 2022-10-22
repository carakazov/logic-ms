package notes.project.logic.validation.impl;

import notes.project.logic.validation.Validator;
import notes.project.logic.validation.dto.DeleteNoteValidationDto;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;

@Component
public class DeleteNoteValidator implements Validator<DeleteNoteValidationDto> {
    @Override
    public void validate(DeleteNoteValidationDto source) {
        if(!source.getNote().getClient().getExternalId().equals(source.getClientExternalId())) {
            throw new AccessDeniedException("User " + source.getClientExternalId() + " has no access to " + source.getNote().getExternalId());
        }
    }
}
