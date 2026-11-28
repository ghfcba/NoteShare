package ru.itis.noteshare.service;

import ru.itis.noteshare.dao.ScoreDao;
import ru.itis.noteshare.dto.ScoreDto;
import ru.itis.noteshare.models.ComposerModel;
import ru.itis.noteshare.models.ScoreModel;
import ru.itis.noteshare.models.UserModel;
import ru.itis.noteshare.utils.Mapper;

import java.util.ArrayList;
import java.util.List;

public class ScoreService {
    private final ScoreDao scoreDao;
    private final ComposerService composerService;

    public ScoreService(ScoreDao scoreDao, ComposerService composerService) {
        this.scoreDao = scoreDao;
        this.composerService = composerService;
    }

    public ScoreModel addScore(UserModel user, ScoreModel score, ComposerModel composer){
        score.setUserId(user.getUserId());
        score.setComposerId(composer.getComposerId());
        return scoreDao.saveScore(score);
    }

    public List<ScoreModel> scorelikeTitle(String title){
        return scoreDao.getTitleLike(title);
    }

    public List<ScoreModel> getAllScores(){
        return scoreDao.getAllNote();
    }

    public List<ScoreDto> getDtoScores(){
        List<ScoreModel> scores = scoreDao.getAllApprovedScores();
        List<ScoreDto> listDto = new ArrayList<>();
        for(ScoreModel scoreModel : scores){
            listDto.add(Mapper.modelToDto(scoreModel, composerService));
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

    public List<ScoreModel> getScoresByComposerId(Long composerId){
        return scoreDao.getScoresByComposerId(composerId);
    }

    public List<ScoreModel> getPendingScores(){
        return scoreDao.getAllPendingScores();
    }
    public List<ScoreModel> getApprovedScores(){
        return scoreDao.getAllApprovedScores();
    }
}
