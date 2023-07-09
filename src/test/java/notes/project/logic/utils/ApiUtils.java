package notes.project.logic.utils;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Map;

import lombok.experimental.UtilityClass;
import notes.project.logic.dto.*;
import notes.project.logic.dto.api.*;
import notes.project.logic.dto.integration.userdatasystem.ChangePersonalInfoMarker;
import notes.project.logic.exception.ExceptionCode;
import notes.project.logic.exception.ValidationException;
import notes.project.logic.model.AccessMode;
import notes.project.logic.validation.dto.*;

import static notes.project.logic.utils.TestDataConstants.*;

@UtilityClass
public class ApiUtils {
    public static AccessorsListResponseDto accessorsListResponseDto() {
        return new AccessorsListResponseDto()
            .setExternalId(NOTE_EXTERNAL_ID)
            .setAccessors(Collections.singletonList(accessorDto()));
    }

    public static AccessorDto accessorDto() {
        return new AccessorDto()
            .setExternalId(CLIENT_EXTERNAL_ID)
            .setName(CLIENT_NAME)
            .setSurname(CLIENT_SURNAME)
            .setMiddleName(CLIENT_MIDDLE_NAME)
            .setAccessMode(AccessMode.READ_WRITE);
    }

    public static ChangeAccessModeRequestDto changeAccessModeRequestDto() {
        return new ChangeAccessModeRequestDto()
            .setNoteExternalId(NOTE_EXTERNAL_ID)
            .setClientExternalId(CLIENT_EXTERNAL_ID)
            .setAccessMode(AccessMode.READ);
    }

    public static AllClientsResponseDto allClientsResponseDto() {
        return new AllClientsResponseDto()
            .setSystemName(SYSTEM_NAME)
            .setClients(Collections.singletonList(personalInfoDto()));
    }

    public static ChangePersonalInfoRequestDto changePersonalInfoRequestDto() {
        return new ChangePersonalInfoRequestDto()
            .setNewValues(
                Map.of(
                    ChangePersonalInfoMarker.NEW_NAME, CLIENT_NAME,
                    ChangePersonalInfoMarker.NEW_SURNAME, CLIENT_SURNAME,
                    ChangePersonalInfoMarker.NEW_MIDDLE_NAME, CLIENT_MIDDLE_NAME
                )
            ).setChangeAdditionalInfo(Collections.singletonList(changeAdditionalInfoDto()));
    }

    public static ChangeAdditionalInfoDto changeAdditionalInfoDto() {
        return new ChangeAdditionalInfoDto()
            .setType(CLIENT_ADDITIONAL_INFO_TYPE)
            .setNewValue(CLIENT_ADDITIONAL_INFO_VALUE);
    }

    public static ClusterDto clusterDto() {
        return new ClusterDto()
            .setTitle(CLUSTER_TITLE)
            .setExternalId(CLIENT_EXTERNAL_ID)
            .setCreationDate(CLUSTER_CREATE_DATE)
            .setDirectories(Collections.singletonList(directoryInfoDto()));
    }

    public static DirectoryInfoDto directoryInfoDto() {
        return new DirectoryInfoDto()
            .setCreationDate(DIRECTORY_CREATION_TIME)
            .setExternalId(DIRECTORY_EXTERNAL_ID)
            .setTitle(DIRECTORY_NAME);
    }

    public static DirectoryDto directoryDto() {
        return new DirectoryDto()
            .setCreationDate(DIRECTORY_CREATION_TIME)
            .setTitle(DIRECTORY_NAME)
            .setExternalId(DIRECTORY_EXTERNAL_ID)
            .setNotes(Collections.singletonList(noteInfoDto()));
    }

    public static NoteInfoDto noteInfoDto() {
        return new NoteInfoDto()
            .setCreationDate(NOTE_CREATED_DATE)
            .setTitle(NOTE_TITLE)
            .setExternalId(NOTE_EXTERNAL_ID);
    }

    public static OwningValidationDto owningValidationDto() {
        return new OwningValidationDto(
            CLIENT_EXTERNAL_ID,
            CLIENT_EXTERNAL_ID
        );
    }

    public static DeleteDirectoryValidationDto deleteDirectoryValidationDto() {
        return new DeleteDirectoryValidationDto()
            .setClientExternalId(CLIENT_EXTERNAL_ID)
            .setDirectory(DbUtils.directory());
    }

    public static MoveNoteValidationDto moveNoteValidationDto() {
        return new MoveNoteValidationDto()
            .setClientExternalId(CLIENT_EXTERNAL_ID)
            .setDirectory(DbUtils.directory());
    }

    public static NoteVersionResponseDto noteVersionResponseDto() {
        return new NoteVersionResponseDto()
            .setContent(NOTE_CONTENT);
    }

    public static NoteReplacingHistoryResponseDto noteReplacingHistoryResponseDto() {
        return new NoteReplacingHistoryResponseDto()
            .setNote(noteReplacingHistoryDto())
            .setHistory(Collections.singletonList(replacingHistoryRecordDto()));
    }

    public static NoteReplacingHistoryDto noteReplacingHistoryDto() {
        return new NoteReplacingHistoryDto()
            .setNoteTitle(NOTE_TITLE)
            .setNoteExternalId(NOTE_EXTERNAL_ID);
    }

    public static ReplacingHistoryRecordDto replacingHistoryRecordDto() {
        return new ReplacingHistoryRecordDto()
            .setReplacingDate(NOTE_MOVING_DATE)
            .setSourceDirectory(directorySourceReplacingHistoryDto())
            .setTargetDirectory(directoryTargetReplacingHistoryDto());
    }

    public static DirectoryReplacingHistoryDto directoryTargetReplacingHistoryDto() {
        return new DirectoryReplacingHistoryDto()
            .setDirectoryTitle(DIRECTORY_NAME)
            .setDirectoryExternalId(DIRECTORY_ALTERNATE_EXTERNAL_ID);
    }

    public static DirectoryReplacingHistoryDto directorySourceReplacingHistoryDto() {
        return new DirectoryReplacingHistoryDto()
            .setDirectoryTitle(DIRECTORY_NAME)
            .setDirectoryExternalId(DIRECTORY_EXTERNAL_ID);
    }

    public static DeleteHistoryResponseDto deleteHistoryResponseDto(String objectTitle, LocalDateTime cretedDate) {
        return new DeleteHistoryResponseDto()
            .setCreatedDate(cretedDate)
            .setObjectTitle(objectTitle)
            .setCurrentState(CURRENT_STATE)
            .setHistory(Collections.singletonList(deleteHistoryRecordDto()));
    }

    public static DeleteHistoryRecordDto deleteHistoryRecordDto() {
        return new DeleteHistoryRecordDto()
            .setEvent(DELETE_HISTORY_EVENT)
            .setEventDate(DELETE_HISTORY_EVENT_DATE);
    }

    public static NoteHistoryResponseDto noteHistoryResponseDto() {
        return new NoteHistoryResponseDto()
            .setNote(noteHistoryDto())
            .setHistory(Collections.singletonList(noteHistoryRecordDto()));
    }

    public static NoteHistoryDto noteHistoryDto() {
        return new NoteHistoryDto()
            .setNoteTitle(NOTE_TITLE)
            .setNoteExternalId(NOTE_EXTERNAL_ID);
    }

    public static NoteHistoryRecordDto noteHistoryRecordDto() {
        return new NoteHistoryRecordDto()
            .setEditedDate(EDITED_DATE)
            .setVersionNoteGuid(VERSION_FILE_GUID);
    }

    public static DeleteNoteValidationDto deleteNoteValidationDto() {
        return new DeleteNoteValidationDto()
            .setNote(DbUtils.note())
            .setClientExternalId(CLIENT_EXTERNAL_ID);
    }

    public static CreateNoteValidationDto createNoteValidationDto() {
        return new CreateNoteValidationDto()
            .setDirectory(DbUtils.directory())
            .setClientExternalId(CLIENT_EXTERNAL_ID);
    }

    public static UpdateNoteValidationDto updateNoteValidationDto() {
        return new UpdateNoteValidationDto(DbUtils.access());
    }

    public static UpdateNoteRequestDto updateNoteRequestDto() {
        return new UpdateNoteRequestDto()
            .setContent(NEW_NOTE_CONTENT);
    }

    public static NoteResponseDto noteResponseDto() {
        return new NoteResponseDto()
            .setNote(noteDto())
            .setAccessMode(AccessMode.READ_WRITE);
    }

    public static NoteDto noteDto() {
        return new NoteDto()
            .setContent(NOTE_CONTENT)
            .setCreationDate(NOTE_CREATED_DATE)
            .setTitle(NOTE_TITLE);
    }

    public static ReadNoteValidationDto readNoteValidationDto() {
        return new ReadNoteValidationDto()
            .setNote(DbUtils.note())
            .setClientExternalId(CLIENT_EXTERNAL_ID);
    }

    public static MoveNoteResponseDto moveNoteResponseDto() {
        return new MoveNoteResponseDto()
            .setCreatedFileExternalId(NOTE_EXTERNAL_ID)
            .setNewDirectoryExternalId(DIRECTORY_ALTERNATE_EXTERNAL_ID)
            .setReplacingDate(NOTE_MOVING_DATE);
    }

    public static MoveNoteRequestDto moveNoteRequestDto() {
        return new MoveNoteRequestDto()
            .setCreatedNoteExternalId(NOTE_EXTERNAL_ID)
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
            .setDirectoryName(DIRECTORY_NAME);
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
