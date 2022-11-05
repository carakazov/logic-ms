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

    void deleteFile(UUID externalId);

    FileSystemArchiveResponseDto getFileArchiveHistory(UUID externalId);

    FileSystemDeleteHistoryResponseDto getFileDeleteHistory(UUID externalId);

    FileSystemReplacingHistoryResponseDto getFileReplacingHistory(UUID externalId);

    FileSystemFileVersionDto getFileVersion(UUID externalId);

    void deleteDirectory(UUID externalId);

    FileSystemDirectoryDto readDirectory(UUID externalId);

    FileSystemDeleteHistoryResponseDto getDirectoryDeleteHistory(UUID externalId);

    FileSystemClusterDto readCluster(UUID externalId);

    void deleteCluster(UUID deleteCluster);
}
