package notes.project.logic.service.integration.http;

import feign.FeignException;
import notes.project.logic.dto.integration.filesystem.CreateClusterResponseDto;
import notes.project.logic.exception.LogicMsException;
import notes.project.logic.service.integration.http.client.FileSystemFeignClient;
import notes.project.logic.service.integration.http.impl.FileSystemRestServiceImpl;
import notes.project.logic.utils.IntegrationTestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FileSystemRestServiceImplTest {
    @Mock
    private FileSystemFeignClient client;

    private FileSystemRestService service;

    @BeforeEach
    void init() {
        service = new FileSystemRestServiceImpl(client);
    }

    @Test
    void createClusterSuccess() {
        CreateClusterResponseDto expected = IntegrationTestUtils.createClusterResponseDto();

        when(client.createCluster(any())).thenReturn(ResponseEntity.ok(expected));

        CreateClusterResponseDto actual = service.createCluster(IntegrationTestUtils.createClusterRequestDto());

        assertEquals(expected, actual);

        verify(client).createCluster(IntegrationTestUtils.createClusterRequestDto());
    }

    @Test
    void createClusterWhenFeignException() {
        when(client.createCluster(any())).thenThrow(FeignException.class);

        assertThrows(
            LogicMsException.class,
            () -> service.createCluster(IntegrationTestUtils.createClusterRequestDto())
        );

        verify(client).createCluster(IntegrationTestUtils.createClusterRequestDto());
    }

    @Test
    void createClusterWhenBadResponse() {
        when(client.createCluster(any())).thenReturn(ResponseEntity.badRequest().build());

        assertThrows(
            LogicMsException.class,
            () -> service.createCluster(IntegrationTestUtils.createClusterRequestDto())
        );
    }
}
