import com.google.gson.Gson;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class MongoRepository implements UserRepository
{ //klasa majaca dostep do bd
    private Gson gson = new Gson();
    MongoCollection<Document> users;

    public MongoRepository() {
        MongoClient mongoClient = MongoClients.create();
        MongoDatabase database = mongoClient.getDatabase("UsersDB");
        users = database.getCollection("Users");

        users.drop();
    }

    public void insert(User user) {
        users.insertOne(Document.parse(gson.toJson(user)));

    }

    public List<User> findAll() {
        FindIterable<Document> AllUsers = users.find();
        List<User> userList = new ArrayList<>();

        for (Document allUser : AllUsers) {
            User user = gson.fromJson(allUser.toJson(), User.class);
            userList.add(user);
        }
        return userList;
    }

    public boolean isEmailExist(String email) {
        Document foundUsers = users.find(eq("email", email)).first();

        return foundUsers != null;
    }

    public User findUser(String email) {

        Document foundUser = users.find(eq("email", email)).first();
        if (foundUser != null) {
            return gson.fromJson(foundUser.toJson(), User.class);
        } else
            return null;
//        return users.find(Filters.eq("email", email)).first(); //inny sposob
    }

    public boolean Login(String email, String password) {
        Document foundData = users.find(Filters.and(
                eq("email", email),
                eq("password", password))).first();
        return foundData != null;
    }

    public boolean login(String email, String password) {
        User foundUser = findUser(email);

        if (foundUser != null && foundUser.getPassword().equals(password)) {
            return true;
        }
        return false;
    }

}
