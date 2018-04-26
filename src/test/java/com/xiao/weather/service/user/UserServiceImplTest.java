package com.xiao.weather.service.user;

import com.xiao.weather.WeatherApplicationTests;
import com.xiao.weather.common.vo.user.UserVo;
import com.xiao.weather.dao.user.UserDao;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

public class UserServiceImplTest extends WeatherApplicationTests{

    @Autowired
    private UserService userService;

    @Autowired
    private UserDao userDao;

    @Test
    public void createUser() throws Exception {
        UserVo user = new UserVo();
        user.setLoginName("BG329474||123");
        user.setUserName("林凌霄");
        user.setPassword("123456");
        user.setSalt("lsxsa");
        user.setTelephone("15797692522");
        userService.createUser(user);
    }

    @Test
    public void deleteUser() throws Exception {
    }

    @Test
    public void login() throws Exception {
    }

    @Test
    public void getLoginNameList() {
        Assert.notNull(userService.getLoginNameList(), "用户登录列表");
    }

}