package notes.project.logic.service.api;

import java.util.UUID;

import notes.project.logic.dto.api.*;
import notes.project.logic.utils.mapper.dto.ChangePersonalInfoMappingDto;
import notes.project.logic.model.Client;
import org.apache.kafka.common.protocol.types.Field;

public interface ClientService {
    Client save(UUID externalId);

    PersonalInfoDto getPersonalInfo(UUID externalId);

    Client findByExternalId(UUID externalId);

    ClusterDto readCluster();

    void deleteCluster();

    DeleteHistoryResponseDto getClusterDeleteHistory(UUID clientExternalId);

    PersonalInfoDto changePersonalInfo(ChangePersonalInfoRequestDto request, Boolean createNew);

    AllClientsResponseDto getAllClients();

    void renewClientCluster(UUID clusterExternalId);

    PersonalInfoDto getPersonalInfoByClusterExternalId(UUID clusterExternalId);
}
