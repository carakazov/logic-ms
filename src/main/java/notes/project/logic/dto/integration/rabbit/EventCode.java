package notes.project.logic.dto.integration.rabbit;

import lombok.Getter;

public enum EventCode {
    CLUSTER_WAS_ARCHIVED("clusterWasArchived"),
    CLUSTER_WILL_BE_DELETED_SOON("clusterWillBeDeletedSoon"),
    CLUSTER_IRREVOCABLE_DELETED("clusterIrrevocableDeleted");

    @Getter
    private final String code;

    EventCode(String code) {
        this.code = code;
    }
}
