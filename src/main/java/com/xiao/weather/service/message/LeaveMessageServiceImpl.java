package com.xiao.weather.service.message;

import com.xiao.weather.service.core.AbstractServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author xiao_elevener
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class LeaveMessageServiceImpl extends AbstractServiceImpl implements LeaveMessageService {

}
