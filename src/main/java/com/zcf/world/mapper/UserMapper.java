package com.zcf.world.mapper;

import com.zcf.world.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;
/**
* @author 许宝予
* @date 2019/04/15
*/
public interface UserMapper extends Mapper<User> {

    //根据手机号  设置登录新密码
    @Update("UPDATE `user` SET login_pwd = #{pwd} WHERE phone = #{phone}")
    int updateLoginPwd(@Param("phone")String phone,@Param("pwd")String pwd);
}
