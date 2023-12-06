package de.htwberlin.webapp.login;

public class UserManipulationRequest {
    private String username;
    private String password;

    private String firstname;
    private String lastname;

    public UserManipulationRequest() {
    }

    public UserManipulationRequest( String username, String password, String firstname, String lastname) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.username = username;
        this.password = password;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getLastname() {
        return lastname;
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
