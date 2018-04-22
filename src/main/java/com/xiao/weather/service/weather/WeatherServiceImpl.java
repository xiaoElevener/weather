package com.xiao.weather.service.weather;

import com.xiao.weather.common.constant.XinZhiProperties;
import com.xiao.weather.entity.location.WechatLocation;
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
    private XinZhiProperties xinZhiProperties;

    @Override
    public String getNowWeather(WechatLocation wechatLocation) {
        Map<String, Object> map = new HashMap<>(1);
        map.put("location", getCityNameByLocation(wechatLocation));
        XinZhiResultVO<NowWeatherVO> vo = weatherRequestUtil.request(xinZhiProperties.getWeatherApi(), map);
        if (vo.getResults() == null || vo.getResults().length == 0) {
            return null;
        }
        return changeWeatherVoToWords(vo.getResults()[0]);
    }

    /**
     * 将天气信息展示为中文
     *
     * @param nowWeatherVO
     * @return
     */
    public String changeWeatherVoToWords(NowWeatherVO nowWeatherVO) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("位置:" + nowWeatherVO.getLocation().getName() + "\n" + "天气:" + nowWeatherVO.getNow().getText() + "\n" + "气温:" + nowWeatherVO.getNow().getTemperature());
        return stringBuilder.toString();
    }

    public String getCityNameByLocation(WechatLocation wechatLocation) {
        return weatherRequestUtil.getCityNameByLocation(wechatLocation.getLongitude() + "," + wechatLocation.getLatitude());
    }


}
