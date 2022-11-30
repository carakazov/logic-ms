package notes.project.logic.service.api.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.transaction.Transactional;

import lombok.RequiredArgsConstructor;
import notes.project.logic.dto.api.*;
import notes.project.logic.dto.integration.filesystem.*;
import notes.project.logic.dto.integration.userdatasystem.UserDataSystemPersonalInfoDto;
import notes.project.logic.exception.NotFoundException;
import notes.project.logic.model.*;
import notes.project.logic.repository.NoteRepository;
import notes.project.logic.service.api.AccessService;
import notes.project.logic.service.api.ClientService;
import notes.project.logic.service.api.DirectoryService;
import notes.project.logic.service.api.NoteService;
import notes.project.logic.service.integration.http.FileSystemRestService;
import notes.project.logic.service.integration.http.UserDataSystemRestService;
import notes.project.logic.utils.AuthHelper;
import notes.project.logic.utils.mapper.*;
import notes.project.logic.utils.mapper.dto.AccessorMappingDto;
import notes.project.logic.utils.mapper.dto.AccessorsResponseMappingDto;
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
    private final NoteHistoryResponseMapper noteHistoryResponseMapper;
    private final DeleteHistoryResponseMapper deleteHistoryResponseMapper;
    private final ReplacingHistoryResponseMapper replacingHistoryResponseMapper;
    private final NoteVersionMapper noteVersionMapper;
    private final Validator<MoveNoteValidationDto> moveNoteValidator;
    private final Validator<OwningValidationDto> owningValidator;
    private final UserDataSystemRestService userDataSystemRestService;
    private final AccessorsResponseMapper accessorsResponseMapper;
    private final Validator<List<Boolean>> denyAccessValidator;

    @Override
    @Transactional
    public CreateNoteResponseDto createNote(CreateNoteRequestDto request) {
        UUID clientId = authHelper.getAuthorizedClientId();
        Directory directory = directoryService.findDirectoryByExternalId(request.getDirectoryExternalId());
        owningValidator.validate(new OwningValidationDto(
            clientId,
            directory.getClient().getExternalId()
        ));
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
        owningValidator.validate(new OwningValidationDto(
            authHelper.getAuthorizedClientId(),
            note.getClient().getExternalId()
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

    @Override
    @Transactional
    public void changeAccess(ChangeAccessModeRequestDto request) {
        Note note = findByExternalId(request.getNoteExternalId());
        Client client = clientService.findByExternalId(request.getClientExternalId());
        owningValidator.validate(new OwningValidationDto(
            authHelper.getAuthorizedClientId(),
            note.getClient().getExternalId()
        ));
        accessService.addAccess(note, client, request.getAccessMode());
    }

    @Override
    public AccessorsListResponseDto getAllNoteAccessors(UUID noteExternalId) {
        Note note = findByExternalId(noteExternalId);
        owningValidator.validate(new OwningValidationDto(authHelper.getAuthorizedClientId(), note.getClient().getExternalId()));
        List<Access> accesses = accessService.getAllAccessesToNote(note);
        List<AccessorMappingDto> accessors = new ArrayList<>();
        accesses.forEach(item -> {
            UserDataSystemPersonalInfoDto clientInfo = userDataSystemRestService.getPersonalInfo(item.getClient().getExternalId());
            accessors.add(new AccessorMappingDto(clientInfo, item));
        });
        return accessorsResponseMapper.to(new AccessorsResponseMappingDto(note, accessors));
    }

    @Override
    @Transactional
    public void denyAccess(UUID externalId, List<UUID> clientIds) {
        Note note = findByExternalId(externalId);
        owningValidator.validate(new OwningValidationDto(authHelper.getAuthorizedClientId(), note.getClient().getExternalId()));
        List<Client> clients = clientIds.stream()
            .map(clientService::findByExternalId)
            .collect(Collectors.toList());
        denyAccessValidator.validate(
            clients.stream()
                .map(item -> accessService.clientHasAccessToNote(item, note))
                .collect(Collectors.toList())
        );
        clients.forEach(item -> accessService.denyAccess(note, item));
    }
}
