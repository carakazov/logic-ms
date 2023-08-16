package notes.project.logic.service.api;

import java.util.List;
import java.util.UUID;

import notes.project.logic.dto.api.*;
import notes.project.logic.model.Note;

public interface NoteService {
    CreateNoteResponseDto createNote(CreateNoteRequestDto request);

    MoveNoteResponseDto moveNote(MoveNoteRequestDto request);

    Note findByExternalId(UUID externalId);

    NoteResponseDto readNote(UUID externalId);

    void updateNote(UUID externalId, UpdateNoteRequestDto request);

    void deleteNote(UUID externalId);

    NoteHistoryResponseDto getNoteArchiveHistory(UUID externalId);

    DeleteHistoryResponseDto getNoteDeleteHistory(UUID externalId);

    NoteReplacingHistoryResponseDto getNoteReplacingHistory(UUID externalId);

    NoteVersionResponseDto getNoteVersion(UUID externalId);

    void changeAccess(ChangeAccessModeRequestDto request);

    AccessorsListResponseDto getAllNoteAccessors(UUID noteExternalId);

    void denyAccess(UUID externalId, List<UUID> clientIds);

    AccessedNotesResponseDto getMyAccessedNotes();
}
