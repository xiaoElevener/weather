package com.xiao.weather.service.dealhistory;

import com.xiao.weather.common.constant.DealType;
import com.xiao.weather.common.exception.BizException;
import com.xiao.weather.common.so.user.UserSo;
import com.xiao.weather.common.vo.dealhistory.DealHistoryVo;
import com.xiao.weather.common.vo.user.UserVo;
import com.xiao.weather.dao.account.AccountDao;
import com.xiao.weather.dao.dealhistory.DealHistoryDao;
import com.xiao.weather.dao.predefinedcode.PredefinedCodeDao;
import com.xiao.weather.dao.user.UserDao;
import com.xiao.weather.entity.account.Account;
import com.xiao.weather.entity.dealhistory.DealHistory;
import com.xiao.weather.entity.predefinedcode.PredefinedCode;
import com.xiao.weather.service.core.AbstractServiceImpl;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author xiao_elevener
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class DealHistoryServiceImpl extends AbstractServiceImpl implements DealHistoryService {

    @Autowired
    private DealHistoryDao dealHistoryDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private PredefinedCodeDao predefinedCodeDao;

    private final String OVERDRAFT_TIMES = "overdraft_times";

    @Override
    public void create(DealHistoryVo dealHistoryVo) {
        setUserId(dealHistoryVo);
        accountOptions(dealHistoryVo);
        dealHistoryDao.insert(dozer.convert(dealHistoryVo, DealHistory.class));
    }

    /**
     * 通过loginName找到userId
     *
     * @param dealHistoryVo
     */
    public void setUserId(DealHistoryVo dealHistoryVo) {
        UserSo userSo = new UserSo();
        userSo.setLoginName(dealHistoryVo.getLoginName());
        List<UserVo> result = userDao.selectVoBySo(userSo);
        if (CollectionUtils.isEmpty(result)) {
            throw new BizException("用户异常" + dealHistoryVo.getLoginName());
        }
        dealHistoryVo.setUserId(result.get(0).getId());

    }

    /**
     * 更新账户信息
     *
     * @param dealHistoryVo
     */
    public void accountOptions(DealHistoryVo dealHistoryVo) {
        Account account = accountDao.findByUserId(dealHistoryVo.getUserId());
        if (account == null) {
            throw new BizException("账户异常");
        }

        PredefinedCode predefinedCode = predefinedCodeDao.findByCode(OVERDRAFT_TIMES);
        if (account.getOverdraft() > Integer.valueOf(predefinedCode.getValue())) {
            throw new BizException("透支过多，账户被冻结");
        }

        if (dealHistoryVo.getDealType().equals(DealType.CONSUME)) {
            account.setBalance(account.getBalance() - dealHistoryVo.getMoney());
            if (account.getBalance() < 0) {
                account.setOverdraft(account.getOverdraft() + 1);
            }
        } else if (dealHistoryVo.getDealType().equals(DealType.RECHARGE)) {
            account.setBalance(account.getBalance() + dealHistoryVo.getMoney());
        }
        accountDao.update(account);
    }
}
