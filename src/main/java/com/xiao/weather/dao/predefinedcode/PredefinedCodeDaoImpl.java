package com.xiao.weather.dao.predefinedcode;

import com.xiao.weather.dao.core.BaseDao;
import com.xiao.weather.entity.predefinedcode.PredefinedCode;
import org.springframework.stereotype.Repository;

/**
 * @author xiao_elevener
 */
@Repository
public class PredefinedCodeDaoImpl extends BaseDao<PredefinedCode> implements PredefinedCodeDao {

    private final String FIND_BY_CODE = "findByCode";

    @Override
    public PredefinedCode findByCode(String code) {
        return sqlSession.selectOne(getStatementPrefix() + FIND_BY_CODE, code);
    }
}


