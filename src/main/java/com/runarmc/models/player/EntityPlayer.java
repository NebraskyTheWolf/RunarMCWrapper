package com.runarmc.models.player;

import com.runarmc.annotations.Route;
import com.runarmc.models.AbstractModel;
import com.runarmc.results.player.PlayerResult;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;

@Route(
        method = "GET",
        route = "/api/v1/player/:uuid",
        result = PlayerResult.class,
        arguments = true
)
@Getter @Setter @NoArgsConstructor
public class EntityPlayer extends AbstractModel {
    String _id; // UNIQUE DATABASE ID
    String uuid;
    String username;
    String password;
    String email;
    SecurityOption security;
    Timestamp createdAt;
    Timestamp updatedAt;

    @Getter @Setter @NoArgsConstructor
    public static class SecurityOption  implements Serializable {
        Boolean isMFAEnabled;
        Boolean isEmailVerified;
        Boolean isTerminated;
        Boolean isOnboardFinished;
    }
}
