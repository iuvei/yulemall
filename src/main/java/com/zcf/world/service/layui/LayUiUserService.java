package com.zcf.world.service.layui;

import com.zcf.world.common.utils.Body;
import com.zcf.world.pojo.User;
import com.zcf.world.mapper.UserMapper;
import com.zcf.world.common.layui.LayUiResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import tk.mybatis.mapper.entity.Example;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
/**
* @author 许宝予
* @date 2019/04/15
*/
@Service
public class LayUiUserService{

    @Resource
    private UserMapper LayUiUserMapper;

    /**
    *新增数据
    */
    public boolean add(User user) {
        if (user.getCreatTime() == null){
            user.setCreatTime(new Date());
        }
        if (user.getUpdateTime() == null){
            user.setUpdateTime(new Date());
        }
        user.setDeleted("N");
        return this.LayUiUserMapper.insert(user) == 1;
    }
    /**
    *根据主键删除数据
    */
    public boolean delete(Integer id) {
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("id",id);
        List<User> list = this.LayUiUserMapper.selectByExample(example);
        if (list.size() != 1){
            return false;
        }
        User category = new User();
        category.setId(list.get(0).getId());
        category.setDeleted("Y");
        boolean update = update(category);
        return update;
    }

    /**
    *根据主键更新非空数据
    */
    public boolean update(User user) {
        user.setUpdateTime(new Date());
        return this.LayUiUserMapper.updateByPrimaryKeySelective(user) == 1;
    }

    /**
    *分页查询表中数据
    */
    public LayUiResult query(Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("deleted", "N");
        List<User> list = this.LayUiUserMapper.selectByExample(example);
        return new LayUiResult("0", "查询成功", new PageInfo<>(list).getTotal(), list);
    }

    /**
    *分页并搜索关键字
    */
     public LayUiResult search(Integer page, Integer limit,String keywords) {
         Example example = new Example(User.class);
         example.createCriteria().andLike("id", "%" + keywords + "%")
                 .andEqualTo("deleted","N");
         PageHelper.startPage(page, limit);
        List<User> list = this.LayUiUserMapper.selectByExample(example);
        return new LayUiResult("0", "查询成功", new PageInfo<>(list).getTotal(), list);
    }
}
