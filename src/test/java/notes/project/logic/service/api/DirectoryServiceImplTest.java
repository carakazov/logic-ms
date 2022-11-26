package notes.project.logic.service.api;

import java.util.Optional;

import notes.project.logic.dto.api.CreateDirectoryResponseDto;
import notes.project.logic.dto.api.DeleteHistoryResponseDto;
import notes.project.logic.dto.api.DirectoryDto;
import notes.project.logic.dto.integration.filesystem.FileSystemCreateDirectoryResponseDto;
import notes.project.logic.dto.integration.filesystem.FileSystemDeleteHistoryResponseDto;
import notes.project.logic.model.Client;
import notes.project.logic.model.Directory;
import notes.project.logic.repository.DirectoryRepository;
import notes.project.logic.service.api.impl.DirectoryServiceImpl;
import notes.project.logic.service.integration.http.FileSystemRestService;
import notes.project.logic.utils.*;
import notes.project.logic.utils.mapper.CreateDirectoryMapper;
import notes.project.logic.utils.mapper.DeleteHistoryResponseMapper;
import notes.project.logic.utils.mapper.DirectoryDtoMapper;
import notes.project.logic.validation.Validator;
import notes.project.logic.validation.dto.DeleteDirectoryValidationDto;
import notes.project.logic.validation.dto.OwningValidationDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static notes.project.logic.utils.TestDataConstants.*;

@ExtendWith(MockitoExtension.class)
class DirectoryServiceImplTest {
    @Mock
    private DirectoryRepository repository;
    @Mock
    private FileSystemRestService fileSystemRestService;
    @Mock
    private ClientService clientService;
    @Mock
    private AuthHelper authHelper;
    @Mock
    private Validator<OwningValidationDto> owningValidator;

    private DirectoryService service;

    @BeforeEach
    void init() {
        service = new DirectoryServiceImpl(
            repository,
            fileSystemRestService,
            Mappers.getMapper(CreateDirectoryMapper.class),
            clientService,
            authHelper,
            TestUtils.getComplexMapper(DirectoryDtoMapper.class),
            owningValidator,
            TestUtils.getComplexMapper(DeleteHistoryResponseMapper.class)
        );
    }

    @Test
    void createDirectory() {
        FileSystemCreateDirectoryResponseDto fileSystemResponse = IntegrationTestUtils.fileSystemCreateDirectoryResponseDto();
        CreateDirectoryResponseDto expected = ApiUtils.createDirectoryResponseDto();
        Client client = DbUtils.client();
        Directory directory = DbUtils.directory();

        when(fileSystemRestService.createDirectory(any())).thenReturn(fileSystemResponse);
        when(authHelper.getAuthorizedClientId()).thenReturn(CLIENT_EXTERNAL_ID);
        when(clientService.findByExternalId(any())).thenReturn(client);
        when(repository.save(any())).thenReturn(directory);

        CreateDirectoryResponseDto actual = service.createDirectory(ApiUtils.createDirectoryRequestDto());

        assertEquals(expected, actual);

        verify(fileSystemRestService).createDirectory(IntegrationTestUtils.fileSystemCreateDirectoryRequestDto());
        verify(authHelper).getAuthorizedClientId();
        verify(clientService).findByExternalId(CLIENT_EXTERNAL_ID);
        verify(repository).save(directory.setId(null));
    }

    @Test
    void deleteDirectorySuccess() {
        when(repository.findByExternalId(any())).thenReturn(Optional.of(DbUtils.directory()));

        service.deleteDirectory(DIRECTORY_EXTERNAL_ID);

        verify(repository).findByExternalId(DIRECTORY_EXTERNAL_ID);
        verify(fileSystemRestService).deleteDirectory(DIRECTORY_EXTERNAL_ID, CLUSTER_EXTERNAL_ID);
    }

    @Test
    void readDirectorySuccess() {
        DirectoryDto expected = ApiUtils.directoryDto();

        when(repository.findByExternalId(any())).thenReturn(Optional.of(DbUtils.directory()));
        when(fileSystemRestService.readDirectory(any())).thenReturn(IntegrationTestUtils.fileSystemDirectoryDto());

        DirectoryDto actual = service.readDirectory(DIRECTORY_EXTERNAL_ID);

        assertEquals(expected, actual);

        verify(repository).findByExternalId(DIRECTORY_EXTERNAL_ID);
        verify(fileSystemRestService).readDirectory(DIRECTORY_EXTERNAL_ID);
    }

    @Test
    void getDirectoryDeleteHistory() {
        FileSystemDeleteHistoryResponseDto fileSystemResponse = IntegrationTestUtils.fileSystemDeleteHistoryResponseDto(
            DIRECTORY_NAME,
            DIRECTORY_CREATION_TIME
        );

        when(repository.findByExternalId(any())).thenReturn(Optional.of(DbUtils.directory()));
        when(fileSystemRestService.getDirectoryDeleteHistory(any())).thenReturn(fileSystemResponse);

        DeleteHistoryResponseDto expected = ApiUtils.deleteHistoryResponseDto(DIRECTORY_NAME, DIRECTORY_CREATION_TIME);

        DeleteHistoryResponseDto actual = service.getDirectoryDeleteHistory(DIRECTORY_EXTERNAL_ID);

        assertEquals(expected, actual);

        verify(repository).findByExternalId(DIRECTORY_EXTERNAL_ID);
        verify(fileSystemRestService).getDirectoryDeleteHistory(DIRECTORY_EXTERNAL_ID);
    }

}
