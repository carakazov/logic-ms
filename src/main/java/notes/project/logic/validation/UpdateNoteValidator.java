package notes.project.logic.validation;

import java.util.Objects;

import notes.project.logic.model.AccessMode;
import notes.project.logic.validation.dto.UpdateNoteValidationDto;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;

@Component
public class UpdateNoteValidator implements Validator<UpdateNoteValidationDto> {
    @Override
    public void validate(UpdateNoteValidationDto source) {
        if(!AccessMode.READ_WRITE.equals(source.getAccess().getAccessMode())) {
            throw new AccessDeniedException("Current client does not have access to update this note");
        }
    }
}
