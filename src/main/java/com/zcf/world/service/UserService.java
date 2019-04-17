package com.zcf.world.service;

import com.zcf.world.pojo.User;
import com.zcf.world.mapper.UserMapper;
import tk.mybatis.mapper.entity.Example;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import com.zcf.world.common.exception.CommonException;
import com.zcf.world.common.exception.ExceptionEnum;
import java.util.List;
/**
* @author 许宝予
* @date 2019/04/15
*/
@Service
public class UserService{

    @Autowired
    private UserMapper usermapper;

    /**
     * 新增一条数据
     *
     * @param user user对象
     */
    public void addUser(User user) {
        int count = this.usermapper.insertSelective(user);
        if(count != 1){
             throw new CommonException(ExceptionEnum.SAVE_FAILURE);
        }
    }

    /**
     * 根据主键删除数据
     *
     * @param id 主键
     */
    public void deleteUserById(Integer id) {
        int count = this.usermapper.deleteByPrimaryKey(id);
        if(count != 1){
             throw new CommonException(ExceptionEnum.DELETE_FAILURE);
        }
    }

    /**
     * 根据主键更新非空数据
     *
     * @param user user对象
     */
    public void updateUser(User user) {
        int count = this.usermapper.updateByPrimaryKeySelective(user);
         if(count != 1){
             throw new CommonException(ExceptionEnum.UPDATE_FAILURE);
        }
    }

    /**
     * 获取所有表中数据
     *
     * @return User对象集合
     */
    public List<User> getAllUser() {
        List<User> list = this.usermapper.selectAll();
       if(CollectionUtils.isEmpty(list)){
            throw new CommonException(ExceptionEnum.DATA_DOES_NOT_EXIST);
        }
        return list;
    }

    /**
     * 根据主键获取单一数据
     *
     * @param id 主键
     * @return User对象
     */
    public User getUser(Integer id){
        User User = this.usermapper.selectByPrimaryKey(id);
        if (User == null) {
            throw new CommonException(ExceptionEnum.DATA_DOES_NOT_EXIST);
        }
        return User;
    }

}
