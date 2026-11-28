package ru.itis.noteshare.service;

import ru.itis.noteshare.dao.ComposerDao;
import ru.itis.noteshare.models.ComposerModel;

import java.util.List;

public class ComposerService {
    private final ComposerDao composerDao;

    public ComposerService(ComposerDao composerDao) {
        this.composerDao = composerDao;
    }

    public boolean checkComposer(ComposerModel composer){
        ComposerModel compFromDb = composerDao.getCompByFullName(composer.getFullName());

        if (compFromDb == null) {
            return false;
        }

        return composer.getFullName().toLowerCase().equals(compFromDb.getFullName().toLowerCase());
    }

    public ComposerModel getComposerByName(String fullname) {
        return composerDao.getCompByFullName(fullname);
    }

    public String compNameUpqrade(String fullName){
        String[] splitName = fullName.split(" ");
        for(String str : splitName){
            str = str.substring(0,1).toUpperCase()+str.substring(1);
        }
        String string = "";
        for(String s:splitName){
            string += s + " ";
        }
        string.strip();
        return string;
    }

    public boolean saveComposer(ComposerModel composer){
        if((checkComposer(composer))){
            return false;
        }
        String fullName = compNameUpqrade(composer.getFullName());
        composer.setFullName(fullName);

        System.out.println(composer);

        composerDao.saveComposer(composer);

        return true;
    }

    public ComposerModel getComposerById(Long composerId){
        return composerDao.getCompById(composerId);
    }


    public List<ComposerModel> getAllComposers(){
        return composerDao.getAllComposers();
    }

}
