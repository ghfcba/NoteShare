package ru.itis.noteshare.dao;

import ru.itis.noteshare.models.ComposerModel;
import ru.itis.noteshare.models.UserModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
                            rs.getLong("composer_id"),
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

    public ComposerModel getCompById(Long composerId) {
        String sql = "select * from composers where composer_id = ?";
        ComposerModel composerModel = null;
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, composerId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    composerModel = new ComposerModel(
                            rs.getLong("composer_id"),
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

    public List<ComposerModel> getAllComposers(){
        String sql = "select * from composers";
        List<ComposerModel> listComp = new ArrayList<>();
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    ComposerModel model = ComposerModel.builder()
                            .composerId(rs.getLong("composer_id"))
                            .fullName(rs.getString("full_name"))
                            .country(rs.getString("country"))
                            .build();
                    listComp.add(model);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listComp;
    }
}
