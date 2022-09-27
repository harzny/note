package com.lss.mapping;

import com.lss.domain.entity.User;
import com.lss.domain.vo.UserVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapping {
    UserMapping INSTANCE= Mappers.getMapper(UserMapping.class);
    UserVo toUserVo(User user);
}
