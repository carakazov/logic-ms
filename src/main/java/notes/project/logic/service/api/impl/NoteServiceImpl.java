package notes.project.logic.service.api.impl;

import java.util.UUID;
import javax.transaction.Transactional;

import lombok.RequiredArgsConstructor;
import notes.project.logic.dto.api.*;
import notes.project.logic.dto.integration.filesystem.*;
import notes.project.logic.exception.NotFoundException;
import notes.project.logic.model.*;
import notes.project.logic.repository.NoteRepository;
import notes.project.logic.service.api.AccessService;
import notes.project.logic.service.api.ClientService;
import notes.project.logic.service.api.DirectoryService;
import notes.project.logic.service.api.NoteService;
import notes.project.logic.service.integration.http.FileSystemRestService;
import notes.project.logic.utils.AuthHelper;
import notes.project.logic.utils.mapper.*;
import notes.project.logic.validation.Validator;
import notes.project.logic.validation.dto.*;
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
    private final Validator<UpdateNoteValidationDto> updateNoteValidator;
    private final UpdateNoteMapper updateNoteMapper;
    private final Validator<CreateNoteValidationDto> createNoteValidator;
    private final Validator<DeleteNoteValidationDto> deleteNoteValidator;
    private final NoteHistoryResponseMapper noteHistoryResponseMapper;
    private final DeleteHistoryResponseMapper deleteHistoryResponseMapper;
    private final ReplacingHistoryResponseMapper replacingHistoryResponseMapper;
    private final NoteVersionMapper noteVersionMapper;
    private final Validator<MoveNoteValidationDto> moveNoteValidator;

    @Override
    @Transactional
    public CreateNoteResponseDto createNote(CreateNoteRequestDto request) {
        UUID clientId = authHelper.getAuthorizedClientId();
        Directory directory = directoryService.findDirectoryByExternalId(request.getDirectoryExternalId());
        createNoteValidator.validate(new CreateNoteValidationDto(directory, clientId));
        FileSystemCreateFileResponseDto fileSystemResponse = fileSystemRestService.createFile(createFileMapper.toRequest(request));
        CreateNoteResponseDto response = createFileMapper.toResponse(fileSystemResponse);
        Client client = clientService.findByExternalId(clientId);
        Note note = createFileMapper.toNote(response, client, directory);
        repository.save(note);
        accessService.addAccess(note, client, AccessMode.READ_WRITE);
        return response;
    }

    @Override
    @Transactional
    public MoveNoteResponseDto moveNote(MoveNoteRequestDto request) {
        Directory newDirectory = directoryService.findDirectoryByExternalId(request.getNewDirectoryExternalId());
        moveNoteValidator.validate(new MoveNoteValidationDto(authHelper.getAuthorizedClientId(), newDirectory));
        FileSystemChangeFileDirectoryResponseDto fileSystemResponse = fileSystemRestService.changeFileDirectory(changeDirectoryMapper.toRequest(request));
        MoveNoteResponseDto response = changeDirectoryMapper.toResponse(fileSystemResponse);
        Note note = findByExternalId(request.getCreatedFileExternalId());
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

    @Override
    public void updateNote(UUID externalId, UpdateNoteRequestDto request) {
        Note note = findByExternalId(externalId);
        Client client = clientService.findByExternalId(authHelper.getAuthorizedClientId());
        Access access = accessService.getAccessOfClientToNote(client, note);
        updateNoteValidator.validate(new UpdateNoteValidationDto(access));
        fileSystemRestService.updateFile(externalId, updateNoteMapper.to(request));
    }

    @Override
    @Transactional
    public void deleteNote(UUID externalId) {
        Note note = findByExternalId(externalId);
        deleteNoteValidator.validate(new DeleteNoteValidationDto(
            note,
            authHelper.getAuthorizedClientId()
        ));

        fileSystemRestService.deleteFile(externalId);

        note.setDeleted(Boolean.TRUE);
    }

    @Override
    public NoteHistoryResponseDto getNoteArchiveHistory(UUID externalId) {
        FileSystemArchiveResponseDto fileSystemResponse = fileSystemRestService.getFileArchiveHistory(externalId);
        return noteHistoryResponseMapper.to(fileSystemResponse);
    }

    @Override
    public DeleteHistoryResponseDto getNoteDeleteHistory(UUID externalId) {
        FileSystemDeleteHistoryResponseDto fileSystemResponse = fileSystemRestService.getFileDeleteHistory(externalId);
        return deleteHistoryResponseMapper.to(fileSystemResponse);
    }

    @Override
    public NoteReplacingHistoryResponseDto getNoteReplacingHistory(UUID externalId) {
        FileSystemReplacingHistoryResponseDto fileSystemResponse = fileSystemRestService.getFileReplacingHistory(externalId);
        return replacingHistoryResponseMapper.to(fileSystemResponse);
    }

    @Override
    public NoteVersionResponseDto getNoteVersion(UUID externalId) {
        FileSystemFileVersionDto fileSystemResponse = fileSystemRestService.getFileVersion(externalId);
        return noteVersionMapper.to(fileSystemResponse);
    }
}
