package notes.project.logic.service.api.impl;

import java.util.UUID;
import javax.transaction.Transactional;

import lombok.RequiredArgsConstructor;
import notes.project.logic.dto.api.CreateNoteRequestDto;
import notes.project.logic.dto.api.CreateNoteResponseDto;
import notes.project.logic.dto.integration.filesystem.FileSystemCreateFileRequestDto;
import notes.project.logic.dto.integration.filesystem.FileSystemCreateFileResponseDto;
import notes.project.logic.model.Client;
import notes.project.logic.model.Directory;
import notes.project.logic.model.Note;
import notes.project.logic.repository.NoteRepository;
import notes.project.logic.service.api.ClientService;
import notes.project.logic.service.api.DirectoryService;
import notes.project.logic.service.api.NoteService;
import notes.project.logic.service.integration.http.FileSystemRestService;
import notes.project.logic.utils.AuthHelper;
import notes.project.logic.utils.mapper.CreateFileMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {
    private final NoteRepository repository;
    private final ClientService clientService;
    private final DirectoryService directoryService;
    private final AuthHelper authHelper;
    private final CreateFileMapper createFileMapper;
    private final FileSystemRestService fileSystemRestService;

    @Override
    @Transactional
    public CreateNoteResponseDto createNote(CreateNoteRequestDto request) {
        UUID clientId = authHelper.getAuthorizedClientId();
        FileSystemCreateFileResponseDto fileSystemResponse = fileSystemRestService.createFile(createFileMapper.toRequest(request));
        CreateNoteResponseDto response = createFileMapper.toResponse(fileSystemResponse);
        Client client = clientService.findByExternalId(clientId);
        Directory directory = directoryService.findDirectoryByExternalId(request.getDirectoryExternalId());
        Note note = createFileMapper.toNote(response, client, directory);
        repository.save(note);
        return response;
    }
}
