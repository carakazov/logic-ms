package notes.project.logic.service.api;

import java.util.Optional;

import javax.validation.Valid;

import io.swagger.annotations.Api;
import notes.project.logic.dto.api.*;
import notes.project.logic.model.Access;
import notes.project.logic.model.Client;
import notes.project.logic.model.Note;
import notes.project.logic.repository.NoteRepository;
import notes.project.logic.service.api.impl.NoteServiceImpl;
import notes.project.logic.service.integration.http.FileSystemRestService;
import notes.project.logic.utils.*;
import notes.project.logic.utils.mapper.*;
import notes.project.logic.validation.Validator;
import notes.project.logic.validation.dto.*;
import notes.project.logic.validation.impl.OwningValidator;
import org.apache.zookeeper.Op;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.data.neo4j.DataNeo4jTest;

import static notes.project.logic.utils.TestDataConstants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class NoteServiceImplTest {
    @Mock
    private NoteRepository repository;
    @Mock
    private ClientService clientService;
    @Mock
    private DirectoryService directoryService;
    @Mock
    private AuthHelper authHelper;
    @Mock
    private FileSystemRestService fileSystemRestService;
    @Mock
    private Validator<ReadNoteValidationDto> readNoteValidator;
    @Mock
    private AccessService accessService;
    @Mock
    private Validator<UpdateNoteValidationDto> updateNoteValidator;
    @Mock
    private Validator<MoveNoteValidationDto> moveNoteValidator;
    @Mock
    private Validator<OwningValidationDto> owningValidator;

    private NoteService service;

    @BeforeEach
    void init() {
        service = new NoteServiceImpl(
            repository,
            clientService,
            directoryService,
            authHelper,
            Mappers.getMapper(CreateFileMapper.class),
            fileSystemRestService,
            Mappers.getMapper(ChangeDirectoryMapper.class),
            TestUtils.getComplexMapper(NoteResponseMapper.class),
            readNoteValidator,
            accessService,
            updateNoteValidator,
            Mappers.getMapper(UpdateNoteMapper.class),
            TestUtils.getComplexMapper(NoteHistoryResponseMapper.class),
            TestUtils.getComplexMapper(DeleteHistoryResponseMapper.class),
            TestUtils.getComplexMapper(ReplacingHistoryResponseMapper.class),
            Mappers.getMapper(NoteVersionMapper.class),
            moveNoteValidator,
            owningValidator
        );
    }

    @Test
    void createNoteSuccess() {
        CreateNoteResponseDto expected = ApiUtils.createNoteResponseDto();

        when(authHelper.getAuthorizedClientId()).thenReturn(CLIENT_EXTERNAL_ID);
        when(fileSystemRestService.createFile(any())).thenReturn(IntegrationTestUtils.fileSystemCreateFileResponseDto());
        when(clientService.findByExternalId(any())).thenReturn(DbUtils.client());
        when(directoryService.findDirectoryByExternalId(any())).thenReturn(DbUtils.directory());
        when(repository.save(any())).thenReturn(DbUtils.note());

        CreateNoteResponseDto actual = service.createNote(ApiUtils.createNoteRequestDto());

        assertEquals(expected, actual);

        verify(authHelper).getAuthorizedClientId();
        verify(fileSystemRestService).createFile(IntegrationTestUtils.fileSystemCreateFileRequestDto());
        verify(clientService).findByExternalId(CLIENT_EXTERNAL_ID);
        verify(directoryService).findDirectoryByExternalId(DIRECTORY_EXTERNAL_ID);
        verify(repository).save(DbUtils.note().setId(null));
    }

    @Test
    void moveDirectorySuccess() {
        MoveNoteResponseDto expected = ApiUtils.moveNoteResponseDto();

        when(fileSystemRestService.changeFileDirectory(any())).thenReturn(IntegrationTestUtils.fileSystemChangeFileDirectoryResponseDto());
        when(repository.findByExternalId(any())).thenReturn(Optional.of(DbUtils.note()));
        when(directoryService.findDirectoryByExternalId(any())).thenReturn(DbUtils.alternateDirectory());

        MoveNoteResponseDto actual = service.moveNote(ApiUtils.moveNoteRequestDto());

        assertEquals(expected, actual);

        verify(fileSystemRestService).changeFileDirectory(IntegrationTestUtils.fileSystemChangeFileDirectoryRequestDto());
        verify(repository).findByExternalId(NOTE_EXTERNAL_ID);
        verify(directoryService).findDirectoryByExternalId(DIRECTORY_ALTERNATE_EXTERNAL_ID);
    }

    @Test
    void readNoteSuccess() {
        NoteResponseDto expected = ApiUtils.noteResponseDto();


        Note note = DbUtils.note();
        Access access = DbUtils.access();
        Client client = DbUtils.client();

        when(repository.findByExternalId(any())).thenReturn(Optional.of(note));
        when(authHelper.getAuthorizedClientId()).thenReturn(CLIENT_EXTERNAL_ID);
        when(clientService.findByExternalId(any())).thenReturn(client);
        when(accessService.clientHasAccessToNote(any(), any())).thenReturn(Boolean.TRUE);
        when(fileSystemRestService.readFile(any())).thenReturn(IntegrationTestUtils.fileSystemFileResponseDto());
        when(accessService.getAccessOfClientToNote(any(), any())).thenReturn(access);

        NoteResponseDto actual = service.readNote(NOTE_EXTERNAL_ID);

        assertEquals(expected, actual);

        verify(repository).findByExternalId(NOTE_EXTERNAL_ID);
        verify(authHelper).getAuthorizedClientId();
        verify(clientService).findByExternalId(CLIENT_EXTERNAL_ID);
        verify(accessService).clientHasAccessToNote(client, note);
        verify(fileSystemRestService).readFile(NOTE_EXTERNAL_ID);
        verify(accessService).getAccessOfClientToNote(client, note);
    }

    @Test
    void updateNoteSuccess() {
        Note note = DbUtils.note();
        Client client = DbUtils.client();
        Access access = DbUtils.access();

        when(repository.findByExternalId(any())).thenReturn(Optional.of(note));
        when(authHelper.getAuthorizedClientId()).thenReturn(CLIENT_EXTERNAL_ID);
        when(clientService.findByExternalId(any())).thenReturn(client);
        when(accessService.getAccessOfClientToNote(any(), any())).thenReturn(access);

        service.updateNote(NOTE_EXTERNAL_ID, ApiUtils.updateNoteRequestDto());

        verify(repository).findByExternalId(NOTE_EXTERNAL_ID);
        verify(authHelper).getAuthorizedClientId();
        verify(clientService).findByExternalId(CLIENT_EXTERNAL_ID);
        verify(accessService).getAccessOfClientToNote(client, note);
    }

    @Test
    void deleteNoteSuccess() {
        when(repository.findByExternalId(any())).thenReturn(Optional.of(DbUtils.note()));

        service.deleteNote(NOTE_EXTERNAL_ID);

        verify(repository).findByExternalId(NOTE_EXTERNAL_ID);
    }

    @Test
    void getArchiveHistorySuccess() {
        NoteHistoryResponseDto expected = ApiUtils.noteHistoryResponseDto();

        when(fileSystemRestService.getFileArchiveHistory(any())).thenReturn(IntegrationTestUtils.fileSystemArchiveResponseDto());

        NoteHistoryResponseDto actual = service.getNoteArchiveHistory(NOTE_EXTERNAL_ID);

        assertEquals(expected, actual);

        verify(fileSystemRestService).getFileArchiveHistory(NOTE_EXTERNAL_ID);
    }

    @Test
    void getNoteDeletedHistory() {
        DeleteHistoryResponseDto expected = ApiUtils.deleteHistoryResponseDto(NOTE_TITLE, NOTE_CREATED_DATE);

        when(fileSystemRestService.getFileDeleteHistory(any())).thenReturn(IntegrationTestUtils.fileSystemDeleteHistoryResponseDto(NOTE_TITLE, NOTE_CREATED_DATE));

        DeleteHistoryResponseDto actual = service.getNoteDeleteHistory(NOTE_EXTERNAL_ID);

        assertEquals(expected, actual);

        verify(fileSystemRestService).getFileDeleteHistory(NOTE_EXTERNAL_ID);
    }

    @Test
    void getReplacingHistorySuccess() {
        NoteReplacingHistoryResponseDto expected = ApiUtils.noteReplacingHistoryResponseDto();

        when(fileSystemRestService.getFileReplacingHistory(any())).thenReturn(IntegrationTestUtils.fileSystemReplacingHistoryResponseDto());

        NoteReplacingHistoryResponseDto actual = service.getNoteReplacingHistory(NOTE_EXTERNAL_ID);

        assertEquals(expected, actual);

        verify(fileSystemRestService).getFileReplacingHistory(NOTE_EXTERNAL_ID);
    }

    @Test
    void getNoteVersionSuccess() {
        NoteVersionResponseDto expected = ApiUtils.noteVersionResponseDto();

        when(fileSystemRestService.getFileVersion(any())).thenReturn(IntegrationTestUtils.fileSystemFileVersionDto());

        NoteVersionResponseDto actual = service.getNoteVersion(NOTE_EXTERNAL_ID);

        assertEquals(expected, actual);

        verify(fileSystemRestService).getFileVersion(NOTE_EXTERNAL_ID);
    }

    @Test
    void changeAccessSuccess() {
        ChangeAccessModeRequestDto request = ApiUtils.changeAccessModeRequestDto();
        Note note = DbUtils.note();
        Client client = DbUtils.client();

        when(repository.findByExternalId(any())).thenReturn(Optional.of(note));
        when(clientService.findByExternalId(any())).thenReturn(client);

        service.changeAccess(request);

        verify(repository).findByExternalId(request.getNoteExternalId());
        verify(clientService).findByExternalId(request.getClientExternalId());
        verify(accessService).addAccess(note, client, request.getAccessMode());
    }
}
