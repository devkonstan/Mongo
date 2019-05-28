import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

public class InitMysqlDatabase {
    private Logger logger = Logger.getLogger(this.getClass().getName());

    public InitMysqlDatabase() {
        createUserTable();
    }

    public void createUserTable() {
        String createUserTable = "CREATE TABLE IF NOT EXISTS Users(" +
                "ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                "EMAIL VARCHAR(25) NOT NULL UNIQUE," +
                "PASSWORD VARCHAR(50) NOT NULL," +
                "NAME VARCHAR(20) NOT NULL," +
                "LAST_NAME VARCHAR(30) NOT NULL," +
                "ACTIVE BOOLEAN NOT NULL," +
                "CREATED_AT TIMESTAMP NOT NULL" +
                ");";

        Connection connection = null;
        Statement statement = null;

        try {
            connection = MysqlConnection.getConnection();
            statement = connection.createStatement();
            statement.execute(createUserTable);
        } catch (SQLException e) {
            logger.warning("Cannot create User table: " + e);
        }
    }
}
