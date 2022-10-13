package notes.project.logic.service.api;

import notes.project.logic.dto.api.CreateDirectoryRequestDto;
import notes.project.logic.dto.api.CreateDirectoryResponseDto;

public interface DirectoryService {
    CreateDirectoryResponseDto createDirectory(CreateDirectoryRequestDto request);
}
