import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

public class MySQLRepository implements UserRepository {

    private Logger logger = Logger.getLogger(this.getClass().getName());

    public MySQLRepository() {
        new InitMysqlDatabase();
    }

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
        String insertSql = ("INSERT INTO Users VALUES(NULL, ?, ?, ?, ?, ?, ?);");
        try {
            connection = MysqlConnection.getConnection();
            statement = connection.prepareStatement(insertSql);
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getName());
            statement.setString(4, user.getLastname());
            statement.setBoolean(5, user.isActive());
            statement.setObject(6, user.getCreatedAt());
            statement.executeUpdate();

            logger.info("User " + user.getEmail() + "already saved");
        } catch (SQLException e) {
            logger.warning("Cannot insert user" + user.getEmail() + e);
        } finally {
            closeConnections(connection, statement);
        }
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public boolean isEmailExist(String email) {
        return false;
    }

    @Override
    public User findUser(String email) {
        return null;
    }

    @Override
    public boolean Login(String email, String password) {
        return false;
    }

    @Override
    public boolean login(String email, String password) {
        return false;
    }
}
