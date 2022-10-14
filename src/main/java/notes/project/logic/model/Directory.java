package notes.project.logic.model;

import java.util.UUID;
import javax.persistence.*;

import lombok.Data;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
@Entity(name = "directories")
public class Directory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private UUID externalId;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;
}