package com.lss.domain.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor  //无参构造
@AllArgsConstructor  //全参构造
public class UserVo {
  private String userId;
  private String userAccount;
  private String userName;
  private String avatar;
  private String encryption;
  private String uptAct;
  private Date createdTime;
  private Date updatedTime;

}
