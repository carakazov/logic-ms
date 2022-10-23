package notes.project.logic.dto.api;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ApiModel(description = "")
public class NoteReplacingHistoryResponseDto {
    @ApiModelProperty(value = "Записка")
    private NoteReplacingHistoryDto note;
    @ApiModelProperty(value = "Перемещения")
    private List<ReplacingHistoryRecordDto> history;
}
