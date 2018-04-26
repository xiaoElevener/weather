package com.xiao.weather.controller.dealhistory;

import com.xiao.weather.common.vo.ResultVO;
import com.xiao.weather.common.vo.dealhistory.DailyStatisticalVo;
import com.xiao.weather.common.vo.dealhistory.DealHistoryVo;
import com.xiao.weather.service.dealhistory.DealHistoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiao_elevener
 */
@RestController
@RequestMapping(value = DealHistoryController.VIEW_PREFIX)
@Api("交易记录")
public class DealHistoryController {

    public static final String VIEW_PREFIX = "/ajax";

    @Autowired
    private DealHistoryService dealHistoryService;

    @PostMapping("/dealHistory")
    @ApiOperation(value = "创建新的交易")
    public ResultVO<String> create(@RequestBody DealHistoryVo dealHistoryVo) {
        dealHistoryService.create(dealHistoryVo);
        return new ResultVO<>();
    }

    @GetMapping("/dailyStatistical")
    public ResultVO<DailyStatisticalVo> getDailyStatistical() {
        ResultVO<DailyStatisticalVo> resultVO = new ResultVO<>();
        resultVO.setVoList(dealHistoryService.getDailyStatistical());
        return resultVO;
    }
}
