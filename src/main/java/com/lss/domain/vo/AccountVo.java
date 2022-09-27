package com.lss.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lss.domain.entity.Account;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class AccountVo {
    @JsonFormat(pattern="MM月dd日",timezone = "GMT+8")
    private Date createdTime;
    private List<Account> list;

}
