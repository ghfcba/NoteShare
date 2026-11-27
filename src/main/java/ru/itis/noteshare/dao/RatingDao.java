package ru.itis.noteshare.dao;

import java.sql.Connection;

public class RatingDao {
    private Connection connection;

    public RatingDao(Connection connection) {
        this.connection = connection;
    }
}
