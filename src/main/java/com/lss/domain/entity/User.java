package com.lss.domain.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class User {
  @TableId(type= IdType.ASSIGN_ID)
  private String userId;
  @NotNull(message = "账号不能为空")
  @NotEmpty(message = "账号不能为空")
  private String userAccount;
  private String userName;
  @NotNull(message = "密码不能为空")
  @NotEmpty(message = "密码不能为空")
  private String password;
  private String avatar;
  private String encryption;
  @TableField(fill = FieldFill.INSERT_UPDATE)//将该字段设置为自动填充
  private String uptAct;
  @TableField(fill = FieldFill.INSERT)
  @JsonFormat(pattern="yyyy年MM月dd日 HH时mm分ss秒",timezone = "GMT+8")
  private Date createdTime;
  @TableField(fill = FieldFill.INSERT_UPDATE)
  @JsonFormat(pattern="yyyy年MM月dd日 HH时mm分ss秒",timezone = "GMT+8")
  private Date updatedTime;

}
