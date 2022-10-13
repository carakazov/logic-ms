package notes.project.logic.service.api;

import notes.project.logic.dto.api.CreateDirectoryResponseDto;
import notes.project.logic.dto.integration.filesystem.FileSystemCreateDirectoryResponseDto;
import notes.project.logic.model.Client;
import notes.project.logic.model.Directory;
import notes.project.logic.repository.DirectoryRepository;
import notes.project.logic.service.api.impl.DirectoryServiceImpl;
import notes.project.logic.service.integration.http.FileSystemRestService;
import notes.project.logic.utils.ApiUtils;
import notes.project.logic.utils.AuthHelper;
import notes.project.logic.utils.DbUtils;
import notes.project.logic.utils.IntegrationTestUtils;
import notes.project.logic.utils.mapper.CreateDirectoryMapper;
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

    private DirectoryService service;

    @BeforeEach
    void init() {
        service = new DirectoryServiceImpl(
            repository,
            fileSystemRestService,
            Mappers.getMapper(CreateDirectoryMapper.class),
            clientService,
            authHelper
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
}
