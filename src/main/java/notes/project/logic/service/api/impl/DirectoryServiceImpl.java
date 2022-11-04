package notes.project.logic.service.api.impl;

import java.util.UUID;
import javax.transaction.Transactional;

import lombok.RequiredArgsConstructor;
import notes.project.logic.dto.api.CreateDirectoryRequestDto;
import notes.project.logic.dto.api.CreateDirectoryResponseDto;
import notes.project.logic.dto.api.DeleteHistoryResponseDto;
import notes.project.logic.dto.api.DirectoryInfoDto;
import notes.project.logic.dto.integration.filesystem.FileSystemCreateDirectoryRequestDto;
import notes.project.logic.dto.integration.filesystem.FileSystemCreateDirectoryResponseDto;
import notes.project.logic.dto.integration.filesystem.FileSystemDeleteHistoryResponseDto;
import notes.project.logic.dto.integration.filesystem.FileSystemDirectoryDto;
import notes.project.logic.exception.NotFoundException;
import notes.project.logic.model.Client;
import notes.project.logic.model.Directory;
import notes.project.logic.repository.DirectoryRepository;
import notes.project.logic.service.api.ClientService;
import notes.project.logic.service.api.DirectoryService;
import notes.project.logic.service.integration.http.FileSystemRestService;
import notes.project.logic.utils.AuthHelper;
import notes.project.logic.utils.mapper.CreateDirectoryMapper;
import notes.project.logic.utils.mapper.DeleteHistoryResponseMapper;
import notes.project.logic.utils.mapper.DirectoryInfoMapper;
import notes.project.logic.validation.Validator;
import notes.project.logic.validation.dto.DeleteDirectoryValidationDto;
import notes.project.logic.validation.dto.OwningValidationDto;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DirectoryServiceImpl implements DirectoryService {
    private final DirectoryRepository repository;
    private final FileSystemRestService fileSystemRestService;
    private final CreateDirectoryMapper createDirectoryMapper;
    private final ClientService clientService;
    private final AuthHelper authHelper;
    private final Validator<DeleteDirectoryValidationDto> deleteDirectoryValidator;
    private final DirectoryInfoMapper directoryInfoMapper;
    private final Validator<OwningValidationDto> owningValidator;
    private final DeleteHistoryResponseMapper deleteHistoryResponseMapper;

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

    @Override
    @Transactional
    public Directory findDirectoryByExternalId(UUID externalId) {
        return repository.findByExternalId(externalId).orElseThrow(
            () -> new NotFoundException("Directory with id " + externalId + " not found")
        );
    }

    @Override
    public void deleteDirectory(UUID externalId) {
        Directory directory = findDirectoryByExternalId(externalId);
        deleteDirectoryValidator.validate(new DeleteDirectoryValidationDto(authHelper.getAuthorizedClientId(), directory));
        fileSystemRestService.deleteDirectory(directory.getExternalId());
    }

    @Override
    @Transactional
    public DirectoryInfoDto readDirectory(UUID externalId) {
        Directory directory = findDirectoryByExternalId(externalId);
        owningValidator.validate(new OwningValidationDto(authHelper.getAuthorizedClientId(), directory.getClient().getExternalId()));
        FileSystemDirectoryDto fileSystemResponse = fileSystemRestService.readDirectory(externalId);
        return directoryInfoMapper.to(fileSystemResponse);
    }

    @Override
    public DeleteHistoryResponseDto getDirectoryDeleteHistory(UUID externalId) {
        Directory directory = findDirectoryByExternalId(externalId);
        FileSystemDeleteHistoryResponseDto fileSystemResponse = fileSystemRestService.getDirectoryDeleteHistory(directory.getExternalId());
        return deleteHistoryResponseMapper.to(fileSystemResponse);
    }
}
