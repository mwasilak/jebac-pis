package pl.sixpinetrees.tournament.auth;

import java.io.Serializable;

class UserLoginResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String authToken;

    public UserLoginResponse(String authToken) {
        this.authToken = authToken;
    }

    public String getAuthToken() {
        return authToken;
    }
}
