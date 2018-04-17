package com.xiao.weather.controller.user;

import com.xiao.weather.common.vo.ResultVO;
import com.xiao.weather.common.vo.user.UserVo;
import com.xiao.weather.service.user.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiao_elevener
 */
@RestController
@Api("用户api")
@RequestMapping(UserController.VIEW_PREFIX)
public class UserController {

    public static final String VIEW_PREFIX = "ajax";

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/user",method = RequestMethod.POST)
    @ApiOperation("创建用户")
    public ResultVO<String> createUser(UserVo userVo){
        userService.createUser(userVo);
        return  new ResultVO<>();
    }

}
