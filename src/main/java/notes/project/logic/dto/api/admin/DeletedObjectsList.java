package notes.project.logic.dto.api.admin;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import notes.project.logic.dto.api.DirectoryInfoDto;
import notes.project.logic.dto.api.NoteDto;

import java.util.List;

@Data
@ApiModel(description = "Список всех удаленных директорий и записок пользователя")
@NoArgsConstructor
@AllArgsConstructor
public class DeletedObjectsList {
	private List<DirectoryInfoDto> directories;

	private List<NoteDto> notes;
}
