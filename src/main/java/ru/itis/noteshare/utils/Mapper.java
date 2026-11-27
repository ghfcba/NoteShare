package ru.itis.noteshare.utils;

import ru.itis.noteshare.dto.ScoreDto;
import ru.itis.noteshare.models.ScoreModel;
import ru.itis.noteshare.service.ComposerService;

public class Mapper {
    private static ComposerService composerService = new ComposerService();
    public static ScoreDto modelToDto(ScoreModel scoreModel) {
        ScoreDto scoreDto = ScoreDto.builder()
                .title(scoreModel.getTitle())
                .composerName(composerService.getComposerById(scoreModel.getComposerId()).getFullName())
                .difficulity(scoreModel.getDifficulty())
                .date(scoreModel.getUploadedAt().toLocalDateTime().toLocalDate())
                .build();

        return scoreDto;

    }
}
