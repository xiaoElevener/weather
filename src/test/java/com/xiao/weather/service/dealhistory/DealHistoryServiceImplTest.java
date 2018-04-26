package com.xiao.weather.service.dealhistory;

import com.xiao.weather.WeatherApplicationTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

public class DealHistoryServiceImplTest extends WeatherApplicationTests {

    @Autowired
    private DealHistoryService dealHistoryService;

    @Test
    public void create() throws Exception {

    }

    @Test
    public void getDailyStatistical() throws Exception {
        Assert.notNull(dealHistoryService.getDailyStatistical(), "交易统计");
    }
}