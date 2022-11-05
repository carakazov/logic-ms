package notes.project.logic.service.api;

import java.util.UUID;

import notes.project.logic.dto.api.CreateDirectoryRequestDto;
import notes.project.logic.dto.api.CreateDirectoryResponseDto;
import notes.project.logic.dto.api.DeleteHistoryResponseDto;
import notes.project.logic.dto.api.DirectoryDto;
import notes.project.logic.model.Directory;

public interface DirectoryService {
    CreateDirectoryResponseDto createDirectory(CreateDirectoryRequestDto request);

    Directory findDirectoryByExternalId(UUID externalId);

    void deleteDirectory(UUID externalId);

    DirectoryDto readDirectory(UUID externalId);

    DeleteHistoryResponseDto getDirectoryDeleteHistory(UUID externalId);
}
