package com.xiao.weather.service.weather;

import com.xiao.weather.common.constant.XinZhiProperties;
import com.xiao.weather.common.exception.BizException;
import com.xiao.weather.common.vo.weather.NowWeatherVO;
import com.xiao.weather.common.vo.weather.XinZhiResultVO;
import com.xiao.weather.dao.predefinedcode.PredefinedCodeDao;
import com.xiao.weather.entity.location.WechatLocation;
import com.xiao.weather.entity.predefinedcode.PredefinedCode;
import com.xiao.weather.util.WeatherRequestUtil;
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

    private final String LOCATION = "location";

    @Autowired
    private WeatherRequestUtil weatherRequestUtil;

    @Autowired
    private XinZhiProperties xinZhiProperties;

    @Autowired
    private PredefinedCodeDao predefinedCodeDao;

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

    @Override
    public NowWeatherVO getSystemWeather() {
        PredefinedCode predefinedCode = predefinedCodeDao.findByCode(LOCATION);
        String location = predefinedCode.getValue();
        Map<String, Object> map = new HashMap<>(1);
        map.put("location", location);
        XinZhiResultVO<NowWeatherVO> vo = weatherRequestUtil.request(xinZhiProperties.getWeatherApi(), map);
        if (vo.getResults() == null || vo.getResults().length == 0) {
            throw new BizException("系统地理位置配置错误！");
        }
        return vo.getResults()[0];
    }

    /**
     * 将天气信息展示为中文
     *
     * @param nowWeatherVO
     * @return
     */
    private String changeWeatherVoToWords(NowWeatherVO nowWeatherVO) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("位置:" + nowWeatherVO.getLocation().getName() + "\n" + "天气:" + nowWeatherVO.getNow().getText() + "\n" + "气温:" + nowWeatherVO.getNow().getTemperature());
        return stringBuilder.toString();
    }

    private String getCityNameByLocation(WechatLocation wechatLocation) {
        return weatherRequestUtil.getCityNameByLocation(wechatLocation.getLongitude() + "," + wechatLocation.getLatitude());
    }


}
