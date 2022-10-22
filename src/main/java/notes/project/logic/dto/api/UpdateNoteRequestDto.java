package notes.project.logic.dto.api;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ApiModel(description = "Запрос на изменение записки")
public class UpdateNoteRequestDto {
    @ApiModelProperty(value = "Новое содержание")
    private String content;
}
