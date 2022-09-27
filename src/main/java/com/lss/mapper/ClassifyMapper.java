package com.lss.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.lss.domain.entity.Classify;
import com.lss.domain.vo.ClassifyVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassifyMapper extends BaseMapper<Classify> {
    List<ClassifyVo> queryClassifyWithNum(@Param(Constants.WRAPPER) QueryWrapper<ClassifyVo> wrapper);
}
