package notes.project.logic.dto.api;

import java.time.LocalDateTime;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ApiModel(description = "Ответ на запрос истории удаления объектов")
public class DeleteHistoryResponseDto {
    @ApiModelProperty(value = "Название объекта")
    private String objectTitle;
    @ApiModelProperty(value = "Текущее состояние объекта")
    private String currentState;
    @ApiModelProperty(value = "Дата создания объекта")
    private LocalDateTime createdDate;
    @ApiModelProperty(value = "Список операций")
    private List<DeleteHistoryRecordDto> history;
}
