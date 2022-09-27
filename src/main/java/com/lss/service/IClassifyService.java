package com.lss.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lss.domain.vo.ClassifyVo;
import com.lss.domain.entity.Classify;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IClassifyService extends IService<Classify> {
    List<ClassifyVo> queryClassifyWithNum(QueryWrapper<ClassifyVo> wrapper);

}
