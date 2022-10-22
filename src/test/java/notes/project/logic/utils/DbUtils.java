package notes.project.logic.utils;

import liquibase.pro.packaged.N;
import lombok.experimental.UtilityClass;
import notes.project.logic.model.*;

import static notes.project.logic.utils.TestDataConstants.*;

@UtilityClass
public class DbUtils {
    public static Access access() {
        return new Access()
            .setId(ID)
            .setClient(client())
            .setNote(note())
            .setAccessMode(AccessMode.READ_WRITE);
    }

    public static Directory alternateDirectory() {
        return directory()
            .setId(ID_2)
            .setExternalId(DIRECTORY_ALTERNATE_EXTERNAL_ID);
    }

    public static Note note() {
        return new Note()
            .setId(ID)
            .setExternalId(NOTE_EXTERNAL_ID)
            .setClient(client())
            .setDirectory(directory())
            .setDeleted(Boolean.FALSE);
    }

    public static Directory directory() {
        return new Directory()
            .setId(ID)
            .setExternalId(DIRECTORY_EXTERNAL_ID)
            .setClient(client())
            .setDeleted(Boolean.FALSE);
    }

    public static Client client() {
        return new Client()
            .setId(ID)
            .setExternalId(CLIENT_EXTERNAL_ID)
            .setClusterExternalId(CLUSTER_EXTERNAL_ID);
    }
}
