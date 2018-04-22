package com.xiao.weather.util;

import com.alibaba.fastjson.JSON;
import com.xiao.weather.common.constant.XinZhiProperties;
import com.xiao.weather.common.vo.weather.NowWeatherVO;
import com.xiao.weather.common.vo.weather.XinZhiResultVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WeatherRequestUtilTest {

    @Autowired
    private WeatherRequestUtil weatherRequestUtil;

    @Autowired
    private XinZhiProperties xinZhiProperties;

    @Test
    public void request() throws Exception {
        Map<String, Object> map = new HashMap<>(1);
        map.put("location", weatherRequestUtil.getCityNameByLocation("120.102249,30.276270"));
        XinZhiResultVO<NowWeatherVO> weatherVOXinZhiResultVO = weatherRequestUtil.request(xinZhiProperties.getWeatherApi(), map);
        System.out.println(JSON.toJSONString(weatherVOXinZhiResultVO));
    }

    @Test
    public void getCityNameByLocation(){
        String location = "120.102249,30.276270";
        System.out.println(weatherRequestUtil.getCityNameByLocation(location));
    }

}