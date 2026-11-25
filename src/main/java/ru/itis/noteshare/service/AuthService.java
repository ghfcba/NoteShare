package ru.itis.noteshare.service;

import ru.itis.noteshare.dao.UserDao;
import ru.itis.noteshare.models.UserModel;
import ru.itis.noteshare.utils.DataConnection;
import ru.itis.noteshare.utils.HashUtil;

public class AuthService {
    private UserDao userDao = new UserDao(DataConnection.getConnection());

    public boolean registerUser() {
        return true;
    }

//проверять есть ли юзер в бд
    public boolean uniqueUsername(String username){
        if (userDao.getUserByUsername(username) == null) {
            return true;
        }
        return false;
    }

    public boolean uniqueEmail(String email){
        if (userDao.getUserByEmail(email) == null){
            return true;
        }
        return false;
    }
    public boolean checkPass(String password1, String password2){
        return password1.equals(password2);
    }

    public void passHash(UserModel userModel){
        userModel.setPasswordHash(HashUtil.hashPassword(userModel.getPasswordHash()));
    }

    public boolean registerUser(UserModel user){
        if (!uniqueEmail(user.getEmail())){
            return false;
        }
        if (!uniqueUsername(user.getUsername())){
            return false;
        }

        passHash(user);

        userDao.saveUser(user);

        return true;
    }
}
