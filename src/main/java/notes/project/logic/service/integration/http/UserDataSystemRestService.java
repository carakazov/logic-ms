package notes.project.logic.service.integration.http;

import java.util.UUID;

import notes.project.logic.dto.integration.userdatasystem.UserDataSystemPersonalInfoDto;

public interface UserDataSystemRestService {
    UserDataSystemPersonalInfoDto getPersonalInfo(UUID externalId);
}
