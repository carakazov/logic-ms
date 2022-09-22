package notes.project.logic.dto.integration.filesystem;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class CreateClusterRequestDto {
    private String clusterTitle;
}
