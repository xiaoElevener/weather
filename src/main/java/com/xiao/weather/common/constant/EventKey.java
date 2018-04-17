package com.xiao.weather.common.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EventKey {
    NOW_WEATHER("nowWeather");

    String eventKey;
}
