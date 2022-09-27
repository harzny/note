package com.lss.controller;

import com.lss.domain.vo.AccountVo;
import com.lss.domain.data.AjaxResult;
import com.lss.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController extends BaseController {
    @Qualifier("IAccountService")
    @Autowired
    private IAccountService iAccountService;

    @PostMapping
    public AjaxResult queryList(){
        List<AccountVo> accountList = iAccountService.getList();
        return success(accountList);
    }

}
