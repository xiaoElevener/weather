package com.xiao.weather.controller.user;

import com.xiao.weather.common.so.user.UserSo;
import com.xiao.weather.common.vo.ResultVO;
import com.xiao.weather.common.vo.user.UserVo;
import com.xiao.weather.service.user.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Method;
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

    @PostMapping(value = "/user")
    @ApiOperation("创建用户")
    public ResultVO<String> createUser(@RequestBody UserVo userVo) {
        userService.createUser(userVo);
        return new ResultVO<>();
    }

    @GetMapping(value = "/userList")
    @ApiOperation("获取用户列表")
    public ResultVO<UserVo> listUser(UserSo userSo) {
        ResultVO<UserVo> resultVO = new ResultVO<>();
        resultVO.setVoList(userService.findUserVosBySo(userSo));
        resultVO.setTotal(userService.countByUserSo(userSo));
        return resultVO;
    }


    @PutMapping(value = "/user/{id}")
    public ResultVO<String> updateUser(@PathVariable("id") long id, @RequestBody UserVo userVo) {
        userVo.setId(id);
        userService.updateUser(userVo);
        return new ResultVO<>();
    }

    @DeleteMapping(value = "/user/{id}")
    public ResultVO<String> deleteUser(@PathVariable(value = "id") long id) {
        userService.deleteUser(id);
        return new ResultVO<>();
    }


    @PutMapping(value = "/user/login")
    public ResultVO<UserVo> login(@RequestBody UserVo userVo) {
        UserVo user = userService.login(userVo);
        ResultVO<UserVo> resultVO = new ResultVO<>();
        if (user == null) {
            resultVO.setSuccess(Boolean.FALSE);
            resultVO.setMessage("用户名密码错误!");
        } else {
            resultVO.setVo(userVo);
        }
        return resultVO;
    }


}
