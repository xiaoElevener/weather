package com.xiao.weather.common.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author Administrator
 * @create 2018-03-22 14:24
 */
@Data
@NoArgsConstructor
public class ResultVO<T extends Serializable> implements Serializable  {

    /**
     * 调用是否成功
     */
    private Boolean success = Boolean.TRUE;

    /**
     * 错误及提示
     */
    private String message;

    /**
     * 返回对象
     */
    private T vo;

    /**
     * 返回列表
     */
    private List<T> voList;

    /**
     * 列表的数量
     */
    private Integer total;

    public ResultVO(T vo){
        this.vo = vo;
    }


    public ResultVO(List<T> voList){
        this.voList = voList;
    }
}
