package notes.project.logic.service.api.impl;

import java.util.UUID;

import javax.transaction.Transactional;

import lombok.RequiredArgsConstructor;
import notes.project.logic.dto.api.ClusterDto;
import notes.project.logic.dto.api.PersonalInfoDto;
import notes.project.logic.dto.integration.filesystem.CreateClusterRequestDto;
import notes.project.logic.dto.integration.filesystem.CreateClusterResponseDto;
import notes.project.logic.dto.integration.filesystem.FileSystemClusterDto;
import notes.project.logic.dto.integration.userdatasystem.UserDataSystemPersonalInfoDto;
import notes.project.logic.exception.NotFoundException;
import notes.project.logic.model.Client;
import notes.project.logic.repository.ClientRepository;
import notes.project.logic.service.api.ClientService;
import notes.project.logic.service.integration.http.FileSystemRestService;
import notes.project.logic.service.integration.http.UserDataSystemRestService;
import notes.project.logic.utils.AuthHelper;
import notes.project.logic.utils.cache.CacheConfigValue;
import notes.project.logic.utils.mapper.ClusterDtoMapper;
import notes.project.logic.utils.mapper.CreateClientMapper;
import notes.project.logic.utils.mapper.PersonalInfoMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {
    private static final String CLUSTER_TITLE_PLACEHOLDER = "{clientExternalId}";
    private static final String CLUSTER_TITLE_TEMPLATE = "{clientExternalId}-cluster";

    private final ClientRepository repository;
    private final CreateClientMapper createClientMapper;
    private final FileSystemRestService fileSystemRestService;
    private final UserDataSystemRestService userDataSystemRestService;
    private final PersonalInfoMapper personalInfoMapper;
    private final AuthHelper authHelper;
    private final ClusterDtoMapper clusterDtoMapper;

    @Override
    @Transactional
    public Client save(UUID externalId) {
        String clusterTitle = CLUSTER_TITLE_TEMPLATE.replace(CLUSTER_TITLE_PLACEHOLDER, externalId.toString());
        CreateClusterResponseDto createClusterResponse = fileSystemRestService.createCluster(new CreateClusterRequestDto(clusterTitle));
        Client client = createClientMapper.to(externalId, createClusterResponse.getExternalId());
        return repository.save(client);
    }

    @Override
    @Cacheable(value = CacheConfigValue.PERSONAL_INFO, key = CacheConfigValue.EXTERNAL_ID)
    public PersonalInfoDto getPersonalInfo(UUID externalId) {
        Client client = findByExternalId(externalId);
        UserDataSystemPersonalInfoDto personalInfo = userDataSystemRestService.getPersonalInfo(client.getExternalId());
        return personalInfoMapper.to(personalInfo);
    }

    @Override
    public Client findByExternalId(UUID externalId) {
        return repository.findByExternalId(externalId).orElseThrow(
            () -> new NotFoundException("Client with id " + externalId + " not found")
        );
    }

    @Override
    @Transactional
    public ClusterDto readCluster() {
        UUID currentClientId = authHelper.getAuthorizedClientId();
        Client client = findByExternalId(currentClientId);
        FileSystemClusterDto fileSystemResponse = fileSystemRestService.readCluster(client.getClusterExternalId());
        return clusterDtoMapper.to(fileSystemResponse);
    }
}
