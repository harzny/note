package com.lss.domain.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class ClassifyVo {

  @TableId(type = IdType.ASSIGN_ID)
  private String classifyId;
  @NotNull(message = "分类名称不能为空")
  @NotEmpty(message = "分类名称不能为空")
  private String classifyName;
  @TableField(fill = FieldFill.INSERT_UPDATE)//将该字段设置为自动填充
  private String uptAct;
  @TableField(fill = FieldFill.INSERT)
  @JsonFormat(pattern="yyyy年MM月dd日 HH时mm分ss秒",timezone = "GMT+8")
  private Date createdTime;
  @TableField(fill = FieldFill.INSERT_UPDATE)
  @JsonFormat(pattern="yyyy年MM月dd日 HH时mm分ss秒",timezone = "GMT+8")
  private Date updatedTime;
  private String userId;

  //保存队形分类里面的便签数量
  private  Long num;




}
