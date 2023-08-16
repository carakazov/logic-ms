package notes.project.logic.dto.api;

import java.time.LocalDateTime;
import java.util.UUID;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ApiModel(description = "Записка")
public class NoteDto {
    @ApiModelProperty(value = "Содержание записки")
    private String content;
    @ApiModelProperty(value = "Дата создания записки")
    private LocalDateTime creationDate;
    @ApiModelProperty(value = "Название записки")
    private String title;

    @ApiModelProperty(value = "Внешний ID записки. Возвращается только в методе запросе не свои записок")
    private UUID externalId;
}
