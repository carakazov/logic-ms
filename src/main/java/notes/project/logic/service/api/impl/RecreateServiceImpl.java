package notes.project.logic.service.api.impl;

import java.util.UUID;

import javax.transaction.Transactional;

import lombok.RequiredArgsConstructor;
import notes.project.logic.model.Client;
import notes.project.logic.model.Directory;
import notes.project.logic.model.Note;
import notes.project.logic.service.api.ClientService;
import notes.project.logic.service.api.DirectoryService;
import notes.project.logic.service.api.NoteService;
import notes.project.logic.service.api.RecreateService;
import notes.project.logic.service.integration.http.FileSystemRestService;
import notes.project.logic.service.integration.http.client.FileSystemFeignClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecreateServiceImpl implements RecreateService {
    private final ClientService clientService;
    private final DirectoryService directoryService;
    private final NoteService noteService;
    private final FileSystemRestService fileSystemRestService;

    @Override
    @Transactional
    public void recreateCluster(UUID clientExternalId) {
        Client client = clientService.findByExternalId(clientExternalId);
        fileSystemRestService.recreateCluster(client.getClusterExternalId());
    }

    @Override
    @Transactional
    public void recreateDirectory(UUID externalId) {
        Directory directory = directoryService.findDirectoryByExternalId(externalId);
        fileSystemRestService.recreateDirectory(directory.getExternalId(), directory.getClient().getClusterExternalId());
        directory.setDeleted(Boolean.FALSE);
        directory.getNotes().forEach(item -> item.setDeleted(Boolean.FALSE));
    }

    @Override
    @Transactional
    public void recreateNote(UUID externalId) {
        Note note = noteService.findByExternalId(externalId);
        fileSystemRestService.recreateFile(note.getExternalId(), note.getDirectory().getExternalId(), note.getClient().getClusterExternalId());
        note.setDeleted(Boolean.FALSE);
        note.getDirectory().setDeleted(Boolean.FALSE);
    }
}
