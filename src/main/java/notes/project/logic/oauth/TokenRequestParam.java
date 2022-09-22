package notes.project.logic.oauth;

import lombok.Getter;

public enum TokenRequestParam {
    GRANT_TYPE("grant_type"),
    CLIENT_ID("client_id"),
    USERNAME("username"),
    PASSWORD("password"),
    CLIENT_SECRET("client_secret");
    @Getter
    private final String param;

    TokenRequestParam(String param) {
        this.param = param;
    }
}
