package notes.project.logic.utils;

import java.util.Collections;

import lombok.experimental.UtilityClass;
import notes.project.logic.dto.*;
import notes.project.logic.dto.api.AdditionalInfoDto;
import notes.project.logic.dto.api.PersonalInfoDto;
import notes.project.logic.dto.integration.filesystem.CreateClusterRequestDto;
import notes.project.logic.dto.integration.filesystem.CreateClusterResponseDto;
import notes.project.logic.exception.ExceptionCode;
import notes.project.logic.exception.ValidationException;

import static notes.project.logic.utils.TestDataConstants.*;

@UtilityClass
public class ApiUtils {
    public static PersonalInfoDto personalInfoDto() {
        return new PersonalInfoDto()
            .setName(CLIENT_NAME)
            .setSurname(CLIENT_SURNAME)
            .setMiddleName(CLIENT_MIDDLE_NAME)
            .setBirthDate(CLIENT_BIRTH_DATE)
            .setExternalId(CLIENT_EXTERNAL_ID)
            .setAdditionalInfo(Collections.singletonList(additionalInfoDto()));
    }

    public static AdditionalInfoDto additionalInfoDto() {
        return new AdditionalInfoDto()
            .setType(CLIENT_ADDITIONAL_INFO_TYPE)
            .setValue(CLIENT_ADDITIONAL_INFO_VALUE);
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
