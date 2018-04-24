package com.xiao.weather.service.dealhistory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.xiao.weather.service.core.AbstractServiceImpl;

/**
 * @author xiao_elevener
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class DealHistoryServiceImpl extends AbstractServiceImpl implements DealHistoryService {
    
}
