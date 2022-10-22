package notes.project.logic.validation.dto;

import java.awt.image.DirectColorModel;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import notes.project.logic.model.Directory;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class CreateNoteValidationDto {
    private Directory directory;
    private UUID clientExternalId;
}
