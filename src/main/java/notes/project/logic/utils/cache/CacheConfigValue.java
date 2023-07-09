package notes.project.logic.utils.cache;

public interface CacheConfigValue {
    String CREATED_FILE_EXTERNAL_ID = "#request.createdFileExternalId";
    String REQUEST_DIRECTORY_EXTERNAL_ID = "#request.directoryExternalId";
    String REQUEST_CLUSTER_EXTERNAL_ID = "#request.clusterExternalId";
    String REQUEST_CLIENT_EXTERNAL_ID = "#request.clientExternalId";
    String EXTERNAL_ID = "#externalId";
    String PERSONAL_INFO = "personalInfo";
    String NOTE_LIST = "notes";
    String ARCHIVE_HISTORY_LIST = "archiveList";
    String NOTE_DELETE_HISTORY = "deleteHistoryList";
    String REPLACING_HISTORY = "replacingHistory";
    String FILE_VERSION = "fileVersion";
    String DIRECTORY_LIST = "directoryList";
    String DIRECTORY_DELETE_HISTORY = "directoryDeleteHistory";
    String CLUSTER = "cluster";
    String CLUSTER_DELETE_HISTORY = "clusterDeleteHistory";
    String CLUSTER_EXTERNAL_ID = "#clusterExternalId";
    String DIRECTORY_EXTERNAL_ID = "#directoryExternalId";
    String ALL_CLIENTS_LIST = "allClientsList";
}
