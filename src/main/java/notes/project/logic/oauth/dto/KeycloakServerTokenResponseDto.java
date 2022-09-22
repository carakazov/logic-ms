package notes.project.logic.oauth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class KeycloakServerTokenResponseDto extends TokenResponseDto {
    @JsonProperty("refresh_token_expires_in")
    private Long refreshTokenExpiresIn;
    @JsonProperty("refresh_token")
    private String refreshToken;
    @JsonProperty("not_before_policy")
    private Integer notBeforePolicy;
    @JsonProperty("session_state")
    private String sessionState;
}
