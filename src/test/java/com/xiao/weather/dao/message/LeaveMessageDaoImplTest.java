package com.xiao.weather.dao.message;

import com.xiao.weather.WeatherApplicationTests;
import com.xiao.weather.entity.message.LeaveMessage;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;


public class LeaveMessageDaoImplTest extends WeatherApplicationTests {

    @Autowired
    private LeaveMessageDao leaveMessageDao;

    @Test
    public void insert() {
        LeaveMessage leaveMessage = new LeaveMessage();
        leaveMessage.setOpenId("123456");
        leaveMessage.setContent("我留了个言");
        Assert.notNull(leaveMessageDao.insert(leaveMessage), "插入失败");
    }

}