package notes.project.logic.service.integration.http.impl;

import java.util.UUID;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import notes.project.logic.dto.integration.userdatasystem.UserDataSystemChangePersonalInfoRequestDto;
import notes.project.logic.dto.integration.userdatasystem.UserDataSystemPersonalInfoDto;
import notes.project.logic.service.integration.http.AbstractRestService;
import notes.project.logic.service.integration.http.UserDataSystemRestService;
import notes.project.logic.service.integration.http.client.UserDataSystemFeignClient;
import notes.project.logic.utils.cache.CacheConfigValue;
import org.springframework.cache.annotation.CacheEvict;
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

    @Override
    @CacheEvict(value = CacheConfigValue.PERSONAL_INFO, key = CacheConfigValue.REQUEST_CLIENT_EXTERNAL_ID)
    public UserDataSystemPersonalInfoDto changePersonalInfo(UserDataSystemChangePersonalInfoRequestDto request) {
        ResponseEntity<UserDataSystemPersonalInfoDto> response;
        try {
            response = client.changePersonalInfo(request);
        } catch(FeignException exception) {
            throw handleFeignException(exception);
        }
        checkResponse(response);
        return response.getBody();
    }
}
