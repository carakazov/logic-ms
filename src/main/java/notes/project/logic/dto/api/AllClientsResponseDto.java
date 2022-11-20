package notes.project.logic.dto.api;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ApiModel(description = "Все клиенты системы")
public class AllClientsResponseDto {
    @ApiModelProperty(value = "Название системы")
    private String systemName;
    @ApiModelProperty(value = "Клиенты")
    private List<PersonalInfoDto> clients;
}
