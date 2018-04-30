package com.xiao.weather.controller.predefinedcode;

import com.xiao.weather.common.so.predefinedcode.PredefinedCodeSo;
import com.xiao.weather.common.vo.ResultVO;
import com.xiao.weather.common.vo.predefinedcode.PredefinedCodeVo;
import com.xiao.weather.service.predefinedcode.PredefinedCodeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 预定义
 *
 * @author xiao_elevener
 * @date 2018-04-30 14:31
 */
@RestController
@Api("预定义api")
@RequestMapping(com.xiao.weather.controller.predefinedcode.PredefinedCodeController.VIEW_PREFIX)
public class PredefinedCodeController {

    public static final String VIEW_PREFIX = "ajax";

    @Autowired
    private PredefinedCodeService predefinedCodeService;

    @PostMapping(value = "/predefinedCode")
    @ApiOperation("创建预定义")
    public ResultVO<String> createPredefinedCode(@RequestBody PredefinedCodeVo predefinedCodeVo) {
        predefinedCodeService.createPredefinedCode(predefinedCodeVo);
        return new ResultVO<>();
    }

    @GetMapping(value = "/predefinedCodeList")
    @ApiOperation("获取预定义列表")
    public ResultVO<PredefinedCodeVo> listPredefinedCode(PredefinedCodeSo predefinedCodeSo) {
        ResultVO<PredefinedCodeVo> resultVO = new ResultVO<>();
        resultVO.setVoList(predefinedCodeService.findPredefinedCodeVosBySo(predefinedCodeSo));
        resultVO.setTotal(predefinedCodeService.countByPredefinedCodeSo(predefinedCodeSo));
        return resultVO;
    }


    @PutMapping(value = "/predefinedCode/{id}")
    @ApiOperation("修改预定义")
    public ResultVO<String> updatePredefinedCode(@PathVariable("id") long id, @RequestBody PredefinedCodeVo predefinedCodeVo) {
        predefinedCodeVo.setId(id);
        predefinedCodeService.updatePredefinedCode(predefinedCodeVo);
        return new ResultVO<>();
    }

    @DeleteMapping(value = "/predefinedCode/{id}")
    @ApiOperation("删除预定义")
    public ResultVO<String> deletePredefinedCode(@PathVariable(value = "id") long id) {
        predefinedCodeService.deletePredefinedCode(id);
        return new ResultVO<>();
    }

}
