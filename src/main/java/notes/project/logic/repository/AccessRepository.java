package notes.project.logic.repository;

import java.util.List;

import notes.project.logic.model.Access;
import notes.project.logic.model.Client;
import notes.project.logic.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessRepository extends JpaRepository<Access, Long> {
    Access findByClientAndNote(Client client, Note note);

    boolean existsByClientAndNote(Client client, Note note);

    List<Access> findAllByNote(Note note);

    void deleteByNoteAndClient(Note note, Client client);

    List<Access> findAllByClient(Client client);
}
