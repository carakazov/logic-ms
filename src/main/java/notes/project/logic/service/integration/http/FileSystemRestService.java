package notes.project.logic.service.integration.http;

import notes.project.logic.dto.integration.filesystem.CreateClusterRequestDto;
import notes.project.logic.dto.integration.filesystem.CreateClusterResponseDto;

public interface FileSystemRestService {
    CreateClusterResponseDto createCluster(CreateClusterRequestDto request);
}
