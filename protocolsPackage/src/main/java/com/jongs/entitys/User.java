package com.jongs.entitys;
import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable{
    private static final long serialVersionUID = 1L;
    private int id;
    private String login;
    private String password;
    private USER_POLICY policy;


    public User() {
    }

    public User(int id, String login, String password, USER_POLICY policy) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.policy = policy;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return this.login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public USER_POLICY getPolicy() {
        return this.policy;
    }

    public void setPolicy(USER_POLICY policy) {
        this.policy = policy;
    }

    public User id(int id) {
        setId(id);
        return this;
    }

    public User login(String login) {
        setLogin(login);
        return this;
    }

    public User password(String password) {
        setPassword(password);
        return this;
    }

    public User policy(USER_POLICY policy) {
        setPolicy(policy);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        return id == user.id && Objects.equals(login, user.login) && Objects.equals(password, user.password) && Objects.equals(policy, user.policy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, policy);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", login='" + getLogin() + "'" +
            ", password='" + getPassword() + "'" +
            ", policy='" + getPolicy() + "'" +
            "}";
    }


    public enum USER_POLICY{
        CUSTOMER, EMPLOYEE, ADMIN;
    }
}
