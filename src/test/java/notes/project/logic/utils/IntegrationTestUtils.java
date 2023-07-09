package notes.project.logic.utils;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Map;

import lombok.experimental.UtilityClass;
import notes.project.logic.dto.api.ChangeAdditionalInfoDto;
import notes.project.logic.dto.integration.filesystem.*;
import notes.project.logic.dto.integration.rabbit.EventCode;
import notes.project.logic.dto.integration.rabbit.FileSystemMessage;
import notes.project.logic.dto.integration.userdatasystem.*;
import notes.project.logic.oauth.dto.InternalServerTokenResponseDto;
import notes.project.logic.oauth.dto.KeycloakServerTokenResponseDto;

import static notes.project.logic.utils.TestDataConstants.*;

@UtilityClass
public class IntegrationTestUtils {
    public static FileSystemMessage fileSystemMessage() {
        return new FileSystemMessage(EventCode.CLUSTER_WILL_BE_DELETED_SOON.getCode(), CLUSTER_EXTERNAL_ID, DAYS_TO_DELETE);
    }

    public static UserDataSystemAllClientsResponseDto userDataSystemAllClientsResponseDto() {
        return new UserDataSystemAllClientsResponseDto()
            .setSystemName(SYSTEM_NAME)
            .setClients(Collections.singletonList(userDataSystemPersonalInfoDto()));
    }

    public static UserDataSystemChangePersonalInfoRequestDto userDataSystemChangePersonalInfoRequestDto() {
        return new UserDataSystemChangePersonalInfoRequestDto()
            .setClientExternalId(CLIENT_EXTERNAL_ID)
            .setClientInfo(userDataSystemChangePersonalInfoDto());
    }

    public static UserDataSystemChangePersonalInfoDto userDataSystemChangePersonalInfoDto() {
        return new UserDataSystemChangePersonalInfoDto()
            .setNewValues(
                Map.of(
                    ChangePersonalInfoMarker.NEW_NAME, CLIENT_NAME,
                    ChangePersonalInfoMarker.NEW_SURNAME, CLIENT_SURNAME,
                    ChangePersonalInfoMarker.NEW_MIDDLE_NAME, CLIENT_MIDDLE_NAME
                )
            ).setChangeAdditionalInfo(
                Collections.singletonList(userDataSystemChangeAdditionalInfoDto())
            );
    }

    public static UserDataSystemChangeAdditionalInfoDto userDataSystemChangeAdditionalInfoDto() {
        return new UserDataSystemChangeAdditionalInfoDto()
            .setType(CLIENT_ADDITIONAL_INFO_TYPE)
            .setNewValue(CLIENT_ADDITIONAL_INFO_VALUE);
    }

    public static FileSystemClusterDto fileSystemClusterDto() {
        return new FileSystemClusterDto()
            .setTitle(CLUSTER_TITLE)
            .setExternalId(CLIENT_EXTERNAL_ID)
            .setCreationDate(CLUSTER_CREATE_DATE)
            .setDirectories(Collections.singletonList(fileSystemDirectoryInfoDto()));
    }

    public static FileSystemDirectoryInfoDto fileSystemDirectoryInfoDto() {
        return new FileSystemDirectoryInfoDto()
            .setCreationDate(DIRECTORY_CREATION_TIME)
            .setExternalId(DIRECTORY_EXTERNAL_ID)
            .setTitle(DIRECTORY_NAME);
    }

    public static FileSystemDirectoryDto fileSystemDirectoryDto() {
        return new FileSystemDirectoryDto()
            .setCreationDate(DIRECTORY_CREATION_TIME)
            .setTitle(DIRECTORY_NAME)
            .setExternalId(DIRECTORY_EXTERNAL_ID)
            .setFiles(Collections.singletonList(fileSystemFileInfoDto()));
    }

    public static FileSystemFileInfoDto fileSystemFileInfoDto() {
        return new FileSystemFileInfoDto()
            .setCreationDate(NOTE_CREATED_DATE)
            .setTitle(NOTE_TITLE)
            .setExternalId(NOTE_EXTERNAL_ID);
    }

    public static FileSystemFileVersionDto fileSystemFileVersionDto() {
        return new FileSystemFileVersionDto()
            .setContent(NOTE_CONTENT);
    }

    public static FileSystemReplacingHistoryResponseDto fileSystemReplacingHistoryResponseDto() {
        return new FileSystemReplacingHistoryResponseDto()
            .setFile(fileSystemFileDto())
            .setHistory(Collections.singletonList(fileSystemReplacingHistoryRecordDto()));
    }

    public static FileSystemReplacingHistoryRecordDto fileSystemReplacingHistoryRecordDto() {
        return new FileSystemReplacingHistoryRecordDto()
            .setReplacingDate(NOTE_MOVING_DATE)
            .setSourceDirectory(fileSystemSourceDirectoryReplacingHistoryDto())
            .setTargetDirectory(fileSystemTargetDirectoryReplacingHistoryDto());
    }

    public static FileSystemDirectoryReplacingHistoryDto fileSystemTargetDirectoryReplacingHistoryDto() {
        return new FileSystemDirectoryReplacingHistoryDto()
            .setDirectoryTitle(DIRECTORY_NAME)
            .setDirectoryExternalId(DIRECTORY_ALTERNATE_EXTERNAL_ID);
    }

    public static FileSystemDirectoryReplacingHistoryDto fileSystemSourceDirectoryReplacingHistoryDto() {
        return new FileSystemDirectoryReplacingHistoryDto()
            .setDirectoryTitle(DIRECTORY_NAME)
            .setDirectoryExternalId(DIRECTORY_EXTERNAL_ID);
    }

    public static FileSystemDeleteHistoryResponseDto fileSystemDeleteHistoryResponseDto(String objectTitle, LocalDateTime cretedDate) {
        return new FileSystemDeleteHistoryResponseDto()
            .setCreatedDate(cretedDate)
            .setObjectTitle(objectTitle)
            .setCurrentState(CURRENT_STATE)
            .setHistory(Collections.singletonList(fileSystemDeleteHistoryRecordDto()));
    }

    public static FileSystemDeleteHistoryRecordDto fileSystemDeleteHistoryRecordDto() {
        return new FileSystemDeleteHistoryRecordDto()
            .setEvent(DELETE_HISTORY_EVENT)
            .setEventDate(DELETE_HISTORY_EVENT_DATE);
    }

    public static FileSystemArchiveResponseDto fileSystemArchiveResponseDto() {
        return new FileSystemArchiveResponseDto()
            .setFile(fileSystemFileDto())
            .setHistory(Collections.singletonList(fileSystemHistoryDto()));
    }

    public static FileSystemFileDto fileSystemFileDto() {
        return new FileSystemFileDto()
            .setFileTitle(NOTE_TITLE)
            .setFileExternalId(NOTE_EXTERNAL_ID);
    }

    public static FileSystemHistoryDto fileSystemHistoryDto() {
        return new FileSystemHistoryDto()
            .setEditedDate(EDITED_DATE)
            .setVersionFileGuid(VERSION_FILE_GUID);
    }

    public static FileSystemUpdateFileRequestDto fileSystemUpdateFileRequestDto() {
        return new FileSystemUpdateFileRequestDto()
            .setContent(NEW_NOTE_CONTENT);
    }

    public static FileSystemFileResponseDto fileSystemFileResponseDto() {
        return new FileSystemFileResponseDto()
            .setContent(NOTE_CONTENT)
            .setCreationDate(NOTE_CREATED_DATE)
            .setTitle(NOTE_TITLE);
    }

    public static FileSystemChangeFileDirectoryRequestDto fileSystemChangeFileDirectoryRequestDto(){
        return new FileSystemChangeFileDirectoryRequestDto()
            .setCreatedFileExternalId(NOTE_EXTERNAL_ID)
            .setNewDirectoryExternalId(DIRECTORY_ALTERNATE_EXTERNAL_ID);
    }

    public static FileSystemChangeFileDirectoryResponseDto fileSystemChangeFileDirectoryResponseDto() {
        return new FileSystemChangeFileDirectoryResponseDto()
            .setCreatedFileExternalId(NOTE_EXTERNAL_ID)
            .setNewDirectoryExternalId(DIRECTORY_ALTERNATE_EXTERNAL_ID)
            .setReplacingDate(NOTE_MOVING_DATE);
    }

    public static FileSystemCreateFileRequestDto fileSystemCreateFileRequestDto() {
        return new FileSystemCreateFileRequestDto()
            .setContent(NOTE_CONTENT)
            .setDirectoryExternalId(DIRECTORY_EXTERNAL_ID)
            .setTitle(NOTE_TITLE);
    }

    public static FileSystemCreateFileResponseDto fileSystemCreateFileResponseDto() {
        return new FileSystemCreateFileResponseDto()
            .setCreatedDate(NOTE_CREATED_DATE)
            .setExternalId(NOTE_EXTERNAL_ID)
            .setTitle(NOTE_TITLE);
    }

    public static FileSystemCreateDirectoryResponseDto fileSystemCreateDirectoryResponseDto() {
        return new FileSystemCreateDirectoryResponseDto()
            .setClusterName(CLUSTER_TITLE)
            .setCreationDate(DIRECTORY_CREATION_TIME)
            .setDirectoryName(DIRECTORY_NAME)
            .setExternalId(DIRECTORY_EXTERNAL_ID);
    }

    public static FileSystemCreateDirectoryRequestDto fileSystemCreateDirectoryRequestDto() {
        return new FileSystemCreateDirectoryRequestDto()
            .setDirectoryName(DIRECTORY_NAME)
            .setClusterExternalId(CLUSTER_EXTERNAL_ID);
    }

    public static UserDataSystemPersonalInfoDto userDataSystemPersonalInfoDto() {
        return new UserDataSystemPersonalInfoDto()
            .setName(CLIENT_NAME)
            .setSurname(CLIENT_SURNAME)
            .setMiddleName(CLIENT_MIDDLE_NAME)
            .setBirthDate(CLIENT_BIRTH_DATE)
            .setExternalId(CLIENT_EXTERNAL_ID)
            .setAdditionalInfo(Collections.singletonList(userDataSystemAdditionalInfoDto()));
    }

    public static UserDataSystemAdditionalInfoDto userDataSystemAdditionalInfoDto() {
        return new UserDataSystemAdditionalInfoDto()
            .setType(CLIENT_ADDITIONAL_INFO_TYPE)
            .setValue(CLIENT_ADDITIONAL_INFO_VALUE);
    }

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

    public static KeycloakServerTokenResponseDto keycloakServerTokenResponseDto() {
        return (KeycloakServerTokenResponseDto) new KeycloakServerTokenResponseDto()
            .setRefreshTokenExpiresIn(REFRESH_TOKEN_EXPIRES_IN)
            .setRefreshToken(REFRESH_TOKEN)
            .setNotBeforePolicy(NOT_BEFORE_POLICY)
            .setSessionState(SESSION_STATE)
            .setAccessToken(ACCESS_TOKEN)
            .setTokenType(TOKEN_TYPE)
            .setExpiresIn(EXPIRES_IN)
            .setScope(SCOPE);
    }

    public static InternalServerTokenResponseDto internalServerTokenResponseDto() {
        return (InternalServerTokenResponseDto) new InternalServerTokenResponseDto()
            .setJti(JTI)
            .setAccessToken(ACCESS_TOKEN)
            .setTokenType(TOKEN_TYPE)
            .setExpiresIn(EXPIRES_IN)
            .setScope(SCOPE);
    }
}
