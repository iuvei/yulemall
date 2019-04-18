package com.zcf.world.service;

import com.zcf.world.common.exception.CommonException;
import com.zcf.world.common.exception.ExceptionEnum;
import com.zcf.world.common.utils.Body;
import com.zcf.world.mapper.UserMapper;
import com.zcf.world.pojo.User;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
/**
* @author 许宝予
* @date 2019/04/15
*/
@Service
public class UserService{

    @Resource
    private UserMapper usermapper;

    /**
     * 新增一条数据
     *
     * @param user user对象
     */
    public void addUser(User user) {
        if (user.getCreatTime() == null){
            user.setCreatTime(new Date());
        }
        if (user.getUpdateTime() == null){
            user.setUpdateTime(new Date());
        }
        user.setDeleted("N");
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

        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("id",id);
        List<User> list = this.usermapper.selectByExample(example);
        if (list.size() != 1){
            throw new CommonException(ExceptionEnum.NULL_LIST);
        }
        User user = new User();
        user.setId(list.get(0).getId());
        user.setDeleted("Y");
        user.setUpdateTime(new Date());
        updateUser(user);
    }

    /**
     * 根据主键更新非空数据
     *
     * @param user user对象
     */
    public void updateUser(User user) {
        user.setUpdateTime(new Date());
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
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("deleted","N");
        List<User> list = this.usermapper.selectByExample(example);
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

    /**
     * 登录
     */
    public Body login(String phone, String password){
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("phone", phone)
                .andEqualTo("loginPwd",password)
                .andEqualTo("deleted","N");
        List<User> list = this.usermapper.selectByExample(example);
        if (list.size() != 1){
            return Body.newInstance(100,"用户名或密码错误");
        }
        return Body.newInstance(list);
    }
}
