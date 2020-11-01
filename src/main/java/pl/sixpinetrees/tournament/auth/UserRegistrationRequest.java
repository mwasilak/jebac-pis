package pl.sixpinetrees.tournament.auth;

import javax.validation.constraints.Size;

public class UserRegistrationRequest {

    @Size(min = 1, max = 32, message = "Username's length must be between {min} and {max} characters.")
    private String username;

    @Size(min = 1, max = 32, message = "Password's length must be between {min} and {max} characters.")
    private String password;

    public UserRegistrationRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
