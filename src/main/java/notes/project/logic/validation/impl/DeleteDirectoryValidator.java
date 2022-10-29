package notes.project.logic.validation.impl;

import notes.project.logic.exception.ExceptionCode;
import notes.project.logic.utils.helper.ValidationHelper;
import notes.project.logic.validation.Validator;
import notes.project.logic.validation.dto.DeleteDirectoryValidationDto;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;

@Component
public class DeleteDirectoryValidator implements Validator<DeleteDirectoryValidationDto> {

    @Override
    public void validate(DeleteDirectoryValidationDto source) {
        if(BooleanUtils.isFalse(source.getDirectory().getClient().getExternalId().equals(source.getClientExternalId()))) {
            throw new AccessDeniedException("User " + source.getClientExternalId() + " has no access to directory " + source.getDirectory().getExternalId());
        }
    }
}
