package com.lss.domain.entity;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
@Data
public class Account {

  private long id;
  private String monomial;
  private double price;
  @TableField(fill = FieldFill.INSERT_UPDATE)//将该字段设置为自动填充
  private String uptAct;
  @TableField(fill = FieldFill.INSERT)
  @JsonFormat(pattern="yyyy年MM月dd日 HH时mm分ss秒",timezone = "GMT+8")
  private Date createdTime;
  @TableField(fill = FieldFill.INSERT_UPDATE)
  @JsonFormat(pattern="yyyy年MM月dd日 HH时mm分ss秒",timezone = "GMT+8")
  private Date updatedTime;
  private String userId;



}
