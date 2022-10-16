package notes.project.logic.dto.api;

import java.util.UUID;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ApiModel(description = "Запрос на изменение директории файла")
public class MoveNoteRequestDto {
    @ApiModelProperty(value = "Внешний ID файла")
    private UUID createdFileExternalId;
    @ApiModelProperty(value = "Внешний ID новой директории")
    private UUID newDirectoryExternalId;
}
