package com.xiao.weather.vo;

import lombok.Data;

/**
 * @author Administrator
 * @create 2018-03-20 12:23
 */
@Data
public class ResultVO<T> {
    private T[] results;
}
