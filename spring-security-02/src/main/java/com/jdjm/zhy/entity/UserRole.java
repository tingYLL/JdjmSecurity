package com.jdjm.zhy.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("user_role")
public class UserRole {
    @TableId
    private Integer id;

    private Integer uid;

    private Integer rid;
}
