package com.xiao.weather.controller.wechat;

import com.xiao.weather.common.vo.ResultVO;
import com.xiao.weather.service.wechat.messageHandler.MessageHandlerCenter;
import com.xiao.weather.service.wechat.sns.SnsService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import weixin.popular.bean.message.EventMessage;
import weixin.popular.util.XMLConverUtil;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * @author xiao_elevener
 * @date 2018-03-24 17:04
 */
@Controller
@Slf4j
@Api(value = "WechatController", description = "微信接口")
public class WechatController {

    @Autowired
    private MessageHandlerCenter messageHandlerCenter;

    @Autowired
    private SnsService snsService;

    private final String SUCCESS = "success";

    @PostMapping("/wechat")
    public void responseToWechat(@RequestBody String content, HttpServletResponse response) throws IOException {
        log.info("receive={}", content);
        EventMessage eventMessage = XMLConverUtil.convertToObject(EventMessage.class, content);
        EventMessage responseMessage = messageHandlerCenter.handleEventMessage(eventMessage);
        printResponse(response.getWriter(), responseMessage);
    }

    @GetMapping("/wechat")
    @ResponseBody
    public String check(@RequestParam String echostr) {
        return echostr;
    }


    private void printResponse(PrintWriter writer, EventMessage responseMessage) {
        String response = responseMessage == null ? SUCCESS : XMLConverUtil.convertToXML(responseMessage);
        log.info("response={}", response);
        writer.print(response);
        writer.close();
    }

    @ResponseBody
    @GetMapping("/openId")
    public ResultVO<String> getOpenId(String code) {
        return new ResultVO<>(snsService.getOpenId(code));
    }

}
