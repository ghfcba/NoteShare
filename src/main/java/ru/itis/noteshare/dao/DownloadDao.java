package ru.itis.noteshare.dao;

import java.sql.Connection;

public class DownloadDao {
    private Connection connection;

    public DownloadDao(Connection connection) {
        this.connection = connection;
    }
}
