package notes.project.logic.validation.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import notes.project.logic.model.Note;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class ReadNoteValidationDto {
    private Note note;
    private UUID clientExternalId;
    private Boolean hasAccess;
}
