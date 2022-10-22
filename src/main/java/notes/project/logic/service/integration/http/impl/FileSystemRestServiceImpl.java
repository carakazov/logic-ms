package notes.project.logic.service.integration.http.impl;

import java.util.UUID;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import notes.project.logic.dto.integration.filesystem.*;
import notes.project.logic.service.integration.http.AbstractRestService;
import notes.project.logic.service.integration.http.FileSystemRestService;
import notes.project.logic.service.integration.http.client.FileSystemFeignClient;
import notes.project.logic.utils.cache.CacheConfigValue;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FileSystemRestServiceImpl extends AbstractRestService implements FileSystemRestService {
    private final FileSystemFeignClient client;

    @Override
    public CreateClusterResponseDto createCluster(CreateClusterRequestDto request) {
        ResponseEntity<CreateClusterResponseDto> response;
        try {
            response = client.createCluster(request);
        } catch(FeignException exception) {
            throw handleFeignException(exception);
        }
        checkResponse(response);
        return response.getBody();
    }

    @Override
    public FileSystemCreateDirectoryResponseDto createDirectory(FileSystemCreateDirectoryRequestDto request) {
        ResponseEntity<FileSystemCreateDirectoryResponseDto> response;
        try {
            response = client.createDirectory(request);
        } catch(FeignException exception) {
            throw handleFeignException(exception);
        }
        checkResponse(response);
        return response.getBody();
    }

    @Override
    public FileSystemCreateFileResponseDto createFile(FileSystemCreateFileRequestDto request) {
        ResponseEntity<FileSystemCreateFileResponseDto> response;
        try {
            response = client.createFile(request);
        } catch(FeignException exception) {
            throw handleFeignException(exception);
        }
        checkResponse(response);
        return response.getBody();
    }

    @Override
    public FileSystemChangeFileDirectoryResponseDto changeFileDirectory(FileSystemChangeFileDirectoryRequestDto request) {
        ResponseEntity<FileSystemChangeFileDirectoryResponseDto> response;
        try {
            response = client.changeFileDirectory(request);
        } catch(FeignException exception) {
            throw handleFeignException(exception);
        }
        checkResponse(response);
        return response.getBody();
    }

    @Override
    @Cacheable(value = CacheConfigValue.NOTE_LIST, key = CacheConfigValue.EXTERNAL_ID)
    public FileSystemFileResponseDto readFile(UUID externalId) {
        ResponseEntity<FileSystemFileResponseDto> response;
        try {
            response = client.readFile(externalId);
        } catch(FeignException exception) {
            throw handleFeignException(exception);
        }
        checkResponse(response);
        return response.getBody();
    }

    @Override
    @CacheEvict(value = CacheConfigValue.NOTE_LIST, key = CacheConfigValue.EXTERNAL_ID)
    public void updateFile(UUID externalId, FileSystemUpdateFileRequestDto request) {
        ResponseEntity<Void> response;
        try {
            response = client.updateFile(externalId, request);
        } catch(FeignException exception) {
            throw handleFeignException(exception);
        }
        checkResponse(response);
    }

    @Override
    @CacheEvict(value = CacheConfigValue.NOTE_LIST, key = CacheConfigValue.EXTERNAL_ID)
    public void deleteFile(UUID externalId) {
        ResponseEntity<Void> response;
        try {
            response = client.deleteFile(externalId);
        } catch(FeignException exception) {
            throw handleFeignException(exception);
        }
        checkResponse(response);
    }
}
