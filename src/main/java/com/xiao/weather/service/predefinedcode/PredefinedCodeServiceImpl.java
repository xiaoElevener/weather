package com.xiao.weather.service.predefinedcode;

import com.xiao.weather.common.so.predefinedcode.PredefinedCodeSo;
import com.xiao.weather.common.vo.predefinedcode.PredefinedCodeVo;
import com.xiao.weather.dao.predefinedcode.PredefinedCodeDao;
import com.xiao.weather.entity.predefinedcode.PredefinedCode;
import com.xiao.weather.service.core.AbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author xiao_elevener
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class PredefinedCodeServiceImpl extends AbstractServiceImpl implements PredefinedCodeService {

    @Autowired
    private PredefinedCodeDao predefinedCodeDao;

    @Override
    public void createPredefinedCode(PredefinedCodeVo predefinedCodeVo) {
        predefinedCodeDao.insert(dozer.convert(predefinedCodeVo, PredefinedCode.class));
    }

    @Override
    public List<PredefinedCodeVo> findPredefinedCodeVosBySo(PredefinedCodeSo predefinedCodeSo) {
        return predefinedCodeDao.selectPaginationVoBySo(predefinedCodeSo);
    }

    @Override
    public Integer countByPredefinedCodeSo(PredefinedCodeSo predefinedCodeSo) {
        return predefinedCodeDao.selectCountBySo(predefinedCodeSo);
    }

    @Override
    public void updatePredefinedCode(PredefinedCodeVo predefinedCodeVo) {
        predefinedCodeDao.update(dozer.convert(predefinedCodeVo, PredefinedCode.class));
    }

    @Override
    public void deletePredefinedCode(long id) {
        predefinedCodeDao.delete(id);
    }
}
