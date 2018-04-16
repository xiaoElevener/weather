package com.xiao.weather.controller.weather;

import com.xiao.weather.config.XinZhiConfig;
import com.xiao.weather.util.WeatherRequestUtil;
import com.xiao.weather.common.vo.ResultVO;
import com.xiao.weather.common.vo.weather.NowWeatherVO;
import com.xiao.weather.common.vo.weather.XinZhiResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import static java.lang.System.out;

/**
 * @author Administrator
 * @create 2018-03-22 13:40
 */
@RestController
public class XinZhiController {

    @Autowired
    private XinZhiConfig xinZhiConfig;

    @Autowired
    private WeatherRequestUtil weatherRequestUtil;

    @GetMapping("/config")
    public XinZhiConfig getConfig(){
        out.println(xinZhiConfig);
        return xinZhiConfig;
    }

    @GetMapping("/nowWeather")
    public ResultVO<XinZhiResultVO> getNowWeather(@RequestParam String location){
        Map<String,Object> map = new HashMap<>(1);
        map.put("location",location);
        ResultVO<XinZhiResultVO> resultVO = new ResultVO<>();
        resultVO.setVo(weatherRequestUtil.request(xinZhiConfig.getWeatherApi(), map, NowWeatherVO.class));
        return resultVO;
    }

}
