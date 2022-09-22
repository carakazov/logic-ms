package notes.project.logic.utils;

import lombok.experimental.UtilityClass;
import notes.project.logic.model.Client;

import static notes.project.logic.utils.TestDataConstants.*;

@UtilityClass
public class DbUtils {
    public static Client client() {
        return new Client()
            .setId(ID)
            .setExternalId(CLIENT_EXTERNAL_ID)
            .setClusterExternalId(CLUSTER_EXTERNAL_ID);
    }
}
