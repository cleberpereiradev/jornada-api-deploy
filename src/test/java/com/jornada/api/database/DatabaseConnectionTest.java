package com.jornada.api.database;

import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.assertj.core.api.Fail.fail;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DatabaseConnectionTest {

    @Test
    void testDatabaseConnection() {
        String url = "jdbc:mysql://localhost:3307/jornada?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
        String username = "root";
        String password = "root123";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            assertNotNull(connection);
        } catch (SQLException e) {
            e.printStackTrace();
            fail("Database connection failed: " + e.getMessage());
        }
    }
}