package notes.project.logic.service.api;

import java.util.List;

import notes.project.logic.dto.api.ChangeAccessModeRequestDto;
import notes.project.logic.model.Access;
import notes.project.logic.model.AccessMode;
import notes.project.logic.model.Client;
import notes.project.logic.model.Note;

public interface AccessService {
    Access addAccess(Note note, Client client, AccessMode accessMode);

    Access getAccessOfClientToNote(Client client, Note note);

    Boolean clientHasAccessToNote(Client client, Note note);

    List<Access> getAllAccessesToNote(Note note);

    void denyAccess(Note note, Client client);
}
