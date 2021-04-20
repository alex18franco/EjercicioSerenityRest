package co.com.reto2021.wolox.model;

public class Usuario {
    protected String email;
    protected String password;
    private String firstName;
    private String lastName;
    private String role;

    public String getEmail() {
        return email;
    }

    public String getRol() {
        return role;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
