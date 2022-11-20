package notes.project.logic.service.api;

import java.util.UUID;

import notes.project.logic.dto.api.*;
import notes.project.logic.utils.mapper.dto.ChangePersonalInfoMappingDto;
import notes.project.logic.model.Client;

public interface ClientService {
    Client save(UUID externalId);

    PersonalInfoDto getPersonalInfo(UUID externalId);

    Client findByExternalId(UUID externalId);

    ClusterDto readCluster();

    void deleteCluster();

    DeleteHistoryResponseDto getClusterDeleteHistory(UUID clientExternalId);

    PersonalInfoDto changePersonalInfo(ChangePersonalInfoRequestDto request);

    AllClientsResponseDto getAllClients();
}
