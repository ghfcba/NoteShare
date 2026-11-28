package ru.itis.noteshare.service;

import ru.itis.noteshare.dao.UserDao;
import ru.itis.noteshare.models.UserModel;
import ru.itis.noteshare.utils.HashUtil;
import ru.itis.noteshare.validation.ValidationException;
import ru.itis.noteshare.validation.Validator;

public class AuthService {
    private final UserDao userDao;

    public AuthService(UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean uniqueUsername(String username) {
        return userDao.getUserByUsername(username) == null;
    }

    public boolean uniqueEmail(String email) {
        return userDao.getUserByEmail(email) == null;
    }

    public void passHash(UserModel userModel) {
        userModel.setPasswordHash(HashUtil.hashPassword(userModel.getPasswordHash()));
    }

    public void registerUser(String username, String email, String password, String passwordConfirm) {
        String cleanUsername = Validator.requireText(username, "Имя пользователя", 50);
        String cleanEmail = Validator.requireEmail(email);
        Validator.requirePassword(password);

        if (passwordConfirm == null || !password.equals(passwordConfirm)) {
            throw new ValidationException("Пароли не совпадают");
        }

        if (!uniqueEmail(cleanEmail)) {
            throw new ValidationException("Пользователь с таким email уже существует");
        }

        if (!uniqueUsername(cleanUsername)) {
            throw new ValidationException("Пользователь с таким именем уже существует");
        }

        UserModel user = new UserModel(cleanUsername, cleanEmail, password);
        passHash(user);
        userDao.saveUser(user);
    }

    public UserModel getUserByEmail(String email) {
        return userDao.getUserByEmail(email);
    }

    public boolean loginUser(String email, String password) {
        UserModel user = userDao.getUserByEmail(email);

        if (user == null) {
            return false;
        }

        return HashUtil.verify(password, user.getPasswordHash());
    }
}
