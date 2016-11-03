package ru.itis.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import ru.itis.models.User;
import ru.itis.utils.HashGenerator;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Kyzlek_pc on 02.11.16.
 */
public class UserDaoJdbcImpl implements UserDaoJdbc {

    // language=SQL
    public static final String SQL_SELECT_ALL_USERS = "SELECT *  FROM users;";
    // language=SQL
    public static final String SQL_ADD_USER = "INSERT INTO users( user_name, account_name, hash_code, token) VALUES (:userName, :accountName, :hashCode, :token);";
    // language=SQL
    public static final String SQL_UPDATE_USER = "UPDATE users SET user_id = :userId, user_name = :userName,  account_name = :accountName WHERE user_id = :userId ;";
    // language=SQL
    public static final String SQL_DELETE_USER = "DELETE FROM users WHERE user_id = :userId;";
    // language=SQL
    public static final String SQL_ADD_TOKEN = "UPDATE users SET token = :token WHERE user_id = :userId;";
    // language=SQL
    public static final String SQL_FIND_TOKEN = "SELECT *  FROM users WHERE token = :token;";
    // language=SQL
    public static final String SQL_FIND_USER_BY_ACCNAME_AND_PASS = "SELECT *  FROM users WHERE account_name = :accountName AND hash_code = :hashCode;";
    // language=SQL
    public static final String SQL_CHECK_ACCOUNT = "SELECT EXISTS (SELECT * FROM users WHERE account_name = :accountName) checkAccount";

    private NamedParameterJdbcTemplate namedJdbcTemplate;

    public UserDaoJdbcImpl(DataSource dataSource) {

        this.namedJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public List<User> getAllUsers() {

        return this.namedJdbcTemplate.query(
                SQL_SELECT_ALL_USERS,
                new RowMapper<User>() {

                    public User mapRow(ResultSet resultSet, int i) throws SQLException {
                        return new User(resultSet.getInt("user_id"), resultSet.getString("user_name"), resultSet.getString("account_name"), resultSet.getString("hash_code"));
                    }
                }
        );

    }

    public User findUserByAccNameAndPass(String accountName, String password) {

        Map<String, Object> paramsMap = new HashMap<String, Object>();
        paramsMap.put("accountName", accountName);
        paramsMap.put("hashCode", HashGenerator.md5ApacheHash(password));
        User user = this.namedJdbcTemplate.queryForObject(
                SQL_FIND_USER_BY_ACCNAME_AND_PASS,
                paramsMap,
                new RowMapper<User>() {

                    public User mapRow(ResultSet resultSet, int i) throws SQLException {
                        return new User(resultSet.getInt("user_id"), resultSet.getString("user_name"), resultSet.getString("account_name"), resultSet.getString("hash_code"));
                    }
        });

        if(user != null){
            return user;
        }
        else
            return null;
    }

    public User findUserByToken(String token) {

        Map<String, Object> paramsMap = new HashMap<String, Object>();
        paramsMap.put("token", Integer.parseInt(token));
        User user = this.namedJdbcTemplate.queryForObject(
                SQL_FIND_TOKEN,
                paramsMap,
                new RowMapper<User>() {

                    public User mapRow(ResultSet result, int rowNum) throws SQLException {
                        return new User(result.getInt("user_id"), result.getString("user_name"), result.getString("account_name"), result.getString("hash_code"));
                    }
                });
        if(user != null) return user;
        else
            return null;
    }

    public boolean checkAccountName(String accountName) {

        Map<String, Object> paramsMap = new HashMap<String, Object>();
        paramsMap.put("accountName", accountName);
        List<Boolean> listBool = this.namedJdbcTemplate.query(
                SQL_CHECK_ACCOUNT,
                paramsMap,
                new RowMapper<Boolean>() {
                    public Boolean mapRow(ResultSet resultSet, int i) throws SQLException {

                        if(resultSet.getString("checkAccount").equals("t")){
                            return new Boolean(true);
                        }
                        else return new Boolean(false);
                    }
        });

        if(listBool.get(0).booleanValue()){
            return true;
        }
        else return false;
    }

    public void add(User user) {

        Map<String, Object> paramsMap = new HashMap<String, Object>();
        paramsMap.put("userName", user.getName());
        paramsMap.put("accountName", user.getAccountName());
        paramsMap.put("hashCode", user.getHashCode());
        paramsMap.put("token", user.getToken());
        this.namedJdbcTemplate.update(
                SQL_ADD_USER, paramsMap
        );
    }

    public void update(User user) {

        Map<String, Object> paramsMap = new HashMap<String, Object>();
        paramsMap.put("userId", user.getId());
        paramsMap.put("accountName", user.getAccountName());
        paramsMap.put("userName", user.getName());
        this.namedJdbcTemplate.update(
                SQL_UPDATE_USER, paramsMap
        );
    }

    public void delete(int id) {

        Map<String, Object> paramsMap = new HashMap<String, Object>();
        paramsMap.put("userId",id);
        this.namedJdbcTemplate.update(
                SQL_DELETE_USER,paramsMap
        );
    }

    public void addToken(User user, int token) {

        Map<String, Object> paramsMap = new HashMap<String, Object>();
        paramsMap.put("token", token);
        paramsMap.put("userId", user.getId());
        this.namedJdbcTemplate.update(
                SQL_ADD_TOKEN, paramsMap
        );
    }

}
