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
import org.springframework.cache.annotation.Caching;
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
    @CacheEvict(value = CacheConfigValue.REPLACING_HISTORY)
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
    @Caching(evict = {
        @CacheEvict(value = CacheConfigValue.NOTE_LIST, key = CacheConfigValue.EXTERNAL_ID),
        @CacheEvict(value = CacheConfigValue.ARCHIVE_HISTORY_LIST, key = CacheConfigValue.EXTERNAL_ID),
        @CacheEvict(value = CacheConfigValue.FILE_VERSION, key = CacheConfigValue.EXTERNAL_ID)
    })
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
    @Caching(evict = {
        @CacheEvict(value = CacheConfigValue.NOTE_LIST, key = CacheConfigValue.EXTERNAL_ID),
        @CacheEvict(value = CacheConfigValue.NOTE_DELETE_HISTORY, key = CacheConfigValue.EXTERNAL_ID)
    })
    public void deleteFile(UUID externalId) {
        ResponseEntity<Void> response;
        try {
            response = client.deleteFile(externalId);
        } catch(FeignException exception) {
            throw handleFeignException(exception);
        }
        checkResponse(response);
    }

    @Override
    @Cacheable(value = CacheConfigValue.ARCHIVE_HISTORY_LIST, key = CacheConfigValue.EXTERNAL_ID)
    public FileSystemArchiveResponseDto getFileArchiveHistory(UUID externalId) {
        ResponseEntity<FileSystemArchiveResponseDto> response;
        try {
            response = client.getFileArchiveHistory(externalId);
        } catch(FeignException exception) {
            throw handleFeignException(exception);
        }
        checkResponse(response);
        return response.getBody();
    }

    @Override
    @Cacheable(value = CacheConfigValue.NOTE_DELETE_HISTORY, key = CacheConfigValue.EXTERNAL_ID)
    public FileSystemDeleteHistoryResponseDto getFileDeleteHistory(UUID externalId) {
        ResponseEntity<FileSystemDeleteHistoryResponseDto> response;
        try {
            response = client.getFileDeleteHistory(externalId);
        } catch(FeignException exception) {
            throw handleFeignException(exception);
        }
        checkResponse(response);
        return response.getBody();
    }

    @Override
    @Cacheable(value = CacheConfigValue.REPLACING_HISTORY, key = CacheConfigValue.EXTERNAL_ID)
    public FileSystemReplacingHistoryResponseDto getFileReplacingHistory(UUID externalId) {
        ResponseEntity<FileSystemReplacingHistoryResponseDto> response;
        try {
            response = client.getFileReplacingHistory(externalId);
        } catch(FeignException exception) {
            throw handleFeignException(exception);
        }
        checkResponse(response);
        return response.getBody();
    }

    @Override
    @Cacheable(value = CacheConfigValue.FILE_VERSION, key = CacheConfigValue.EXTERNAL_ID)
    public FileSystemFileVersionDto getFileVersion(UUID externalId) {
        ResponseEntity<FileSystemFileVersionDto> response;
        try {
            response = client.getFileVersion(externalId);
        } catch(FeignException exception) {
            throw handleFeignException(exception);
        }
        checkResponse(response);
        return response.getBody();
    }
}
