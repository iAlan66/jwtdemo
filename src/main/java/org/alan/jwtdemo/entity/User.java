package org.alan.jwtdemo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@TableName("user")
public class User {
    @TableId
    private Integer id;
    private String username;
    private String password;
    private String salt = UUID.randomUUID().toString().replaceAll("-", "");
    private Date createTime;

}
