package com.xiao.weather.service.dealhistory;

import com.xiao.weather.common.constant.DealType;
import com.xiao.weather.common.exception.BizException;
import com.xiao.weather.common.so.dealhistory.DealHistorySo;
import com.xiao.weather.common.so.user.UserSo;
import com.xiao.weather.common.vo.account.AccountVo;
import com.xiao.weather.common.vo.dealhistory.DailyStatisticalVo;
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

    private final String STATISTICAL_DAY = "statistical_day";

    private final String DEAL_HISTORY_COUNT = "deal_history_count";

    @Override
    public AccountVo create(DealHistoryVo dealHistoryVo) {
        setUserIdAndAccountId(dealHistoryVo);
        //先插入，后更新
        dealHistoryDao.insert(dozer.convert(dealHistoryVo, DealHistory.class));
        return accountOptions(dealHistoryVo);
    }

    @Override
    public List<DailyStatisticalVo> getDailyStatistical() {
        PredefinedCode predefinedCode = predefinedCodeDao.findByCode(STATISTICAL_DAY);
        if (predefinedCode == null) {
            throw new BizException("预定义没有维护统计天数字段");
        }
        Integer day = Integer.valueOf(predefinedCode.getValue()) - 1;
        return dealHistoryDao.getDailyStatistical(day);
    }

    @Override
    public List<DealHistoryVo> getRecentlyDealHistory(Long userId) {
        PredefinedCode predefinedCode = predefinedCodeDao.findByCode(DEAL_HISTORY_COUNT);
        return dealHistoryDao.getDealHistoryByUserId(userId, Integer.valueOf(predefinedCode.getValue()));
    }

    @Override
    public List<DealHistoryVo> findDealHistoryVosBySo(DealHistorySo dealHistorySo) {
        return dealHistoryDao.selectPaginationVoBySo(dealHistorySo);
    }

    @Override
    public Integer countByDealHistorySo(DealHistorySo dealHistorySo) {
        return dealHistoryDao.selectCountBySo(dealHistorySo);
    }

    /**
     * 通过loginName找到userId,accountId
     *
     * @param dealHistoryVo
     */
    public void setUserIdAndAccountId(DealHistoryVo dealHistoryVo) {
        UserSo userSo = new UserSo();
        userSo.setLoginName(dealHistoryVo.getLoginName());
        List<UserVo> result = userDao.selectVoBySo(userSo);
        if (CollectionUtils.isEmpty(result)) {
            throw new BizException("用户异常" + dealHistoryVo.getLoginName());
        }
        dealHistoryVo.setUserId(result.get(0).getId());
        Account account = accountDao.findByUserId(result.get(0).getId());
        if (account == null) {
            throw new BizException("用户异常" + dealHistoryVo.getLoginName());
        }
        dealHistoryVo.setAccountId(account.getId());
    }

    /**
     * 更新账户信息
     *
     * @param dealHistoryVo
     */
    public AccountVo accountOptions(DealHistoryVo dealHistoryVo) {
        Account account = accountDao.findByUserId(dealHistoryVo.getUserId());
        if (account == null) {
            throw new BizException("账户异常");
        }
        dealHistoryVo.setAccountId(account.getId());
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
        account = accountDao.update(account);
        return dozer.convert(account, AccountVo.class);
    }
}
