package com.xiao.weather.controller.weather;

import com.xiao.weather.common.vo.ResultVO;
import com.xiao.weather.common.vo.weather.NowWeatherVO;
import com.xiao.weather.service.weather.WeatherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Administrator
 * @create 2018-03-22 13:40
 */
@RestController
@RequestMapping(XinZhiController.VIEW_PREFIX)
@Api("天气查询api")
public class XinZhiController {

    public static final String VIEW_PREFIX = "/ajax";

    @Autowired
    private WeatherService weatherService;

    @GetMapping("/nowWeather")
    @ApiOperation(value = "获取系统时间")
    public ResultVO<NowWeatherVO> getNowWeather() {
        ResultVO<NowWeatherVO> resultVO = new ResultVO<>();
        resultVO.setVo(weatherService.getSystemWeather());
        return resultVO;
    }


}
