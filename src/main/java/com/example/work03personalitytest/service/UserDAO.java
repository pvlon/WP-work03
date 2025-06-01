package com.example.work03personalitytest.service;

import java.util.List;

public interface UserDAO {
    int insertUser(UserDO user) throws Exception;
    int deleteUser(UserDO user) throws Exception;
    UserDO readUser(UserDO user) throws Exception;
}
