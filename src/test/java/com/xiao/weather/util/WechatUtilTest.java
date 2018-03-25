package com.xiao.weather.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WechatUtilTest {

    @Autowired
    private WechatUtil wechatUtil;

    @Test
    public void getAccessToken() throws Exception {
        System.out.println(wechatUtil.getAccessToken());
    }

    @Test
    public void createMenu() {
        wechatUtil.createMenu();
    }

    @Test
    public void getMenu() {

    }

}