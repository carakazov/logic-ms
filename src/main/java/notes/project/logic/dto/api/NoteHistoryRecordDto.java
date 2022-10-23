package notes.project.logic.dto.api;

import java.time.LocalDateTime;
import java.util.UUID;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ApiModel(description = "Запись об истории изменений записки")
public class NoteHistoryRecordDto {
    @ApiModelProperty(value = "Дата обновления записки")
    private LocalDateTime editedDate;
    @ApiModelProperty(value = "ID версии")
    private UUID versionNoteGuid;
}
