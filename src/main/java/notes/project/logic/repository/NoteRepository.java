package notes.project.logic.repository;

import java.util.Optional;
import java.util.UUID;

import notes.project.logic.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
    Optional<Note> findByExternalId(UUID externalId);
}
