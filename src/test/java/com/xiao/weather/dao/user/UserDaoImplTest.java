package com.xiao.weather.dao.user;

import com.xiao.weather.WeatherApplicationTests;
import com.xiao.weather.entity.user.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import static org.junit.Assert.*;

public class UserDaoImplTest extends WeatherApplicationTests {

    @Autowired
    private UserDao userDao;

    @Test
    public void insert(){
        User user = new User();
        user.setLoginName("BG329474");
        user.setUserName("林凌霄");
        user.setPassword("123456");
        user.setSalt("lsxsa");
        user.setTelephone("15797692522");
        userDao.insert(user);
        Assert.notNull(userDao.find(1L),"用户创建失败");
    }

}