package notes.project.logic.service.api.impl;

import java.rmi.server.UID;
import java.util.UUID;

import javax.transaction.Transactional;

import lombok.RequiredArgsConstructor;
import notes.project.logic.config.ApplicationProperties;
import notes.project.logic.dto.api.*;
import notes.project.logic.dto.integration.userdatasystem.UserDataSystemAllClientsResponseDto;
import notes.project.logic.utils.mapper.dto.ChangePersonalInfoMappingDto;
import notes.project.logic.dto.integration.filesystem.CreateClusterRequestDto;
import notes.project.logic.dto.integration.filesystem.CreateClusterResponseDto;
import notes.project.logic.dto.integration.filesystem.FileSystemClusterDto;
import notes.project.logic.dto.integration.filesystem.FileSystemDeleteHistoryResponseDto;
import notes.project.logic.dto.integration.userdatasystem.UserDataSystemChangePersonalInfoRequestDto;
import notes.project.logic.dto.integration.userdatasystem.UserDataSystemPersonalInfoDto;
import notes.project.logic.exception.NotFoundException;
import notes.project.logic.model.Client;
import notes.project.logic.repository.ClientRepository;
import notes.project.logic.service.api.ClientService;
import notes.project.logic.service.integration.http.FileSystemRestService;
import notes.project.logic.service.integration.http.UserDataSystemRestService;
import notes.project.logic.utils.AuthHelper;
import notes.project.logic.utils.cache.CacheConfigValue;
import notes.project.logic.utils.mapper.*;
import org.apache.kafka.common.protocol.types.Field;
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
    private final DeleteHistoryResponseMapper deleteHistoryResponseMapper;
    private final ChangePersonalInfoRequestMapper changePersonalInfoRequestMapper;
    private final AllClientsListMapper allClientsListMapper;
    private final ApplicationProperties applicationProperties;

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
        Client client = getClientFromContext();
        FileSystemClusterDto fileSystemResponse = fileSystemRestService.readCluster(client.getClusterExternalId());
        return clusterDtoMapper.to(fileSystemResponse);
    }

    @Override
    @Transactional
    public void deleteCluster() {
        Client client = getClientFromContext();
        fileSystemRestService.deleteCluster(client.getClusterExternalId());
    }

    @Override
    public DeleteHistoryResponseDto getClusterDeleteHistory(UUID clientExternalId) {
        Client client = findByExternalId(clientExternalId);
        FileSystemDeleteHistoryResponseDto fileSystemResponse = fileSystemRestService.getClusterDeleteHistory(client.getClusterExternalId());
        return deleteHistoryResponseMapper.to(fileSystemResponse);
    }

    @Override
    @Transactional
    public PersonalInfoDto changePersonalInfo(ChangePersonalInfoRequestDto request, Boolean createNew) {
        UUID clientExternalId = authHelper.getAuthorizedClientId();
        UserDataSystemChangePersonalInfoRequestDto userDataSystemRequest = changePersonalInfoRequestMapper.to(
            new ChangePersonalInfoMappingDto(
                clientExternalId,
                request
            )
        );
        UserDataSystemPersonalInfoDto userDataSystemResponse = userDataSystemRestService.changePersonalInfo(userDataSystemRequest, createNew);
        return personalInfoMapper.to(userDataSystemResponse);
    }

    @Override
    @Transactional
    public AllClientsResponseDto getAllClients() {
        String systemName = applicationProperties.getSystemName();
        UserDataSystemAllClientsResponseDto userDataSystemResponse = userDataSystemRestService.getAllClientsOfSystem(systemName);
        return allClientsListMapper.to(userDataSystemResponse);
    }

    @Override
    public void renewClientCluster(UUID clusterExternalId) {
        Client client = repository.findByClusterExternalId(clusterExternalId);
        String clusterTitle = CLUSTER_TITLE_TEMPLATE.replace(CLUSTER_TITLE_PLACEHOLDER, client.getExternalId().toString());
        CreateClusterResponseDto createClusterResponse = fileSystemRestService.createCluster(new CreateClusterRequestDto(clusterTitle));
        client.setClusterExternalId(createClusterResponse.getExternalId());
    }

    @Override
    public PersonalInfoDto getPersonalInfoByClusterExternalId(UUID clusterExternalId) {
        Client client = repository.findByClusterExternalId(clusterExternalId);
        return getPersonalInfo(client.getExternalId());
    }

    private Client getClientFromContext() {
        UUID currentClientId = authHelper.getAuthorizedClientId();
        return findByExternalId(currentClientId);
    }
}
