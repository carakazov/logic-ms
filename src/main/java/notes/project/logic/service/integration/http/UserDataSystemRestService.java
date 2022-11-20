package notes.project.logic.service.integration.http;

import java.util.List;
import java.util.UUID;

import notes.project.logic.dto.integration.userdatasystem.UserDataSystemAllClientsResponseDto;
import notes.project.logic.dto.integration.userdatasystem.UserDataSystemChangePersonalInfoRequestDto;
import notes.project.logic.dto.integration.userdatasystem.UserDataSystemPersonalInfoDto;
import notes.project.logic.utils.cache.CacheConfigValue;
import org.springframework.cache.annotation.CacheEvict;

public interface UserDataSystemRestService {
    UserDataSystemPersonalInfoDto getPersonalInfo(UUID externalId);

    UserDataSystemPersonalInfoDto changePersonalInfo(UserDataSystemChangePersonalInfoRequestDto request);

    UserDataSystemAllClientsResponseDto getAllClientsOfSystem(String systemName);
}
