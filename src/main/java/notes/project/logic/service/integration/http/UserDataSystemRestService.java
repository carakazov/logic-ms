package notes.project.logic.service.integration.http;

import java.util.UUID;

import notes.project.logic.dto.integration.userdatasystem.UserDataSystemChangePersonalInfoRequestDto;
import notes.project.logic.dto.integration.userdatasystem.UserDataSystemPersonalInfoDto;
import notes.project.logic.utils.cache.CacheConfigValue;
import org.springframework.cache.annotation.CacheEvict;

public interface UserDataSystemRestService {
    UserDataSystemPersonalInfoDto getPersonalInfo(UUID externalId);

    UserDataSystemPersonalInfoDto changePersonalInfo(UserDataSystemChangePersonalInfoRequestDto request);
}
