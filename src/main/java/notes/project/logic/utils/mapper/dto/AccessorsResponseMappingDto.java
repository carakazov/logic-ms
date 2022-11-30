package notes.project.logic.utils.mapper.dto;

import java.util.List;

import liquibase.pro.packaged.A;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import notes.project.logic.model.Note;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccessorsResponseMappingDto {
    private Note note;
    private List<AccessorMappingDto> accessors;
}
