package notes.project.logic.service.api;

import java.util.Optional;

import notes.project.logic.dto.api.ClusterDto;
import notes.project.logic.dto.api.PersonalInfoDto;
import notes.project.logic.exception.NotFoundException;
import notes.project.logic.model.Client;
import notes.project.logic.repository.ClientRepository;
import notes.project.logic.service.api.ClientService;
import notes.project.logic.service.api.impl.ClientServiceImpl;
import notes.project.logic.service.integration.http.FileSystemRestService;
import notes.project.logic.service.integration.http.UserDataSystemRestService;
import notes.project.logic.utils.*;
import notes.project.logic.utils.mapper.ClusterDtoMapper;
import notes.project.logic.utils.mapper.CreateClientMapper;
import notes.project.logic.utils.mapper.PersonalInfoMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static notes.project.logic.utils.TestDataConstants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClientServiceImplTest {
    @Mock
    private ClientRepository repository;
    @Mock
    private FileSystemRestService fileSystemRestService;
    @Mock
    private UserDataSystemRestService userDataSystemRestService;
    @Mock
    private AuthHelper authHelper;
    private ClientService service;

    @BeforeEach
    void init() {
        service = new ClientServiceImpl(
            repository,
            Mappers.getMapper(CreateClientMapper.class),
            fileSystemRestService,
            userDataSystemRestService,
            TestUtils.getComplexMapper(PersonalInfoMapper.class),
            authHelper,
            TestUtils.getComplexMapper(ClusterDtoMapper.class)
        );
    }

    @Test
    void saveSuccess() {
        Client expected = DbUtils.client();

        when(fileSystemRestService.createCluster(any())).thenReturn(IntegrationTestUtils.createClusterResponseDto());
        when(repository.save(any())).thenReturn(expected);

        Client actual = service.save(CLIENT_EXTERNAL_ID);


        assertEquals(expected, actual);

        verify(fileSystemRestService).createCluster(IntegrationTestUtils.createClusterRequestDto());
        verify(repository).save(expected.setId(null));
    }

    @Test
    void findByExternalIdSuccess() {
        Client expected = DbUtils.client();

        when(repository.findByExternalId(any())).thenReturn(Optional.of(expected));

        Client actual = service.findByExternalId(expected.getExternalId());

        assertEquals(expected, actual);

        verify(repository).findByExternalId(expected.getExternalId());
    }

    @Test
    void findByExternalIdWhenNotFound() {
        when(repository.findByExternalId(any())).thenReturn(Optional.empty());

        assertThrows(
            NotFoundException.class,
            () -> service.findByExternalId(CLIENT_EXTERNAL_ID)
        );

        verify(repository).findByExternalId(CLIENT_EXTERNAL_ID);
    }


    @Test
    void getPersonalInfoSuccess() {
        PersonalInfoDto expected = ApiUtils.personalInfoDto();

        when(repository.findByExternalId(any())).thenReturn(Optional.of(DbUtils.client()));
        when(userDataSystemRestService.getPersonalInfo(any())).thenReturn(IntegrationTestUtils.userDataSystemPersonalInfoDto());

        PersonalInfoDto actual = service.getPersonalInfo(CLIENT_EXTERNAL_ID);

        assertEquals(expected, actual);

        verify(repository).findByExternalId(CLIENT_EXTERNAL_ID);
        verify(userDataSystemRestService).getPersonalInfo(CLIENT_EXTERNAL_ID);
    }

    @Test
    void readClusterSuccess() {
        ClusterDto expected = ApiUtils.clusterDto();

        when(authHelper.getAuthorizedClientId()).thenReturn(CLIENT_EXTERNAL_ID);
        when(repository.findByExternalId(any())).thenReturn(Optional.of(DbUtils.client()));
        when(fileSystemRestService.readCluster(any())).thenReturn(IntegrationTestUtils.fileSystemClusterDto());

        ClusterDto actual = service.readCluster();

        assertEquals(expected, actual);

        verify(authHelper).getAuthorizedClientId();
        verify(repository).findByExternalId(CLIENT_EXTERNAL_ID);
        verify(fileSystemRestService).readCluster(CLUSTER_EXTERNAL_ID);
    }

    @Test
    void deleteClusterSuccess() {
        when(authHelper.getAuthorizedClientId()).thenReturn(CLIENT_EXTERNAL_ID);
        when(repository.findByExternalId(any())).thenReturn(Optional.of(DbUtils.client()));

        service.deleteCluster();

        verify(authHelper).getAuthorizedClientId();
        verify(repository).findByExternalId(CLIENT_EXTERNAL_ID);
    }
}
