package UserDataBase;

import java.util.List;

public class Main {

    public static void main(String[] args) {
//        UserService userService = new UserService(new MongoRepository()); //podmieniamy
        UserService userService = new UserService(new MySqlRepository()); //podmieniamy

        User user1 = new User("1@sda.pl", "123", "Jan", "Kowalski");
        User user2 = new User("2@sda.pl", "123", "Jan", "Kowalski");

        userService.register(user1);
        userService.register(user2);


        System.out.println(userService.getUsers());

        System.out.println(userService.getUserByEmail("3@sda.pl"));
//
//        System.out.println(userService.checkData("2@sda.pl", "123"));
//
//        System.out.println(userService.login("1@sda.pl", "123"));
    }
}
