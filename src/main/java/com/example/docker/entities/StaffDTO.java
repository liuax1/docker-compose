package com.example.docker.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.util.Date;

/**
 * @author liuaixia
 * @ClassName StaffDTO
 * @description: TODO
 * @date 2024年02月29日
 * @version: 1.0
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@ApiModel(value="员工信息")
public class StaffDTO {
    @ApiModelProperty(value="用户id")
    private Long id;
    @ApiModelProperty(value="用户姓名")
    private String uname;
    @ApiModelProperty(value="用户密码")
    private String password;
    @ApiModelProperty(value="性别 0=女 1=男")
    private Integer sex;
    @ApiModelProperty(value="解除标志，默认0不删除，1删除")
    private Integer deleted;
    @ApiModelProperty(value="更新时间")
    @Column(name = "update_time")
    private Date updateTime;
    @ApiModelProperty(value="创建时间")
    @Column(name = "create_time")
    private Date createTime;
}
