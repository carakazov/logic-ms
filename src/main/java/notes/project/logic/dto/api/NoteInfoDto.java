package notes.project.logic.dto.api;

import java.time.LocalDateTime;
import java.util.UUID;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ApiModel(description = "Краткое описание записки")
public class NoteInfoDto {
    @ApiModelProperty(value = "Дата создания файла")
    private LocalDateTime creationDate;

    @ApiModelProperty(value = "Внешний ID файла")
    private UUID externalId;

    @ApiModelProperty(value = "Название файла")
    private String title;
}
