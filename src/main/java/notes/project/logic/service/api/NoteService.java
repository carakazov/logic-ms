package notes.project.logic.service.api;

import java.util.UUID;

import notes.project.logic.dto.api.CreateNoteRequestDto;
import notes.project.logic.dto.api.CreateNoteResponseDto;
import notes.project.logic.dto.api.MoveNoteRequestDto;
import notes.project.logic.dto.api.MoveNoteResponseDto;
import notes.project.logic.model.Note;

public interface NoteService {
    CreateNoteResponseDto createNote(CreateNoteRequestDto request);

    MoveNoteResponseDto moveNote(MoveNoteRequestDto request);

    Note findByExternalId(UUID externalId);
}
