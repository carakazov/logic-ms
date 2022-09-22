package notes.project.logic.utils;

import java.util.Collections;

import lombok.experimental.UtilityClass;
import notes.project.logic.dto.*;
import notes.project.logic.dto.integration.filesystem.CreateClusterRequestDto;
import notes.project.logic.dto.integration.filesystem.CreateClusterResponseDto;
import notes.project.logic.exception.ExceptionCode;
import notes.project.logic.exception.ValidationException;

import static notes.project.logic.utils.TestDataConstants.*;

@UtilityClass
public class ApiUtils {
    public static CreateClusterRequestDto createClusterRequestDto() {
        return new CreateClusterRequestDto()
            .setClusterTitle(CLUSTER_TITLE);
    }

    public static CreateClusterResponseDto createClusterResponseDto() {
        return new CreateClusterResponseDto()
            .setCreateDate(CLUSTER_CREATE_DATE)
            .setExternalId(CLUSTER_EXTERNAL_ID)
            .setTitle(CLUSTER_TITLE);
    }

    public static ErrorDto errorDto() {
        return new ErrorDto()
                .setCode(EXCEPTION_CODE)
                .setMessage(EXCEPTION_MESSAGE);
    }

    public static ValidationErrorDto validationErrorDto() {
        return new ValidationErrorDto(Collections.singletonList(errorDto()));
    }

    public static ValidationException validationException() {
        ValidationException validationException = new ValidationException();
        validationException.addCode(ExceptionCode.INTERNAL_ERROR);
        return validationException;
    }
}
