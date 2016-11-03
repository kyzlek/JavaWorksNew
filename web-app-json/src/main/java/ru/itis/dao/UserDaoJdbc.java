package ru.itis.dao;

import ru.itis.models.User;

import java.util.List;

/**
 * Created by Kyzlek_pc on 02.11.16.
 */
public interface UserDaoJdbc {

    List<User> getAllUsers();
    User findUserByAccNameAndPass(String accountName, String password);
    User findUserByToken(String token);
    boolean checkAccountName(String accountName);

    void update(User user);
    void add(User user);
    void delete(int id);
    void addToken(User user, int token);

}
