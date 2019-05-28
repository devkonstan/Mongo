
public class Main {

    public static void main(String[] args) {
//        UserService userService = new UserService(new MongoRepository()); //podmieniamy
        UserService userService = new UserService(new MySQLRepository()); //podmieniamy

        User user1 = new User("1@sda.pl", "123", "Jan", "Kowalski");

        userService.register(user1);
        userService.register(user1);

        System.out.println(userService.getUsers());

        System.out.println(userService.getUserByEmail("2@sda.pl"));

        System.out.println(userService.checkData("1@sda.pl","123"));

        System.out.println(userService.login("1@sda.pl","123"));
    }
}
