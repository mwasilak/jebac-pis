package pl.sixpinetrees.tournament.auth;

import javax.validation.constraints.Size;

public class UserRegistrationRequest {

    @Size(min = 1, max = 32, message = "Username's length must be between {min} and {max} characters.")
    private String username;

    @Size(min = 1, max = 32, message = "Password's length must be between {min} and {max} characters.")
    private String password;

    @Size(min = 1, max = 32, message = "First name's length must be between {min} and {max} characters.")
    private String firstName;

    @Size(min = 1, max = 32, message = "Last name's length must be between {min} and {max} characters.")
    private String lastName;

    public UserRegistrationRequest(String username, String password, String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
