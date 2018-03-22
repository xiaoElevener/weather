package com.xiao.weather.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Administrator
 * @create 2018-03-22 14:24
 */
@Data
public class ResultVO<T extends Serializable> implements Serializable  {

    /**
     * 调用是否成功
     */
    private Boolean success = Boolean.TRUE;

    /**
     * 错误及提示
     */
    private List<String> messages;

    /**
     * 返回对象
     */
    private T vo;

    /**
     * 返回列表
     */
    private List<T> voList;
}
