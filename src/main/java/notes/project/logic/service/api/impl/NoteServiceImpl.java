package notes.project.logic.service.api.impl;

import java.util.UUID;
import javax.transaction.Transactional;

import lombok.RequiredArgsConstructor;
import notes.project.logic.dto.api.*;
import notes.project.logic.dto.integration.filesystem.FileSystemChangeFileDirectoryResponseDto;
import notes.project.logic.dto.integration.filesystem.FileSystemCreateFileResponseDto;
import notes.project.logic.dto.integration.filesystem.FileSystemFileResponseDto;
import notes.project.logic.exception.NotFoundException;
import notes.project.logic.model.AccessMode;
import notes.project.logic.model.Client;
import notes.project.logic.model.Directory;
import notes.project.logic.model.Note;
import notes.project.logic.repository.NoteRepository;
import notes.project.logic.service.api.AccessService;
import notes.project.logic.service.api.ClientService;
import notes.project.logic.service.api.DirectoryService;
import notes.project.logic.service.api.NoteService;
import notes.project.logic.service.integration.http.FileSystemRestService;
import notes.project.logic.utils.AuthHelper;
import notes.project.logic.utils.mapper.ChangeDirectoryMapper;
import notes.project.logic.utils.mapper.CreateFileMapper;
import notes.project.logic.utils.mapper.NoteResponseMapper;
import notes.project.logic.validation.Validator;
import notes.project.logic.validation.dto.ReadNoteValidationDto;
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
    private final ChangeDirectoryMapper changeDirectoryMapper;
    private final NoteResponseMapper noteResponseMapper;
    private final Validator<ReadNoteValidationDto> readNoteValidator;
    private final AccessService accessService;

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
        accessService.addAccess(note, client, AccessMode.READ_WRITE);
        return response;
    }

    @Override
    @Transactional
    public MoveNoteResponseDto moveNote(MoveNoteRequestDto request) {
        FileSystemChangeFileDirectoryResponseDto fileSystemResponse = fileSystemRestService.changeFileDirectory(changeDirectoryMapper.toRequest(request));
        MoveNoteResponseDto response = changeDirectoryMapper.toResponse(fileSystemResponse);
        Note note = findByExternalId(request.getCreatedFileExternalId());
        Directory newDirectory = directoryService.findDirectoryByExternalId(request.getNewDirectoryExternalId());
        note.setDirectory(newDirectory);
        return response;
    }

    @Override
    public Note findByExternalId(UUID externalId) {
        return repository.findByExternalId(externalId).orElseThrow(
            () -> new NotFoundException("Note with ID " + externalId + " not found")
        );
    }

    @Override
    public NoteResponseDto readNote(UUID externalId) {
        Note note = findByExternalId(externalId);
        Client client = clientService.findByExternalId(authHelper.getAuthorizedClientId());
        readNoteValidator.validate(new ReadNoteValidationDto(
            note,
            client.getExternalId(),
            accessService.clientHasAccessToNote(client, note)
        ));
        FileSystemFileResponseDto fileSystemResponse = fileSystemRestService.readFile(externalId);
        return noteResponseMapper.to(fileSystemResponse, accessService.getAccessOfClientToNote(client, note));
    }
}
