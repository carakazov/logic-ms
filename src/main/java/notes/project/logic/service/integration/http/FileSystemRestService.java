package notes.project.logic.service.integration.http;

import notes.project.logic.dto.integration.filesystem.*;

public interface FileSystemRestService {
    CreateClusterResponseDto createCluster(CreateClusterRequestDto request);

    FileSystemCreateDirectoryResponseDto createDirectory(FileSystemCreateDirectoryRequestDto request);

    FileSystemCreateFileResponseDto createFile(FileSystemCreateFileRequestDto request);
}
