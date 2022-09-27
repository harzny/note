package com.lss.controller;

import com.lss.domain.data.AjaxResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/file")
public class FileController extends BaseController {
    @PostMapping("/upload")
    public AjaxResult upload(MultipartFile file){
        String fileName = file.getOriginalFilename();
        // 新建一个file
        File file1=new File("D:\\file\\upload\\images\\"+System.currentTimeMillis()+fileName);
        //如果文件不存在就创建
        if (!file1.exists()){
            file1.mkdirs();
        }
        // 保存文件到file1
        try {
            file.transferTo(file1);
        } catch (IOException e) {
            e.printStackTrace();
            return error();
        }
        return success("/upload/images/"+file1.getName());

    }
}
