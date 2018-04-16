package com.xiao.weather.dao.core;

import com.xiao.weather.common.so.BaseSo;
import com.xiao.weather.common.vo.AbstractVo;
import com.xiao.weather.entity.AbstractEntityBase;

import java.util.List;

/**
 * 基础DAO
 * @param <T>
 */
public interface Dao<T extends AbstractEntityBase> {

	/**
	 * 插入 T <code>com.oasis.crm.model.core.base.AbstractBo</code>的继承对象
	 * @param bo
	 * @return
	 */
    Long insert(T bo);
    
    /**
     * 插入T <code>com.oasis.crm.model.core.base.AbstractBo</code>的继承对象.
     * @param bo
     * @param completeCreatingInfo 是否填充创建时间、创建人等相关信息
     * @return
     */
    Long insert(T bo, boolean completeCreatingInfo);

    /**
     * 更新  T <code>com.oasis.crm.model.core.base.AbstractBo</code>的继承对象
     * @param bo
     * @return
     */
    T update(T bo);

    /**
     * 插入T <code>com.oasis.crm.model.core.base.AbstractBo</code>的继承对象.
     * @param bo
     * @param completeUpdateInfo 是否填充更新时间、更新人等相关信息
     * @return
     */
    T update(T bo, boolean completeUpdateInfo);

    /**
     * 删除  T <code>com.oasis.crm.model.core.base.AbstractBo</code>的继承对象
     * @param id
     * @return
     */
    int delete(Long id);

    /**
     * 根据id查找 T <code>com.oasis.crm.model.core.base.AbstractBo</code>的继承对象
     * @param id
     * @return
     */
    T find(Long id);

    /**
     * 根据<code>com.oasis.crm.common.core.base.BaseSo</code>查找 T <code>com.oasis.crm.model.core.base.AbstractBo</code>的继承对象
     * @param so
     * @return
     */
    List<T> selectBySo(BaseSo so);

    /**
     * 根据<code>com.oasis.crm.common.core.base.BaseSo</code>查找分页列表 T <code>com.oasis.crm.model.core.base.AbstractBo</code>的继承对象
     * @param so
     * @return
     */
    List<T> selectPaginationBySo(BaseSo so);

    /**
     * 根据<code>com.oasis.crm.common.core.base.BaseSo</code>得到数量
     * @param so
     * @return
     */
    int selectCountBySo(BaseSo so);

    /**
     * 根据id查找<code>com.oasis.crm.common.core.base.AbstractVo</code>的继承对象
     * @param id
     * @return
     */
    <U extends AbstractVo> U findVo(Long id);

    /**
     * 根据<code>com.oasis.crm.common.core.base.BaseSo</code>查找<code>com.oasis.crm.common.core.base.AbstractVo</code>的继承对象
     * @param so
     * @return
     */
    <U extends AbstractVo> List<U> selectVoBySo(BaseSo so);

    /**
     * 根据<code>com.oasis.crm.common.core.base.BaseSo</code>查找分页列表<code>com.oasis.crm.common.core.base.AbstractVo</code>的继承对象
     * @param so
     * @return
     */
    <U extends AbstractVo> List<U> selectPaginationVoBySo(BaseSo so);

}
