package ru.itis.service;

import ru.itis.dao.UserDaoJdbc;
import ru.itis.models.User;

import java.util.List;

/**
 * Created by Kyzlek_pc on 02.11.16.
 */
public class UserServiceImpl implements UserService {

    private UserDaoJdbc userDaoJdbc;

    public UserServiceImpl(UserDaoJdbc userDaoJdbc) {
        this.userDaoJdbc = userDaoJdbc;
    }


    public List<User> getAllUsers(){
        return userDaoJdbc.getAllUsers();
    }

    public User findUserByAccNameAndPass(String accountName, String password){return userDaoJdbc.findUserByAccNameAndPass(accountName, password);}

    public User findUserByToken(String token){return userDaoJdbc.findUserByToken(token); }

    public boolean checkAccountName(String accountName){ return userDaoJdbc.checkAccountName(accountName); }

    public void update(User user) {
        this.userDaoJdbc.update(user);
    }

    public void add(User user){
        userDaoJdbc.add(user);
    }

    public void delete(int id){
        userDaoJdbc.delete(id);
    }

    public void addToken(User user, int token) { userDaoJdbc.addToken(user, token); }

}
