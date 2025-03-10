package org.alan.jwtdemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.alan.jwtdemo.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
