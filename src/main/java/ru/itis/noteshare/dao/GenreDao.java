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
}
