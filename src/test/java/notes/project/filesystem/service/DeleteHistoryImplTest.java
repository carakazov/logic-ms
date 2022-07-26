package notes.project.filesystem.service;

import notes.project.filesystem.model.Cluster;
import notes.project.filesystem.model.CreatedFile;
import notes.project.filesystem.model.DeleteHistory;
import notes.project.filesystem.model.Directory;
import notes.project.filesystem.repository.DeleteHistoryRepository;
import notes.project.filesystem.service.DeleteHistoryService;
import notes.project.filesystem.service.impl.DeleteHistoryServiceImpl;
import notes.project.filesystem.utils.DbUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.aggregator.ArgumentAccessException;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DeleteHistoryImplTest {
    @Mock
    private DeleteHistoryRepository repository;

    @Captor
    private ArgumentCaptor<DeleteHistory> deleteHistorySaveCaptor;

    private DeleteHistoryService service;

    @BeforeEach
    void init() {
        service = new DeleteHistoryServiceImpl(repository);
    }

    @Test
    void deleteCreatedFileDeleteHistorySuccess() {
        CreatedFile createdFile = DbUtils.createdFile();
        DeleteHistory expected = DbUtils.deleteCreatedFileHistory();

        when(repository.save(any())).thenReturn(expected);

        service.createCreatedFileDeleteHistory(createdFile);

        verify(repository).save(deleteHistorySaveCaptor.capture());

        DeleteHistory actual = deleteHistorySaveCaptor.getValue();

        assertEquals(expected.getCreatedFile(), actual.getCreatedFile());
        assertEquals(expected.getEvent(), actual.getEvent());
    }

    @Test
    void createDirectoryDeleteHistorySuccess() {
        Directory directory = DbUtils.directory();
        DeleteHistory expected = DbUtils.deleteDirectoryHistory();

        when(repository.save(any())).thenReturn(expected);

        service.createDirectoryDeleteHistory(directory);

        verify(repository).save(deleteHistorySaveCaptor.capture());

        DeleteHistory actual = deleteHistorySaveCaptor.getValue();

        assertEquals(expected.getDirectory(), actual.getDirectory());
        assertEquals(expected.getEvent(), actual.getEvent());
    }

    @Test
    void createClusterDeleteHistorySuccess() {
        Cluster cluster = DbUtils.cluster();
        DeleteHistory expected = DbUtils.deleteClusterHistory();

        when(repository.save(any())).thenReturn(expected);

        service.createClusterDeleteHistory(cluster);

        verify(repository).save(deleteHistorySaveCaptor.capture());

        DeleteHistory actual = deleteHistorySaveCaptor.getValue();

        assertEquals(expected.getCluster(), actual.getCluster());
        assertEquals(expected.getEvent(), actual.getEvent());
    }
}