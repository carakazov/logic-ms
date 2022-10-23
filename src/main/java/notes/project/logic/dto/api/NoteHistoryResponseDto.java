package notes.project.logic.dto.api;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ApiModel(description = "Ответ на запрос истории изменений")
public class NoteHistoryResponseDto {
    @ApiModelProperty(value = "Информация по файлу")
    private NoteHistoryDto note;
    @ApiModelProperty(value = "Список изменений")
    private List<NoteHistoryRecordDto> history;
}
