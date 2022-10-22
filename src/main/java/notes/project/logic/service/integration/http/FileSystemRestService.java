package notes.project.logic.service.integration.http;

import java.util.UUID;

import notes.project.logic.dto.integration.filesystem.*;

public interface FileSystemRestService {
    CreateClusterResponseDto createCluster(CreateClusterRequestDto request);

    FileSystemCreateDirectoryResponseDto createDirectory(FileSystemCreateDirectoryRequestDto request);

    FileSystemCreateFileResponseDto createFile(FileSystemCreateFileRequestDto request);

    FileSystemChangeFileDirectoryResponseDto changeFileDirectory(FileSystemChangeFileDirectoryRequestDto request);

    FileSystemFileResponseDto readFile(UUID externalId);

    void updateFile(UUID externalId, FileSystemUpdateFileRequestDto request);
}
