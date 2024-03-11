package nl.hsleiden.iprwc2324.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class LoginResponse {

    public LoginResponse(String message, boolean success) {
        this.message = message;
        this.success = success;
    }

    private String message;

    private boolean success;

    private boolean admin;

    private String token;

}
