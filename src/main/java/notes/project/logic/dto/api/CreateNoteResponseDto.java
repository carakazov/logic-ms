package notes.project.logic.dto.api;

import java.time.LocalDateTime;
import java.util.UUID;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ApiModel(description = "Ответ на создание файла")
public class CreateNoteResponseDto {
    @ApiModelProperty(value = "Дата создания")
    private LocalDateTime createdDate;
    @ApiModelProperty(value = "Внешний ID файла")
    private UUID externalId;
    @ApiModelProperty(value = "Название")
    private String title;
}
