package notes.project.logic.dto.api.admin;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@ApiModel(description = "Описание кластера для админа")
public class ClusterAdminDto {
	private UUID clusterExternalId;
	private UUID userExternalId;

	private List<DirectoryAdminDto> directories;
}
