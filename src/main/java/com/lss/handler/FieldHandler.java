package com.lss.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;
@Component
public class FieldHandler implements MetaObjectHandler {


    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName( "uptAct","I",metaObject);

        this.setFieldValByName( "createdTime", new Date(),metaObject);

        this.setFieldValByName( "updatedTime", new Date(),metaObject);
        this.setFieldValByName( "isEncryption", 0,metaObject);
        this.setFieldValByName( "isTop", 0,metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updatedTime",new Date(),metaObject);
        if(!"D".equals(this.getFieldValByName("uptAct",metaObject))){
            this.setFieldValByName("uptAct", "U",metaObject);
        }
    }
}
