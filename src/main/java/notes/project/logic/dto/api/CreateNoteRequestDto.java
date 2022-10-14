package notes.project.logic.dto.api;

import java.util.UUID;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ApiModel(description = "Запрос на создания записки")
public class CreateNoteRequestDto {
    @ApiModelProperty(value = "Содержание")
    private String content;
    @ApiModelProperty(value = "Внешний ID директории")
    private UUID directoryExternalId;
    @ApiModelProperty(value = "Название записки")
    private String title;
}
