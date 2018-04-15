package com.xiao.weather.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class WeatherServiceImplTest {

    @Autowired
    private WeatherService weatherService;

    @Test
    public void getNowWeather() throws Exception {
        System.out.println(weatherService.getNowWeather("hangzhou"));
    }

}