package com.example.docker.service;

import com.example.docker.entities.Staff;
import com.example.docker.mapper.StaffMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author liuaixia
 * @ClassName StaffService
 * @description: TODO
 * @date 2024年02月29日
 * @version: 1.0
 */
@Service
@Slf4j
public class StaffService {
    public static final String CACHE_KEY_STAFF = "staff:";

    @Resource
    private StaffMapper staffMapper;
    @Resource
    private RedisTemplate redisTemplate;

    public void addStaff(Staff staff){
        int result = staffMapper.insertSelective(staff);
        if(result>0){
            staff = staffMapper.selectByPrimaryKey(staff.getId());
            redisTemplate.opsForValue().set(CACHE_KEY_STAFF+staff.getId(),staff);
        }
    }
    public Staff findStaffById(Integer id){

        Staff staff = (Staff)redisTemplate.opsForValue().get(CACHE_KEY_STAFF+id);
        if(Objects.isNull(staff)){
            staff = staffMapper.selectByPrimaryKey(id);
        }
        return staff;
    }
}
