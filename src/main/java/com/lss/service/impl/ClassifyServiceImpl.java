package com.lss.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lss.domain.vo.ClassifyVo;
import com.lss.service.IClassifyService;
import com.lss.domain.entity.Classify;
import com.lss.mapper.ClassifyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassifyServiceImpl extends ServiceImpl<ClassifyMapper, Classify> implements IClassifyService {
    @Autowired
    ClassifyMapper mapper;

    @Override
    public List<ClassifyVo> queryClassifyWithNum(QueryWrapper<ClassifyVo> wrapper) {
        return  mapper.queryClassifyWithNum(wrapper);
    }
}
