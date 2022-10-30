package notes.project.logic.dto.api;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ApiModel(description = "Краткое описание директории")
public class DirectoryInfoDto {
    @ApiModelProperty(value = "Дата создания директории")
    private LocalDateTime creationDate;

    @ApiModelProperty(value = "Внешний ID директории")
    private UUID externalId;

    @ApiModelProperty(value = "Название директории")
    private String title;

    @ApiModelProperty(value = "Список записок")
    private List<NoteInfoDto> notes;
}
