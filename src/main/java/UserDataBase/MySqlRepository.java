package UserDataBase;

import com.google.gson.Gson;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class MySqlRepository implements UserRepository {

    public MySqlRepository() {
        new InitMySqlDatabase();
    }

    private Gson gson = new Gson();
    private Logger logger = Logger.getLogger(this.getClass().getName());


    private void closeConnections(Connection connection, PreparedStatement statement) {
        try {
            connection.close();
            statement.close();
        } catch (SQLException ex) {
            logger.warning("Cannot close connections");
        }
    }

    @Override
    public void insert(User user) {
        Connection connection = null;
        PreparedStatement statement = null;

        //null zostanie zamieniony na id generowany w b.d.
        String insertSql = ("INSERT INTO users VALUES(NULL, ?, ?, ?, ?, ?, ?);");
        try {
            connection = MySqlConnection.getConnection();
            statement = connection.prepareStatement(insertSql);
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getName());
            statement.setString(4, user.getLastname());
            statement.setBoolean(5, user.isActive());
            statement.setObject(6, user.getCreatedAt());
            statement.executeUpdate();

            logger.info("User " + user.getEmail() + " already saved");
        } catch (SQLException e) {
            logger.warning("Cannot insert user" + user.getEmail() + e);
        } finally {
            closeConnections(connection, statement);
        }
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = MySqlConnection.getConnection();
            statement = connection.prepareStatement("SELECT * FROM users");
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setEmail(rs.getString("email"));
                user.setName(rs.getString("name"));
                user.setLastname(rs.getString("last_name"));
                users.add(user);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeConnections(connection, statement);
        }
        return users;
    }

    @Override
    public boolean isEmailExist(String email) {
//        List<User> users = new ArrayList<>();
//        Connection connection = null;
//        PreparedStatement statement = null;
//
//        try {
//            connection = MySqlConnection.getConnection();
//            statement = connection.prepareStatement("SELECT * FROM users WHERE email=" + "email");
//            ResultSet rs = statement.executeQuery();
//            if (rs.next()) {
//                User user = new User(email);
//                user.setEmail(rs.getString("email"));
//                users.add(user);
//            }
//        } catch (SQLException ex) {
//            logger.warning("Cannot find user" + ex);
//        } finally {
//            closeConnections(connection, statement);
//        }
//        return users!=null;
        return false;
    }

    @Override
    public User findUser(String email) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = MySqlConnection.getConnection();
            statement = connection.prepareStatement("SELECT * FROM users WHERE email=" + "email");
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                User user = new User(email);
                user.setEmail(rs.getString("email"));
                user.setName(rs.getString("name"));
                user.setLastname(rs.getString("last_name"));
                return user;
            }
        } catch (SQLException ex) {
            logger.warning("Cannot find user" + ex);
        } finally {
            closeConnections(connection, statement);
        }
        return null;
    }

    @Override //getuser z konstruktorem
    public boolean Login(String email, String password) {
        return false;
    }

    @Override   //getuser z konstruktorem
    public boolean login(String email, String password) {
        return false;
    }
}
