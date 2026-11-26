package ru.itis.noteshare.dao;

import ru.itis.noteshare.models.ComposerModel;
import ru.itis.noteshare.models.UserModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ComposerDao {
    private Connection connection;

    public ComposerDao(Connection connection) {
        this.connection = connection;
    }
    public ComposerModel saveComposer(ComposerModel composerModel){
        String sql = "insert into composers(full_name, country) values (?, ?)";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1, composerModel.getFullName());
            ps.setString(2, composerModel.getCountry());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return composerModel;
    }
    public ComposerModel getCompByFullName(String fullName){
        String sql = "select * from composers where full_name = ?";
        ComposerModel composerModel = null;
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1, fullName);
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    composerModel = new ComposerModel(
                            rs.getString("full_name"),
                            rs.getString("country")
                    );
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return composerModel;
    }
}
