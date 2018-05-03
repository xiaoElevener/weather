package com.xiao.weather.controller.dealhistory;

import com.xiao.weather.common.so.dealhistory.DealHistorySo;
import com.xiao.weather.common.vo.ResultVO;
import com.xiao.weather.common.vo.dealhistory.DailyStatisticalVo;
import com.xiao.weather.common.vo.dealhistory.DealHistoryVo;
import com.xiao.weather.common.vo.systemStatistical.SystemStatisticalVo;
import com.xiao.weather.dao.user.UserDao;
import com.xiao.weather.service.dealhistory.DealHistoryService;
import com.xiao.weather.service.statistical.StatisticalService;
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

    @Autowired
    private StatisticalService statisticalService;

    @PostMapping("/dealHistory")
    @ApiOperation(value = "创建新的交易")
    public ResultVO<String> create(@RequestBody DealHistoryVo dealHistoryVo) {
        dealHistoryService.create(dealHistoryVo);
        return new ResultVO<>();
    }

    @GetMapping("/dailyStatistical")
    @ApiOperation(value = "获取每日交易统计数据")
    public ResultVO<DailyStatisticalVo> getDailyStatistical() {
        ResultVO<DailyStatisticalVo> resultVO = new ResultVO<>();
        resultVO.setVoList(dealHistoryService.getDailyStatistical());
        return resultVO;
    }

    @GetMapping(value = "/dealHistoryList")
    @ApiOperation("获取交易记录列表")
    public ResultVO<DealHistoryVo> listDealHistory(DealHistorySo dealHistorySo) {
        ResultVO<DealHistoryVo> resultVO = new ResultVO<>();
        resultVO.setVoList(dealHistoryService.findDealHistoryVosBySo(dealHistorySo));
        resultVO.setTotal(dealHistoryService.countByDealHistorySo(dealHistorySo));
        return resultVO;
    }

    @GetMapping(value = "/systemStatistical")
    public ResultVO<SystemStatisticalVo> getStatisticalVo() {
        ResultVO<SystemStatisticalVo> resultVO = new ResultVO<>();
        resultVO.setVo(statisticalService.getSystemStatisticalVo());
        return resultVO;
    }
}
