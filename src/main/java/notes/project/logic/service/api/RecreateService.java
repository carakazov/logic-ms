package notes.project.logic.service.api;

import java.util.UUID;

public interface RecreateService {
    void recreateCluster(UUID clientExternalId);

    void recreateDirectory(UUID externalId);

    void recreateNote(UUID externalId);
}
