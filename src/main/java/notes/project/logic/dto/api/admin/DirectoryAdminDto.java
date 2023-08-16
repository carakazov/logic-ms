package notes.project.logic.dto.api.admin;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import notes.project.logic.dto.api.DirectoryInfoDto;
import notes.project.logic.dto.api.NoteDto;

import java.util.List;

@Data
@ApiModel(description = "Запрос директории для админа")
public class DirectoryAdminDto {
	private DirectoryInfoDto directory;
	private List<NoteDto> notes;
}
