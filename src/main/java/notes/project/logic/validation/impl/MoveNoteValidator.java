package notes.project.logic.validation.impl;

import notes.project.logic.exception.ExceptionCode;
import notes.project.logic.utils.helper.ValidationHelper;
import notes.project.logic.validation.Validator;
import notes.project.logic.validation.dto.MoveNoteValidationDto;
import org.springframework.stereotype.Component;

@Component
public class MoveNoteValidator implements Validator<MoveNoteValidationDto> {
    @Override
    public void validate(MoveNoteValidationDto source) {
        ValidationHelper.getInstance()
            .validate(
                source,
                item -> item.getDirectory().getClient().getExternalId().equals(item.getClientExternalId()),
                ExceptionCode.NOT_YOUR_DIRECTORY
            ).throwIfNotValid();
    }
}
