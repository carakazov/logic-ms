package notes.project.logic.dto.api;

import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ApiModel(description = "Запрос на изменение записки")
public class UpdateNoteRequestDto {
    @Size(min = 1, max = 3064)
    @ApiModelProperty(value = "Новое содержание", required = true)
    private String content;
}
