package notes.project.logic.dto.api;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class AccessedNotesResponseDto {
    private List<NoteResponseDto> notes;
}
