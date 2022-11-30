package notes.project.logic.validation.impl;

import java.util.List;
import javax.validation.Valid;

import notes.project.logic.exception.ExceptionCode;
import notes.project.logic.utils.helper.ValidationHelper;
import notes.project.logic.validation.Validator;
import org.springframework.stereotype.Component;

@Component
public class DenyAccessValidator implements Validator<List<Boolean>> {

    @Override
    public void validate(List<Boolean> source) {
        ValidationHelper.getInstance().validate(
            source,
            item -> item.stream().allMatch(Boolean.TRUE::equals),
            ExceptionCode.CAN_NOT_DENY_ACCESS
        ).throwIfNotValid();
    }
}
