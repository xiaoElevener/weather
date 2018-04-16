package com.xiao.weather.dao.util;

import com.xiao.weather.common.so.BaseSo;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
 * DAO帮助类
 */
public final class DaoHelper {

	private DaoHelper() {

	}

	/**
	 * 检查返回值只有一个
	 * @param list
	 * @return
	 */
    public static Object expectOne(final List<?> list) {
        Object obj = expectOneOrNull(list);
        if (obj == null) {
            throw new RuntimeException();
        }
        return obj;
    }

    /**
     * 检查返回值没有或者大于1个
     * @param list
     * @return
     */
    public static Object expectOneOrNull(final List<?> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        if (list.size() > 1) {
            throw new RuntimeException();
        }
        return list.get(0);
    }

    /**
     * 设置分页信息
     * @param so
     * @return
     */
    public static RowBounds generateRowBounds(final BaseSo so) {
        validatePageSo(so);
        return new RowBounds((so.getPageNumber() - 1) * so.getPageSize(), so.getPageSize());
    }

    /**
     * 检查分页信息
     * @param so
     */
    private static void validatePageSo(BaseSo so) {
        if (so.getPageNumber() < 1) {
            so.setPageNumber(1);
        }
        if (so.getPageSize() < 0) {
            so.setPageSize(BaseSo.DEFAULT_PAGE_SIZE);
        }
    }
}
