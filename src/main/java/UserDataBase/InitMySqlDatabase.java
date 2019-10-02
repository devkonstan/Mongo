package UserDataBase;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

public class InitMySqlDatabase {
    private Logger logger = Logger.getLogger(this.getClass().getName());

    public InitMySqlDatabase() {
        createUserTable();
    }

    public void createUserTable() {
        String dropTable="DROP TABLE users";
        String createTable = "CREATE TABLE IF NOT EXISTS users(" +
                "ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                "EMAIL VARCHAR(25) NOT NULL UNIQUE," +
                "PASSWORD VARCHAR(50) NOT NULL," +
                "NAME VARCHAR(20) NOT NULL," +
                "LAST_NAME VARCHAR(30) NOT NULL," +
                "ACTIVE BOOLEAN NOT NULL," +
                "CREATED_AT TIMESTAMP NOT NULL" +
                ");";

        Connection connection;
        Statement statement;
        try {
            connection = MySqlConnection.getConnection();
            statement = connection.createStatement();
            statement.execute(dropTable);
            statement.execute(createTable);
        } catch (SQLException e) {
            logger.warning("Cannot create User table: " + e);
        }
    }
}
