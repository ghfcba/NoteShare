package ru.itis.noteshare.dao;

import ru.itis.noteshare.models.GenreModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GenreDao {
    private Connection connection;

    public GenreDao(Connection connection) {
        this.connection = connection;
    }

    //на получение всех жанров
    public List<GenreModel> getAllGenres(){
        String sql = "select * from genres";
        List<GenreModel> listOfGenres = new ArrayList<>();
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    GenreModel genreModel = GenreModel.builder()
                            .genreId(rs.getLong("genre_id"))
                            .name(rs.getString("name")).build();
                    listOfGenres.add(genreModel);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listOfGenres;
    }
    public GenreModel getGenreById(Long genreId){
        String sql = "select * from genres where genre_id = ?";
        GenreModel genreModel = null;
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setLong(1, genreId);
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    genreModel = GenreModel.builder()
                            .genreId(rs.getLong("genre_id"))
                            .name(rs.getString("name"))
                            .build();
                }

            }

            return genreModel;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public GenreModel getGenreByName(String name){
        String sql = "select * from genres where name = ?";
        GenreModel genreModel = null;
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1, name);
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    genreModel = GenreModel.builder()
                            .genreId(rs.getLong("genre_id"))
                            .name(rs.getString("name"))
                            .build();
                }
            }
            return genreModel;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
