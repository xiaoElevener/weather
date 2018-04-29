package com.xiao.weather.controller.message;

import com.xiao.weather.common.so.message.LeaveMessageSo;
import com.xiao.weather.common.vo.ResultVO;
import com.xiao.weather.common.vo.message.LeaveMessageVo;
import com.xiao.weather.service.message.LeaveMessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiao_elevener
 */
@RestController
@Api("留言")
@RequestMapping(LeaveMessageController.VIEW_PREFIX)
public class LeaveMessageController {

    public static final String VIEW_PREFIX = "ajax";

    @Autowired
    private LeaveMessageService leaveMessageService;

    @GetMapping(value = "/messageList")
    @ApiOperation("获取留言列表")
    public ResultVO<LeaveMessageVo> listUser(LeaveMessageSo leaveMessageSo) {
        ResultVO<LeaveMessageVo> resultVO = new ResultVO<>();
        resultVO.setVoList(leaveMessageService.findLeaveMessagesBySo(leaveMessageSo));
        resultVO.setTotal(leaveMessageService.countByLeaveMessageSo(leaveMessageSo));
        return resultVO;
    }

}
