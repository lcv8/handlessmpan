package javases.handleSSM.APP.service.impl;

import javases.handleSSM.APP.dao.UserDao;
import javases.handleSSM.APP.service.UserService;

public class UserviceImpl implements UserService {

    private UserDao userDao;

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
