package notes.project.logic.model;

import java.util.UUID;
import javax.persistence.*;

import liquibase.pro.packaged.J;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Entity(name = "notes")
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private UUID externalId;

    private String title;

    @ManyToOne
    @JoinColumn(name = "directory_id")
    private Directory directory;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    private Boolean deleted;
}
