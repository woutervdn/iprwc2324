package nl.hsleiden.iprwc2324.requests;

import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {

    @Nonnull
    private String username;

    @Nonnull
    private String password;

}
