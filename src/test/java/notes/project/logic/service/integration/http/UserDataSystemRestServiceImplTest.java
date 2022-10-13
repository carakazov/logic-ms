package notes.project.logic.service.integration.http;

import feign.FeignException;
import notes.project.logic.dto.integration.userdatasystem.UserDataSystemAdditionalInfoDto;
import notes.project.logic.dto.integration.userdatasystem.UserDataSystemPersonalInfoDto;
import notes.project.logic.exception.IntegrationException;
import notes.project.logic.exception.LogicMsException;
import notes.project.logic.service.integration.http.client.UserDataSystemFeignClient;
import notes.project.logic.service.integration.http.impl.UserDataSystemRestServiceImpl;
import notes.project.logic.utils.IntegrationTestUtils;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static notes.project.logic.utils.TestDataConstants.CLIENT_EXTERNAL_ID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserDataSystemRestServiceImplTest {
    @Mock
    private UserDataSystemFeignClient client;

    private UserDataSystemRestService service;

    @BeforeEach
    void init() {
        service = new UserDataSystemRestServiceImpl(client);
    }

    @Test
    void getPersonalInfoSuccess() {
        UserDataSystemPersonalInfoDto expected = IntegrationTestUtils.userDataSystemPersonalInfoDto();

        when(client.getPersonalInfo(any())).thenReturn(ResponseEntity.ok(expected));

        UserDataSystemPersonalInfoDto actual = service.getPersonalInfo(CLIENT_EXTERNAL_ID);

        assertEquals(expected, actual);

        verify(client).getPersonalInfo(CLIENT_EXTERNAL_ID);
    }

    @Test
    void getPersonalInfoWhenFeignException() {
        when(client.getPersonalInfo(any())).thenThrow(FeignException.class);

        assertThrows(
            IntegrationException.class,
            () -> service.getPersonalInfo(CLIENT_EXTERNAL_ID)
        );

        verify(client).getPersonalInfo(CLIENT_EXTERNAL_ID);
    }

    @Test
    void getPersonalInfoWhenBadResponse() {
        UserDataSystemPersonalInfoDto expected = IntegrationTestUtils.userDataSystemPersonalInfoDto();
        when(client.getPersonalInfo(any())).thenReturn(ResponseEntity.badRequest().body(expected));

        assertThrows(
            LogicMsException.class,
            () -> service.getPersonalInfo(CLIENT_EXTERNAL_ID)
        );

        verify(client).getPersonalInfo(CLIENT_EXTERNAL_ID);
    }
}
