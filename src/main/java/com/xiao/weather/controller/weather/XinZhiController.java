package com.xiao.weather.controller.weather;

import com.xiao.weather.common.constant.XinZhiProperties;
import com.xiao.weather.util.WeatherRequestUtil;
import com.xiao.weather.common.vo.ResultVO;
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
    private XinZhiProperties xinZhiProperties;

    @Autowired
    private WeatherRequestUtil weatherRequestUtil;

    @GetMapping("/config")
    public XinZhiProperties getConfig(){
        out.println(xinZhiProperties);
        return xinZhiProperties;
    }

    @GetMapping("/nowWeather")
    public ResultVO<XinZhiResultVO> getNowWeather(@RequestParam String location){
        Map<String,Object> map = new HashMap<>(1);
        map.put("location",location);
        ResultVO<XinZhiResultVO> resultVO = new ResultVO<>();
        resultVO.setVo(weatherRequestUtil.request(xinZhiProperties.getWeatherApi(), map));
        return resultVO;
    }

}
