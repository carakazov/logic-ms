package notes.project.logic.service.api.impl;

import java.util.List;
import javax.transaction.Transactional;

import lombok.RequiredArgsConstructor;
import notes.project.logic.dto.api.ChangeAccessModeRequestDto;
import notes.project.logic.model.Access;
import notes.project.logic.model.AccessMode;
import notes.project.logic.model.Client;
import notes.project.logic.model.Note;
import notes.project.logic.repository.AccessRepository;
import notes.project.logic.service.api.AccessService;
import notes.project.logic.utils.mapper.AccessMapper;
import notes.project.logic.validation.Validator;
import notes.project.logic.validation.dto.OwningValidationDto;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccessServiceImpl implements AccessService {
    private final AccessRepository repository;
    private final AccessMapper accessMapper;
    private final Validator<OwningValidationDto> owningValidator;

    @Override
    public Access getAccessOfClientToNote(Client client, Note note) {
        return repository.findByClientAndNote(client, note);
    }

    @Override
    @Transactional
    public Access addAccess(Note note, Client client, AccessMode accessMode) {
        return repository.save(accessMapper.to(note, client, accessMode));
    }

    @Override
    @Transactional
    public Boolean clientHasAccessToNote(Client client, Note note) {
        return repository.existsByClientAndNote(client, note);
    }

    @Override
    public List<Access> getAllAccessesToNote(Note note) {
        return repository.findAllByNote(note);
    }

    @Override
    public void denyAccess(Note note, Client client) {
        repository.deleteByNoteAndClient(note, client);
    }
}
