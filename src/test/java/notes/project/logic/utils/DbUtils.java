package notes.project.logic.utils;

import liquibase.pro.packaged.N;
import lombok.experimental.UtilityClass;
import notes.project.logic.model.Client;
import notes.project.logic.model.Directory;
import notes.project.logic.model.Note;

import static notes.project.logic.utils.TestDataConstants.*;

@UtilityClass
public class DbUtils {
    public static Note note() {
        return new Note()
            .setId(ID)
            .setExternalId(NOTE_EXTERNAL_ID)
            .setClient(client())
            .setDirectory(directory());
    }

    public static Directory directory() {
        return new Directory()
            .setId(ID)
            .setExternalId(DIRECTORY_EXTERNAL_ID)
            .setClient(client());
    }

    public static Client client() {
        return new Client()
            .setId(ID)
            .setExternalId(CLIENT_EXTERNAL_ID)
            .setClusterExternalId(CLUSTER_EXTERNAL_ID);
    }
}
