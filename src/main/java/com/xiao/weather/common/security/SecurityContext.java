package com.xiao.weather.common.security;


import com.xiao.weather.entity.user.User;
import lombok.Data;

@Data
public class SecurityContext {

    private User user;

}
