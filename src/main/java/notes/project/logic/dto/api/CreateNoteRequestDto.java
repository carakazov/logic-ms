package notes.project.logic.dto.api;

import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

@Data
@Accessors(chain = true)
@ApiModel(description = "Запрос на создания записки")
public class CreateNoteRequestDto {
    @Size(min = 1, max = 125)
    @ApiModelProperty(value = "Содержание", required = true)
    private String content;
    @ApiModelProperty(value = "Внешний ID директории", required = true)
    private UUID directoryExternalId;
    @Size(min = 1, max = 3064)
    @ApiModelProperty(value = "Название записки", required = true)
    private String title;
}
