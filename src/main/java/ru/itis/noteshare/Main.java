package ru.itis.noteshare;

import ru.itis.noteshare.dao.UserDao;
import ru.itis.noteshare.models.UserModel;
import ru.itis.noteshare.utils.DataConnection;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws SQLException {
        UserDao userDao = new UserDao(DataConnection.getConnection());

        Date date = new Date();

        Timestamp time = new Timestamp(date.getTime());

        UserModel dilya = new UserModel( "dilya22284", "d4ily4a@gmail.com", "321", "ADMIN");


        UserModel user1 =userDao.getUserByUsername("dilya228");
        System.out.println(userDao.getUserByEmail("d4ily4a@gmail.com").toString());

    }
}
