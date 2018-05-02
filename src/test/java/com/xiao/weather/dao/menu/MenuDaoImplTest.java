package com.xiao.weather.dao.menu;

import com.google.common.collect.Lists;
import com.xiao.weather.WeatherApplicationTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;


public class MenuDaoImplTest extends WeatherApplicationTests {

    @Autowired
    private MenuDao menuDao;


    @Test
    public void test() {
        Assert.notNull(menuDao.find(1L), "非空");
    }

    @Test
    public void findPaths() {
        menuDao.findPaths(Lists.newArrayList(new Long(1)));
    }
}