import java.time.LocalDate;
import java.util.List;

//napisac testy
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void register(User user) {
        user.setActive(true);
        user.setCreatedAt(LocalDate.now());

        if (!userRepository.isEmailExist(user.getEmail())) {
            userRepository.insert(user);
        }
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUserByEmail(String email) {
        return userRepository.findUser(email);
    }

    public boolean checkData(String email, String password) {
        return userRepository.Login(email,password);
    }

    public boolean login(String email, String password) { return userRepository.login(email,password);}
}
