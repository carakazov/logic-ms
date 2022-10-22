package notes.project.logic.service.api;

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
}
