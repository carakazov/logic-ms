package notes.project.logic.dto.api;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import notes.project.logic.model.AccessMode;

@Data
@Accessors(chain = true)
@ApiModel(description = "Ответ на запрос записки")
public class NoteResponseDto {
    @ApiModelProperty(value = "Записка")
    private NoteDto note;

    @ApiModelProperty(value = "Тип доступа")
    private AccessMode accessMode;
}
