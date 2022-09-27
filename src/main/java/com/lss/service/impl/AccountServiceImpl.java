package com.lss.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lss.domain.vo.AccountVo;
import com.lss.service.IAccountService;
import com.lss.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, AccountVo> implements IAccountService {
    @Autowired
    AccountMapper accountMapper;
    @Override
    public List<AccountVo> getList() {
        return accountMapper.getAccountList();
    }


}
