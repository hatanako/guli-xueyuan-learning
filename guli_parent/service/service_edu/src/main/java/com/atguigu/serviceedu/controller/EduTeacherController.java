package com.atguigu.serviceedu.controller;


import com.atguigu.serviceedu.entity.EduTeacher;
import com.atguigu.serviceedu.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2024-05-11
 */
@CrossOrigin
@RestController
@RequestMapping("/serviceedu/teacher")
public class EduTeacherController {
    @Autowired
    public EduTeacherService teacherService;

    @GetMapping("findAll")
    public List<EduTeacher> list(){
        return teacherService.list(null);
    }

    @DeleteMapping("{id}")
    public boolean removeById(@PathVariable String id){
        return teacherService.removeById(id);
    }

}

