package notes.project.logic.service.api;

import java.util.Optional;
import javax.persistence.NoResultException;

import io.swagger.annotations.Api;
import notes.project.logic.dto.api.CreateNoteResponseDto;
import notes.project.logic.dto.api.MoveNoteResponseDto;
import notes.project.logic.repository.NoteRepository;
import notes.project.logic.service.api.impl.NoteServiceImpl;
import notes.project.logic.service.integration.http.FileSystemRestService;
import notes.project.logic.utils.ApiUtils;
import notes.project.logic.utils.AuthHelper;
import notes.project.logic.utils.DbUtils;
import notes.project.logic.utils.IntegrationTestUtils;
import notes.project.logic.utils.mapper.ChangeDirectoryMapper;
import notes.project.logic.utils.mapper.CreateFileMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestClassOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
            Mappers.getMapper(ChangeDirectoryMapper.class)
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
}
