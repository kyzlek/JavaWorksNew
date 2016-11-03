package ru.itis.service;

import ru.itis.models.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User findUserByAccNameAndPass(String accountName, String password);
    User findUserByToken(String token);
    boolean checkAccountName(String accountName);

    void update(User user);
    void add(User user);
    void delete(int id);
    void addToken(User user, int token);
}
