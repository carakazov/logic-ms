package notes.project.logic.dto.integration.rabbit;

import lombok.Getter;
import notes.project.logic.exception.LogicMsException;

public enum EventCode {
    CLUSTER_WAS_ARCHIVED("clusterWasArchived"),
    CLUSTER_WILL_BE_DELETED_SOON("clusterWillBeDeletedSoon"),
    CLUSTER_IRREVOCABLE_DELETED("clusterIrrevocableDeleted");

    @Getter
    private final String code;

    EventCode(String code) {
        this.code = code;
    }

    public static EventCode getByCode(String code) {
        if(CLUSTER_WAS_ARCHIVED.getCode().equals(code)) {
            return CLUSTER_WAS_ARCHIVED;
        } else if(CLUSTER_WILL_BE_DELETED_SOON.getCode().equals(code)) {
            return CLUSTER_WILL_BE_DELETED_SOON;
        } else if(CLUSTER_IRREVOCABLE_DELETED.getCode().equals(code)) {
            return CLUSTER_IRREVOCABLE_DELETED;
        } else {
            throw new LogicMsException("Can not parse event code " + code);
        }
    }
}
