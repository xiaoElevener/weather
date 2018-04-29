package com.xiao.weather.service.message;

import com.xiao.weather.WeatherApplicationTests;
import com.xiao.weather.common.so.message.LeaveMessageSo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;


public class LeaveMessageServiceImplTest extends WeatherApplicationTests {

    @Autowired
    private LeaveMessageService leaveMessageService;

    @Test
    public void findLeaveMessagesBySo() {
        LeaveMessageSo leaveMessageSo = new LeaveMessageSo();
        Assert.notNull(leaveMessageService.findLeaveMessagesBySo(leaveMessageSo), "留言分页查询");
    }
}