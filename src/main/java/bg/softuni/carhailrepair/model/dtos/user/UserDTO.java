package bg.softuni.carhailrepair.model.dtos.user;

import bg.softuni.carhailrepair.model.enums.UserRole;

public class UserDTO {
    private Long id;
    private String username;
    private String email;
    private UserRole role;

    public UserDTO() {}

    public UserDTO(Long id, String username, String email, UserRole role) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}
