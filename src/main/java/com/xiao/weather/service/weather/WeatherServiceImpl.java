package com.xiao.weather.service.weather;

import com.xiao.weather.config.XinZhiConfig;
import com.xiao.weather.util.WeatherRequestUtil;
import com.xiao.weather.common.vo.weather.NowWeatherVO;
import com.xiao.weather.common.vo.weather.XinZhiResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xiao_elevener
 * @date 2018-03-25 0:23
 */
@Service
public class WeatherServiceImpl implements WeatherService {

    @Autowired
    private WeatherRequestUtil weatherRequestUtil;

    @Autowired
    private XinZhiConfig xinZhiConfig;

    @Override
    public String getNowWeather(String location) {
        Map<String, Object> map = new HashMap<>(1);
        map.put("location", location);
        XinZhiResultVO<NowWeatherVO> vo = weatherRequestUtil.request(xinZhiConfig.getWeatherApi(), map);
        if (vo.getResults() == null || vo.getResults().length == 0) {
            return null;
        }
        return vo.getResults()[0].toString();
    }


}
