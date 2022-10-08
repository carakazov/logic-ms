package notes.project.logic.repository;

import java.util.Optional;
import java.util.UUID;

import notes.project.logic.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByExternalId(UUID externalId);
}
