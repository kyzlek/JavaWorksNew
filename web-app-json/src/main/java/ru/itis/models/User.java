package ru.itis.models;

import com.google.common.base.MoreObjects;
import ru.itis.utils.HashGenerator;

/**
 * Created by Kyzlek_pc on 02.11.16.
 */
public class User {

    private int userId;
    private String userName;
    private String accountName;
    private String hasCode;
    private int token;


    public User(int userId, String userName, String accountName, String password) {
        this.userId = userId;
        this.userName = userName;
        this.accountName = accountName;
        this.hasCode = HashGenerator.md5ApacheHash(password);
        this.token = 0;
    }

    public int getId() {
        return userId;
    }

    public String getName() {
        return userName;
    }

    public String getAccountName() {return accountName;}

    public String getHashCode() {return hasCode;}

    public int getToken() {return token;}

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", this.userId)
                .add("name", this.userName)
                .add("accountName", this.accountName)
                .add("hashCode", this.hasCode)
                .add("token", this.token)
                .toString();
    }

}
