package notes.project.logic.repository;

import java.util.Optional;
import java.util.UUID;

import notes.project.logic.model.Directory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DirectoryRepository extends JpaRepository<Directory, Long> {
    Optional<Directory> findByExternalId(UUID externalId);
}
