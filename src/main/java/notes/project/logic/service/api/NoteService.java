package notes.project.logic.service.api;

import notes.project.logic.dto.api.CreateNoteRequestDto;
import notes.project.logic.dto.api.CreateNoteResponseDto;

public interface NoteService {
    CreateNoteResponseDto createNote(CreateNoteRequestDto request);
}
