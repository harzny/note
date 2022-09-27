package com.lss.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lss.domain.vo.ClassifyVo;
import com.lss.service.IClassifyService;
import com.lss.utils.JWTUtil;
import com.lss.domain.data.AjaxResult;
import com.lss.domain.entity.Classify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/classify")
public class ClassifyController extends BaseController {

    @Qualifier("IClassifyService")
    @Autowired
    private IClassifyService iClassifyService;

    @PostMapping
    public AjaxResult create(@Validated Classify classify, HttpServletRequest req) {
        String userId = JWTUtil.getUserId(req);
        classify.setUserId(userId);
        boolean b = iClassifyService.save(classify);
        return b ? success("添加成功", classify) : error("添加失败", null);
    }
    @GetMapping
    public AjaxResult queryList(HttpServletRequest req) {
        String userId = JWTUtil.getUserId(req);
        QueryWrapper<ClassifyVo> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        //查询upt_act不等于D
        wrapper.ne("upt_act", "D");
        List<ClassifyVo> classifyVos = iClassifyService.queryClassifyWithNum(wrapper);
        return success(classifyVos);
    }

    @PutMapping
    public AjaxResult update(Classify classify) {
        boolean b = iClassifyService.updateById(classify);
        return b ? success("修改成功", classify) : error("修改失败", null);
    }

    @DeleteMapping("/{classifyId}")
    public AjaxResult delete(@PathVariable String classifyId) {
        Classify classify = iClassifyService.getById(classifyId);
        classify.setUptAct("D");
        boolean b = iClassifyService.updateById(classify);
        return b ? success("修改成功") : error("修改失败", null);
    }
}

