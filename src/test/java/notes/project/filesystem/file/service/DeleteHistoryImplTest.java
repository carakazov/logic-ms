package notes.project.filesystem.file.service;

import notes.project.filesystem.model.DeleteHistory;
import notes.project.filesystem.model.Directory;
import notes.project.filesystem.repository.DeleteHistoryRepository;
import notes.project.filesystem.service.DeleteHistoryService;
import notes.project.filesystem.service.impl.DeleteHistoryServiceImpl;
import notes.project.filesystem.utils.DbUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
    private ArgumentCaptor<DeleteHistory> directoryDeleteHistorySaveCaptor;

    private DeleteHistoryService service;

    @BeforeEach
    void init() {
        service = new DeleteHistoryServiceImpl(repository);
    }

    @Test
    void createDirectoryDeleteHistorySuccess() {
        Directory directory = DbUtils.directory();
        DeleteHistory expected = DbUtils.deleteDirectoryHistory();

        when(repository.save(any())).thenReturn(expected);

        service.createDirectoryDeleteHistory(directory);

        verify(repository).save(directoryDeleteHistorySaveCaptor.capture());

        DeleteHistory actual = directoryDeleteHistorySaveCaptor.getValue();

        assertEquals(expected.getDirectory(), actual.getDirectory());
        assertEquals(expected.getEvent(), actual.getEvent());
    }
}