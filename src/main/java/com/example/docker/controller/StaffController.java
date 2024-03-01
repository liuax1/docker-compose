package com.example.docker.controller;

import com.example.docker.entities.Staff;
import com.example.docker.entities.StaffDTO;
import com.example.docker.service.StaffService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Random;

/**
 * @author liuaixia
 * @ClassName StaffController
 * @description: TODO
 * @date 2024年02月29日
 * @version: 1.0
 */
@Slf4j
@RestController
@RequestMapping("staff")
public class StaffController {

    @Resource
    StaffService staffService;

    @ApiOperation(value="新增staff数据",tags="")
    @PostMapping("/addStaff")
    public String addStaff(StaffDTO staffDTO){
        for(int i=0;i<3;i++){
            Staff staff = new Staff();
            staff.setUname("liuax"+i);
            staff.setPassword(new Random().nextInt(6)+"");
            staff.setSex(new Random().nextInt(2));
            staff.setDeleted(0);
            staff.setCreateTime(new Date());
            staff.setUpdateTime(new Date());

            staffService.addStaff(staff);
        }
        return "success";
    }

    @GetMapping(value = "/getStaff",produces = "application/json;charset=utf-8")
    public Staff getStaff(Integer id){
       return staffService.findStaffById(id);
    }
}
