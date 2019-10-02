package UserDataBase;

import lombok.Data;

import java.time.LocalDate;

@Data
public class User {
    private String email;
    private String password;
    private String name;
    private String lastname;
    private boolean active;
    private LocalDate createdAt;

    public User(String email, String password, String name, String lastname) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.lastname = lastname;
        createdAt = LocalDate.now();
    }

    public User(String email) {
        this.email = email;
    }

    public User() {

    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", active=" + active +
                ", createdAt=" + createdAt +
                '}';
    }
}
