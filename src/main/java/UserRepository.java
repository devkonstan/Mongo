import java.util.List;

public interface UserRepository {
    void insert(User user);
    List<User> findAll();
    boolean isEmailExist(String email);
    User findUser(String email);
    boolean Login(String email, String password);
    boolean login(String email, String password);

}
