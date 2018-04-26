package com.xiao.weather.controller.core;


import com.xiao.weather.common.exception.BizException;
import com.xiao.weather.common.exception.OptLockException;
import com.xiao.weather.common.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.Serializable;

/**
 * @author xiao_elevener
 */
@Slf4j
@ControllerAdvice
public class AjaxBaseController {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResultVO<Serializable> ajaxException(Exception e) {
        logExceptionDetail(e);

        ResultVO<Serializable> packVo = new ResultVO<>();
        packVo.setSuccess(Boolean.FALSE);

        if (e instanceof OptLockException) {
            packVo.setMessage("此记录已被修改，请刷新后再试");
        } else if (e instanceof BizException) {
            packVo.setMessage(e.getMessage());
        } else if (e instanceof MissingServletRequestParameterException
                || e instanceof NumberFormatException
                || e instanceof TypeMismatchException
                || e instanceof IllegalArgumentException
                || e instanceof SecurityException) {
            packVo.setMessage(e.getMessage());
        } else if (e instanceof UnauthorizedException) {
            packVo.setMessage("身份认证异常，请重新登录");
        } else {
            packVo.setMessage("系统位置错误，请联系管理员处理");
        }
        return packVo;
    }

    /**
     * 如果不是 <code>com.oasis.caption.utils.exception.BizException</code> 要在log中打出来
     *
     * @param e
     */
    private void logExceptionDetail(Throwable e) {
        log.error("Service Exception: {} raise an exception", e);
    }
}
