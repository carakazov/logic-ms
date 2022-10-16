package notes.project.logic.dto.api;

import java.time.LocalDateTime;
import java.util.UUID;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ApiModel(description = "Ответ на изменение директоркии файла")
public class MoveNoteResponseDto {
    @ApiModelProperty(value = "Внешний ID файла")
    private UUID createdFileExternalId;
    @ApiModelProperty(value = "Внешний ID новой директории")
    private UUID newDirectoryExternalId;
    @ApiModelProperty(value = "Дата изменения файла")
    private LocalDateTime replacingDate;
}
