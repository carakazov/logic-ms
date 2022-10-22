package notes.project.logic.validation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import notes.project.logic.model.Access;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class UpdateNoteValidationDto {
    private Access access;
}
