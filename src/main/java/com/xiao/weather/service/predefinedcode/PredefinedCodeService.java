package com.xiao.weather.service.predefinedcode;

import com.xiao.weather.common.so.predefinedcode.PredefinedCodeSo;
import com.xiao.weather.common.vo.predefinedcode.PredefinedCodeVo;

import java.util.List;

/**
 * @author xiao_elevener
 */
public interface PredefinedCodeService {
    /**
     * 创建预定义配置
     *
     * @param predefinedCodeVo
     */
    void createPredefinedCode(PredefinedCodeVo predefinedCodeVo);

    /**
     * 条件分页查询
     *
     * @param predefinedCodeSo
     * @return
     */
    List<PredefinedCodeVo> findPredefinedCodeVosBySo(PredefinedCodeSo predefinedCodeSo);

    /**
     * 条件分页查询数量
     *
     * @param predefinedCodeSo
     * @return
     */
    Integer countByPredefinedCodeSo(PredefinedCodeSo predefinedCodeSo);

    /**
     * 更新
     *
     * @param predefinedCodeVo
     */
    void updatePredefinedCode(PredefinedCodeVo predefinedCodeVo);

    /**
     * 删除
     *
     * @param id
     */
    void deletePredefinedCode(long id);
}
