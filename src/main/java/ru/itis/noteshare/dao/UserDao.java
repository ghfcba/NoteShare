package ru.itis.noteshare.dao;

import ru.itis.noteshare.models.UserModel;

import java.sql.*;
import java.util.Date;

public class UserDao {
    private Connection connection;

    public UserDao(Connection connection) {
        this.connection = connection;
    }

    public UserModel getUserByUsername(String username) {
        String sql = "select * from users where username = ?";
        UserModel user = null;

        try(PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, username);

            try(ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    user = new UserModel(
                            rs.getLong("user_id"),
                            rs.getString("username"),
                            rs.getString("email"),
                            rs.getString("password_hash"),
                            rs.getTimestamp("registered_at"),
                            rs.getString("role")
                    );
                }

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    public UserModel getUserByEmail(String email) {
        String sql = "select * from users where email = ?";
        UserModel user = null;
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1, email);
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    user = new UserModel(
                            rs.getLong("user_id"),
                            rs.getString("username"),
                            rs.getString("email"),
                            rs.getString("password_hash"),
                            rs.getTimestamp("registered_at"),
                            rs.getString("role")
                    );
                }
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }
    public UserModel saveUser(UserModel user){
        String sql = "insert into users (username, email, password_hash, registered_at, role) values(?, ?, ?, ?,?)";

        java.util.Date date = new Date();

        Timestamp time = new Timestamp(date.getTime());
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPasswordHash());
            ps.setTimestamp(4, time);
            ps.setString(5, "USER");
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

}
