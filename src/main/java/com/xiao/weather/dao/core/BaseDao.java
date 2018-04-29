package com.xiao.weather.dao.core;


import com.xiao.weather.common.exception.ExceptionAction;
import com.xiao.weather.common.exception.ExceptionBuilder;
import com.xiao.weather.common.exception.OptLockException;
import com.xiao.weather.common.security.SecurityContext;
import com.xiao.weather.common.security.SecurityContextHolder;
import com.xiao.weather.common.so.BaseSo;
import com.xiao.weather.common.vo.AbstractVo;
import com.xiao.weather.dao.util.DaoHelper;
import com.xiao.weather.entity.AbstractEntityBase;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.ParameterizedType;
import java.util.Date;
import java.util.List;

/**
 * 基础DAO实现
 * 
 * @param <T>
 */
@Slf4j
@Component
public abstract class BaseDao<T extends AbstractEntityBase> implements Dao<T> {

    /**
     * 分割点
     */
    private static final String POSTFIX_SPLIT = ".";

    @Autowired
    protected SqlSession sqlSession;

    private Class<T> entityClass = null;

    @SuppressWarnings("unchecked")
    public BaseDao() {
        entityClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
    
    protected SqlSession getSqlSession() {
    	return sqlSession;
    }

    /**
     * 获取执行mapper方法路径前缀
     * 
     * @return
     */
    protected String getStatementPrefix() {
        return this.getClass().getInterfaces()[0].getName() + POSTFIX_SPLIT;
    }

    /**
     * 获取执行mapper方法的全名
     * 
     * @return
     */
    protected String getStatement(String mapperStatementId) {
        return getStatementPrefix() + mapperStatementId;
    }

    private static final String INSERT = "insert";

    /**
     * 插入 T <code>com.oasis.crm.model.core.base.AbstractBo</code>的继承对象
     */
    @Override
    public Long insert(T bo) {
        return insert(bo, true);
    }

    @Override
    public Long insert(T bo, boolean completeCreatingInfo) {
        if (completeCreatingInfo) {
            fillCreateInfo4Bo(bo);
        }
        sqlSession.insert(getStatementPrefix() + INSERT, bo);
        return bo.getId();
    }

    private static final String UPDATE = "update";

    /**
     * 更新 T <code>com.oasis.crm.model.core.base.AbstractBo</code>的继承对象
     */
    @Override
    public T update(T bo,boolean completeUpdateInfo) {
        if(completeUpdateInfo){
            fillUpdateInfo4Bo(bo);
        }
        int affectedRows = sqlSession.update(getStatementPrefix() + UPDATE, bo);
        if (affectedRows == 0) {
            throw ExceptionBuilder.build(new OptLockException(
                    "This record modified by another thread before commit,please try again"), ExceptionAction.NOSHOW);
        }
        return find(bo.getId());
    }

    @Override
    public T update(T bo) {
        fillUpdateInfo4Bo(bo);
        int affectedRows = sqlSession.update(getStatementPrefix() + UPDATE, bo);
        if (affectedRows == 0) {
            throw ExceptionBuilder.build(new OptLockException(
                    "This record modified by another thread before commit,please try again"), ExceptionAction.NOSHOW);
        }
        return find(bo.getId());
    }

    private static final String DELETE = "delete";

    /**
     * 删除 T <code>com.oasis.crm.model.core.base.AbstractBo</code>的继承对象
     */
    @Override
    public int delete(Long id) {
        int affectedRows = sqlSession.delete(getStatementPrefix() + DELETE, id);
        return affectedRows;
    }

    private static final String FIND = "find";

    /**
     * 根据id查找 T <code>com.oasis.crm.model.core.base.AbstractBo</code>的继承对象
     */
    @Override
    public T find(Long id) {
        T bo = sqlSession.selectOne(getStatementPrefix() + FIND, id);
        return bo;
    }

    private static final String SELECT_BY_SO = "selectBySo";

    /**
     * 根据<code>com.oasis.crm.common.core.base.BaseSo</code>查找 T
     * <code>com.oasis.crm.model.core.base.AbstractBo</code>的继承对象
     */
    @Override
    public List<T> selectBySo(BaseSo so) {
        List<T> res = sqlSession.selectList(getStatementPrefix() + SELECT_BY_SO, so);
        return res;
    }

    /**
     * 根据<code>com.oasis.crm.common.core.base.BaseSo</code>查找分页列表 T
     * <code>com.oasis.crm.model.core.base.AbstractBo</code>的继承对象
     */
    @Override
    public List<T> selectPaginationBySo(BaseSo so) {
        RowBounds rowBounds = DaoHelper.generateRowBounds(so);
        List<T> res = sqlSession.selectList(getStatementPrefix() + SELECT_BY_SO, so, rowBounds);
        return res;
    }

    private static final String SELECT_COUNT_BY_SO = "selectCountBySo";

    /**
     * 根据<code>com.oasis.crm.common.core.base.BaseSo</code>得到数量
     */
    @Override
    public int selectCountBySo(BaseSo so) {
        int count = sqlSession.selectOne(getStatementPrefix() + SELECT_COUNT_BY_SO, so);
        return count;
    }

    private static final String FIND_VO = "findVo";

    /**
     * 根据id查找<code>com.oasis.crm.common.core.base.AbstractVo</code>的继承对象
     */
    @Override
    public <U extends AbstractVo> U findVo(Long id) {
        U vo = sqlSession.selectOne(getStatementPrefix() + FIND_VO, id);
        return vo;
    }

    private static final String SELECT_VO_BY_SO = "selectVoBySo";

    /**
     * 根据<code>com.oasis.crm.common.core.base.BaseSo</code>查找
     * <code>com.oasis.crm.common.core.base.AbstractVo</code>的继承对象
     */
    @Override
    public <U extends AbstractVo> List<U> selectVoBySo(BaseSo so) {
        List<U> res = sqlSession.selectList(getStatementPrefix() + SELECT_VO_BY_SO, so);
        return res;
    }

    /**
     * 根据<code>com.oasis.crm.common.core.base.BaseSo</code>查找分页列表
     * <code>com.oasis.crm.common.core.base.AbstractVo</code>的继承对象
     */
    @Override
    public <U extends AbstractVo> List<U> selectPaginationVoBySo(BaseSo so) {
        RowBounds rowBounds = DaoHelper.generateRowBounds(so);
        List<U> res = sqlSession.selectList(getStatementPrefix() + SELECT_VO_BY_SO, so, rowBounds);
        return res;
    }

    /**
     * 创建的后续处理 填创建时间和创建人
     * 
     * @param bo
     */
    protected void fillCreateInfo4Bo(AbstractEntityBase bo) {
        bo.setCreatedTime(bo.getCreatedTime() == null ? 
        		          new Date() : bo.getCreatedTime());
        SecurityContext securityContext = SecurityContextHolder.getContext();
        if (securityContext != null && securityContext.getUser() != null) {
            bo.setCreatorName(securityContext.getUser().getUserName());
        }
    }

    /**
     * 更新的后续处理 填更新时间和更新人
     * 
     * @param bo
     */
    protected void fillUpdateInfo4Bo(AbstractEntityBase bo) {
        bo.setUpdatedTime(new Date());

        SecurityContext securityContext = SecurityContextHolder.getContext();
        if (securityContext != null && securityContext.getUser() != null) {
            bo.setUpdaterName(securityContext.getUser().getUserName());
        }
    }
}
