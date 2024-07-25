package bg.softuni.carhailrepair.model;

import jakarta.persistence.*;

@Entity
@Table(name = "admins")
public class Admin extends BaseEntity {
    @OneToOne
    @JoinColumn(name = "user_id", unique = true, nullable = false)
    private User user;

    public Admin() {
    }

    public Admin(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}