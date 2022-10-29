package notes.project.logic.validation.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import notes.project.logic.model.Directory;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class MoveNoteValidationDto {
    private UUID clientExternalId;
    private Directory directory;
}
