package ru.itis.noteshare.service;

import jakarta.servlet.http.Part;
import ru.itis.noteshare.dao.ScoreDao;
import ru.itis.noteshare.dto.ScoreDto;
import ru.itis.noteshare.models.ComposerModel;
import ru.itis.noteshare.models.ScoreGenreModel;
import ru.itis.noteshare.models.ScoreModel;
import ru.itis.noteshare.models.UserModel;
import ru.itis.noteshare.utils.DataConnection;
import ru.itis.noteshare.utils.Mapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ScoreService {
    private ScoreDao scoreDao;
    private static final String dir = "C:\\Users\\MSI\\IdeaProjects\\NoteShare\\src\\main\\webapp\\assets\\scores";

    public ScoreService() {
        this(new ScoreDao(DataConnection.getConnection()));
    }

    public ScoreService(ScoreDao scoreDao) {
        this.scoreDao = scoreDao;
    }

    public ScoreModel addScore(UserModel user, ScoreModel score, ComposerModel composer){
        score.setUserId(user.getUserId());
        score.setComposerId(composer.getComposerId());
        return scoreDao.saveScore(score);
    }

    public boolean loadFile(Part part, String fileName) {
        File directory = new File(dir);

        if (!directory.exists()) {
            directory.mkdir();
        }

        try {
            part.write(dir + File.separator + fileName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return true;
    }
    public List<ScoreModel> scorelikeTitle(String title){
        return scoreDao.getTitleLike(title);
    }

    public List<ScoreModel> getAllScores(){
        return scoreDao.getAllNote();
    }

    public List<ScoreDto> getDtoScores(){
        List<ScoreModel> scores = scoreDao.getAllNote();
        List<ScoreDto> listDto = new ArrayList<>();
        for(ScoreModel scoreModel : scores){
            listDto.add(Mapper.modelToDto(scoreModel));
        }
        return listDto;
    }

    public void updateScoreStatus(ScoreModel scoreModel){
        scoreDao.updateScoreStatus(scoreModel);
    }

    public void deleteScore(ScoreModel scoreModel){
        scoreDao.deleteScore(scoreModel);
    }
    public ScoreModel getScoreById(Long scoreId){
        return scoreDao.getScoreById(scoreId);
    }
    public List<ScoreModel> getPendingScores(){
        return scoreDao.getAllPendingScores();
    }
}
