package notes.project.logic.service.api.impl;

import javax.transaction.Transactional;

import lombok.RequiredArgsConstructor;
import notes.project.logic.dto.api.CreateDirectoryRequestDto;
import notes.project.logic.dto.api.CreateDirectoryResponseDto;
import notes.project.logic.dto.integration.filesystem.FileSystemCreateDirectoryRequestDto;
import notes.project.logic.dto.integration.filesystem.FileSystemCreateDirectoryResponseDto;
import notes.project.logic.model.Client;
import notes.project.logic.model.Directory;
import notes.project.logic.repository.DirectoryRepository;
import notes.project.logic.service.api.ClientService;
import notes.project.logic.service.api.DirectoryService;
import notes.project.logic.service.integration.http.FileSystemRestService;
import notes.project.logic.utils.AuthHelper;
import notes.project.logic.utils.mapper.CreateDirectoryMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DirectoryServiceImpl implements DirectoryService {
    private final DirectoryRepository repository;
    private final FileSystemRestService fileSystemRestService;
    private final CreateDirectoryMapper createDirectoryMapper;
    private final ClientService clientService;
    private final AuthHelper authHelper;

    @Override
    @Transactional
    public CreateDirectoryResponseDto createDirectory(CreateDirectoryRequestDto request) {
        FileSystemCreateDirectoryRequestDto fileSystemRequest = createDirectoryMapper.toRequest(request);
        FileSystemCreateDirectoryResponseDto fileSystemResponse = fileSystemRestService.createDirectory(fileSystemRequest);
        CreateDirectoryResponseDto response = createDirectoryMapper.toResponse(fileSystemResponse);
        Client client = clientService.findByExternalId(authHelper.getAuthorizedClientId());
        Directory directory = createDirectoryMapper.toDirectory(client, response);
        repository.save(directory);
        return response;
    }
}
