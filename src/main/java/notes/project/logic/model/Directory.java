package notes.project.logic.model;

import java.util.List;
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

    private String title;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToMany(mappedBy = "directory", fetch = FetchType.LAZY)
    private List<Note> notes;

    private Boolean deleted;
}
