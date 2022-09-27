package com.lss.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lss.domain.vo.AccountVo;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface IAccountService extends IService<AccountVo> {
    List<AccountVo> getList();
}
