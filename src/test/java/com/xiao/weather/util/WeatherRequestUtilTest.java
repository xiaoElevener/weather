package com.xiao.weather.util;

import com.alibaba.fastjson.JSON;
import com.xiao.weather.config.XinZhiConfig;
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
    private XinZhiConfig xinZhiConfig;

    @Test
    public void request() throws Exception {
        Map<String, Object> map = new HashMap<>(1);
        map.put("location", "hangzhou");
        XinZhiResultVO<NowWeatherVO> weatherVOXinZhiResultVO = weatherRequestUtil.request(xinZhiConfig.getWeatherApi(), map);
        System.out.println(JSON.toJSONString(weatherVOXinZhiResultVO));
    }

}