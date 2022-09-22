package notes.project.logic.service;

import notes.project.logic.model.Client;
import notes.project.logic.repository.ClientRepository;
import notes.project.logic.service.api.ClientService;
import notes.project.logic.service.api.impl.ClientServiceImpl;
import notes.project.logic.service.integration.http.FileSystemRestService;
import notes.project.logic.utils.ApiUtils;
import notes.project.logic.utils.DbUtils;
import notes.project.logic.utils.mapper.CreateClientMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static notes.project.logic.utils.TestDataConstants.CLIENT_EXTERNAL_ID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClientServiceImplTest {
    @Mock
    private ClientRepository repository;
    @Mock
    private FileSystemRestService fileSystemRestService;

    private ClientService service;

    @BeforeEach
    void init() {
        service = new ClientServiceImpl(
            repository,
            Mappers.getMapper(CreateClientMapper.class),
            fileSystemRestService
        );
    }

    @Test
    void saveSuccess() {
        Client expected = DbUtils.client();

        when(fileSystemRestService.createCluster(any())).thenReturn(ApiUtils.createClusterResponseDto());
        when(repository.save(any())).thenReturn(expected);

        Client actual = service.save(CLIENT_EXTERNAL_ID);


        assertEquals(expected, actual);

        verify(fileSystemRestService).createCluster(ApiUtils.createClusterRequestDto());
        verify(repository).save(expected.setId(null));
    }
}
