package ru.itis.noteshare.service;

import ru.itis.noteshare.dao.ComposerDao;
import ru.itis.noteshare.models.ComposerModel;
import ru.itis.noteshare.utils.DataConnection;

public class ComposerService {
    public ComposerDao composerDao = new ComposerDao(DataConnection.getConnection());

    public boolean checkComposer(ComposerModel composer){
        ComposerModel compFromDb = composerDao.getCompByFullName(composer.getFullName());

        if (compFromDb == null) {
            return false;
        }

        return composer.getFullName().toLowerCase().equals(compFromDb.getFullName().toLowerCase());
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
        string.trim();
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
}
