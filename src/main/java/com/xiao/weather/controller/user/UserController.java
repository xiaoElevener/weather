package com.xiao.weather.controller.user;

import com.xiao.weather.common.so.user.UserSo;
import com.xiao.weather.common.vo.ResultVO;
import com.xiao.weather.common.vo.user.UserVo;
import com.xiao.weather.service.user.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    @ApiOperation("创建用户")
    public ResultVO<String> createUser(@RequestBody UserVo userVo) {
        userService.createUser(userVo);
        return new ResultVO<>();
    }

    @RequestMapping(value = "/userList", method = RequestMethod.GET)
    @ApiOperation("获取用户列表")
    public ResultVO<UserVo> listUser(@RequestBody(required = false) UserSo userSo) {
        if (userSo == null) {
            userSo = new UserSo();
        }
        ResultVO<UserVo> resultVO = new ResultVO<>();
        List<UserVo> userVoList = userService.findUserVosBySo(userSo);
        resultVO.setVoList(userVoList);
        resultVO.setTotal(userVoList.size());
        return resultVO;
    }


    @RequestMapping(value = "/user", method = RequestMethod.PUT)
    public ResultVO<String> updateUser(@RequestBody UserVo userVo) {
        userService.updateUser(userVo);
        return new ResultVO<>();
    }
}
