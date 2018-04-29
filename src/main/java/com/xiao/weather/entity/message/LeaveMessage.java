package com.xiao.weather.entity.message;

import com.xiao.weather.entity.AbstractEntityBase;
import com.xiao.weather.util.annotation.DbInfo;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户微信留言
 *
 * @author xiao_elevener
 * @date 2018-04-28 21:52
 */
@DbInfo(tableName = "leave_message")
@Data
public class LeaveMessage extends AbstractEntityBase implements Serializable {

    private static final long serialVersionUID = 1049145479751977575L;
    /**
     * 微信openId
     */
    private String openId;

    /**
     * 内容
     */
    private String content;

}
