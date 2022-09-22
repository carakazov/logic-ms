package notes.project.logic.service.api.impl;

import java.util.UUID;

import javax.transaction.Transactional;

import lombok.RequiredArgsConstructor;
import notes.project.logic.dto.integration.filesystem.CreateClusterRequestDto;
import notes.project.logic.dto.integration.filesystem.CreateClusterResponseDto;
import notes.project.logic.model.Client;
import notes.project.logic.repository.ClientRepository;
import notes.project.logic.service.api.ClientService;
import notes.project.logic.service.integration.http.FileSystemRestService;
import notes.project.logic.utils.mapper.CreateClientMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {
    private static final String CLUSTER_TITLE_PLACEHOLDER = "{clientExternalId}";
    private static final String CLUSTER_TITLE_TEMPLATE = "{clientExternalId}-cluster";

    private final ClientRepository repository;
    private final CreateClientMapper createClientMapper;
    private final FileSystemRestService fileSystemRestService;

    @Override
    @Transactional
    public Client save(UUID externalId) {
        String clusterTitle = CLUSTER_TITLE_TEMPLATE.replace(CLUSTER_TITLE_PLACEHOLDER, externalId.toString());
        CreateClusterResponseDto createClusterResponse = fileSystemRestService.createCluster(new CreateClusterRequestDto(clusterTitle));
        Client client = createClientMapper.to(externalId, createClusterResponse.getExternalId());
        return repository.save(client);
    }
}
