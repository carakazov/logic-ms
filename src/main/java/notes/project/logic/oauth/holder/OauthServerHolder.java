package notes.project.logic.oauth.holder;

import notes.project.logic.oauth.TokenSource;

public class OauthServerHolder {
    private static TokenSource source;

    public static void setSource(TokenSource tokenSource) {
        source = tokenSource;
    }

    public static TokenSource getSource() {
        return source;
    }
}
