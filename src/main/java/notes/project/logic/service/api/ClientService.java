package notes.project.logic.service.api;

import java.util.UUID;

import notes.project.logic.dto.api.PersonalInfoDto;
import notes.project.logic.model.Client;

public interface ClientService {
    Client save(UUID externalId);

    PersonalInfoDto getPersonalInfo(UUID externalId);

    Client findByExternalId(UUID externalId);
}
