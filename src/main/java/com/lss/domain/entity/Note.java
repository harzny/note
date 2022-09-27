package com.lss.domain.entity;


import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
@Data
@TableName("list")
public class Note {


  @TableId(type = IdType.ASSIGN_ID)
  private String noteId;
  @NotNull(message = "标题不能为空")//指的是这个字段不能为空，必须有着字段
  @NotEmpty(message = "标题不能为空") //这个字段的之不能为空，可以不给这个字段，但是值不能是空的
  private String noteTitle;
  @NotNull(message = "内容不能为空")
  @NotEmpty(message = "内容不能为空")
  private String noteContent;
  @TableField(fill = FieldFill.INSERT_UPDATE)
  private int isEncryption;
  @OrderBy
  @TableField(fill = FieldFill.INSERT_UPDATE)
  private int isTop;
  private String isDelete;
  @TableField(fill = FieldFill.INSERT_UPDATE)//将该字段设置为自动填充
  private String uptAct;
  @TableField(fill = FieldFill.INSERT)
  @JsonFormat(pattern="yyyy年MM月dd日 HH时mm分ss秒",timezone = "GMT+8")
  private Date createdTime;
  @TableField(fill = FieldFill.INSERT_UPDATE)
  @JsonFormat(pattern="yyyy年MM月dd日 HH时mm分ss秒",timezone = "GMT+8")
  private Date updatedTime;
  private String classifyId;
  private String userId;

}
