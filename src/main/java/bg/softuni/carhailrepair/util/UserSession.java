package bg.softuni.carhailrepair.util;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@SessionScope
@Component
public class UserSession {
    private long id;
    private String username;

    public void login(long id, String username) {
        this.id = id;
        this.username = username;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long id() {
        return id;
    }

    public String username() {
        return username;
    }

    public boolean isLoggedIn() {
        return id > 0;
    }

    public void logout() {
        id = 0;
        username = null;
    }
}
