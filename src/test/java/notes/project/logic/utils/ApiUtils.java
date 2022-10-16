package notes.project.logic.utils;

import java.util.Collections;

import liquibase.pro.packaged.M;
import lombok.experimental.UtilityClass;
import notes.project.logic.dto.*;
import notes.project.logic.dto.api.*;
import notes.project.logic.dto.integration.filesystem.CreateClusterRequestDto;
import notes.project.logic.dto.integration.filesystem.CreateClusterResponseDto;
import notes.project.logic.exception.ExceptionCode;
import notes.project.logic.exception.ValidationException;

import static notes.project.logic.utils.TestDataConstants.*;

@UtilityClass
public class ApiUtils {
    public static MoveNoteResponseDto moveNoteResponseDto() {
        return new MoveNoteResponseDto()
            .setCreatedFileExternalId(NOTE_EXTERNAL_ID)
            .setNewDirectoryExternalId(DIRECTORY_ALTERNATE_EXTERNAL_ID)
            .setReplacingDate(NOTE_MOVING_DATE);
    }

    public static MoveNoteRequestDto moveNoteRequestDto() {
        return new MoveNoteRequestDto()
            .setCreatedFileExternalId(NOTE_EXTERNAL_ID)
            .setNewDirectoryExternalId(DIRECTORY_ALTERNATE_EXTERNAL_ID);
    }

    public static CreateNoteRequestDto createNoteRequestDto() {
        return new CreateNoteRequestDto()
            .setContent(NOTE_CONTENT)
            .setDirectoryExternalId(DIRECTORY_EXTERNAL_ID)
            .setTitle(NOTE_TITLE);
    }

    public static CreateNoteResponseDto createNoteResponseDto() {
        return new CreateNoteResponseDto()
            .setCreatedDate(NOTE_CREATED_DATE)
            .setExternalId(NOTE_EXTERNAL_ID)
            .setTitle(NOTE_TITLE);
    }

    public static CreateDirectoryResponseDto createDirectoryResponseDto() {
        return new CreateDirectoryResponseDto()
            .setClusterName(CLUSTER_TITLE)
            .setCreationDate(DIRECTORY_CREATION_TIME)
            .setDirectoryName(DIRECTORY_NAME)
            .setExternalId(DIRECTORY_EXTERNAL_ID);
    }

    public static CreateDirectoryRequestDto createDirectoryRequestDto() {
        return new CreateDirectoryRequestDto()
            .setDirectoryName(DIRECTORY_NAME)
            .setClusterExternalId(CLUSTER_EXTERNAL_ID);
    }

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
