package com.xiao.weather.controller.dealhistory;

import com.wuwenze.poi.ExcelKit;
import com.xiao.weather.common.excel.bean.DealHistoryExcelBean;
import com.xiao.weather.common.so.dealhistory.DealHistorySo;
import com.xiao.weather.common.vo.ResultVO;
import com.xiao.weather.common.vo.account.AccountVo;
import com.xiao.weather.common.vo.dealhistory.DailyStatisticalVo;
import com.xiao.weather.common.vo.dealhistory.DealHistoryVo;
import com.xiao.weather.common.vo.systemStatistical.SystemStatisticalVo;
import com.xiao.weather.service.dealhistory.DealHistoryService;
import com.xiao.weather.service.statistical.StatisticalService;
import com.xiao.weather.util.dozer.DozerHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author xiao_elevener
 */
@RequestMapping(value = DealHistoryController.VIEW_PREFIX)
@Api("交易记录")
@Controller
public class DealHistoryController {

    public static final String VIEW_PREFIX = "/ajax";

    @Autowired
    private DozerHelper dozer;

    @Autowired
    private DealHistoryService dealHistoryService;

    @Autowired
    private StatisticalService statisticalService;

    @PostMapping("/dealHistory")
    @ApiOperation(value = "创建新的交易")
    @ResponseBody
    public ResultVO<AccountVo> create(@RequestBody DealHistoryVo dealHistoryVo) {
        AccountVo accountVo = dealHistoryService.create(dealHistoryVo);
        return new ResultVO<>(accountVo);
    }

    @GetMapping("/dailyStatistical")
    @ApiOperation(value = "获取每日交易统计数据")
    @ResponseBody
    public ResultVO<DailyStatisticalVo> getDailyStatistical() {
        ResultVO<DailyStatisticalVo> resultVO = new ResultVO<>();
        resultVO.setVoList(dealHistoryService.getDailyStatistical());
        return resultVO;
    }


    @ApiOperation("获取交易记录列表")
    @ResponseBody
    @GetMapping(value = "/dealHistoryList")
    public ResultVO<DealHistoryVo> listDealHistory(DealHistorySo dealHistorySo) {
        ResultVO<DealHistoryVo> resultVO = new ResultVO<>();
        resultVO.setVoList(dealHistoryService.findDealHistoryVosBySo(dealHistorySo));
        resultVO.setTotal(dealHistoryService.countByDealHistorySo(dealHistorySo));
        return resultVO;
    }


    @ApiOperation("获取微信/交易统计信息")
    @ResponseBody
    @GetMapping(value = "/systemStatistical")
    public ResultVO<SystemStatisticalVo> getStatisticalVo() {
        ResultVO<SystemStatisticalVo> resultVO = new ResultVO<>();
        resultVO.setVo(statisticalService.getSystemStatisticalVo());
        return resultVO;
    }

    @GetMapping(value = "/dealHistory/export")
    public void export(HttpServletResponse response, DealHistorySo dealHistorySo) {
        List<DealHistoryVo> dealHistoryVoList = dealHistoryService.findDealHistoryVosBySo(dealHistorySo);
        ExcelKit.$Export(DealHistoryExcelBean.class, response).toExcel(dozer.convertList(dealHistoryVoList, DealHistoryExcelBean.class), "交易记录");
    }
}
