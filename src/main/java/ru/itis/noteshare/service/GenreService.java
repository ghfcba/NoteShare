package ru.itis.noteshare.service;

import ru.itis.noteshare.dao.GenreDao;
import ru.itis.noteshare.models.GenreModel;
import ru.itis.noteshare.utils.DataConnection;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;

public class GenreService {
    private GenreDao genreDao = new GenreDao(DataConnection.getConnection());

    public List<GenreModel> getAllGenres(){
        List<GenreModel> list = genreDao.getAllGenres();

        for (GenreModel genreModel : list) {
            System.out.println(genreModel);
        }


        return genreDao.getAllGenres();
    }

    public GenreModel getGenreModelById(Long id) {
        return genreDao.getGenreById(id);
    }

    public GenreModel getGenreModelByName(String name){
        return genreDao.getGenreByName(name);
    }
}
