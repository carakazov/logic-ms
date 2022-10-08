package notes.project.logic.service.integration.http.impl;

import java.util.UUID;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import notes.project.logic.dto.integration.userdatasystem.UserDataSystemPersonalInfoDto;
import notes.project.logic.service.integration.http.AbstractRestService;
import notes.project.logic.service.integration.http.UserDataSystemRestService;
import notes.project.logic.service.integration.http.client.UserDataSystemFeignClient;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDataSystemRestServiceImpl extends AbstractRestService implements UserDataSystemRestService {
    private final UserDataSystemFeignClient client;

    @Override
    public UserDataSystemPersonalInfoDto getPersonalInfo(UUID externalId) {
        ResponseEntity<UserDataSystemPersonalInfoDto> response;
        try {
            response = client.getPersonalInfo(externalId);
        } catch(FeignException exception) {
            throw handleFeignException(exception);
        }
        checkResponse(response);
        return response.getBody();
    }
}
