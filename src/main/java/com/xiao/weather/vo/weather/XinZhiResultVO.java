package com.xiao.weather.vo.weather;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Administrator
 * @create 2018-03-20 12:23
 */
@Data
public class XinZhiResultVO<T> implements Serializable{
    private T[] results;
}
