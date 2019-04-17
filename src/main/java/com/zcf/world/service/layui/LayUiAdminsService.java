package com.zcf.world.service.layui;

import com.zcf.world.common.exception.CommonException;
import com.zcf.world.common.exception.ExceptionEnum;
import com.zcf.world.pojo.Admins;
import com.zcf.world.mapper.AdminsMapper;
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
* @date 2019/04/16
*/
@Service
public class LayUiAdminsService{

    @Resource
    private AdminsMapper LayUiAdminsMapper;

    /**
    *新增数据
    */
    public boolean add(Admins admins) {
        if (admins.getCreatTime() == null){
            admins.setCreatTime(new Date());
        }
        if (admins.getUpdateTime() == null){
            admins.setUpdateTime(new Date());
        }
        admins.setDeleted("N");
        return this.LayUiAdminsMapper.insert(admins) == 1;
    }
    /**
    *根据主键删除数据
    */
    public boolean delete(Integer id) {
        Example example = new Example(Admins.class);
        example.createCriteria().andEqualTo("id",id);
        List<Admins> list = this.LayUiAdminsMapper.selectByExample(example);
        if (list.size() != 1){
            return false;
        }
        Admins category = new Admins();
        category.setId(list.get(0).getId());
        category.setDeleted("Y");
        boolean update = update(category);
        return update;
    }

    /**
    *根据主键更新非空数据
    */
    public boolean update(Admins admins) {
        admins.setUpdateTime(new Date());
        return this.LayUiAdminsMapper.updateByPrimaryKeySelective(admins) == 1;
    }

    /**
    *分页查询表中数据
    */
    public LayUiResult query(Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        List<Admins> list = this.LayUiAdminsMapper.selectAll();
        return new LayUiResult("0", "查询成功", new PageInfo<>(list).getTotal(), list);
    }

    /**
    *分页并搜索关键字
    */
     public LayUiResult search(Integer page, Integer limit,String keywords) {
        Example example = new Example(Admins.class);
        example.createCriteria().andLike("id", "%" + keywords + "%")
                            .andEqualTo("deleted","N");
        PageHelper.startPage(page, limit);
        List<Admins> list = this.LayUiAdminsMapper.selectByExample(example);
        return new LayUiResult("0", "查询成功", new PageInfo<>(list).getTotal(), list);
    }

    /**
     * 后台登录
     * @param name 用户名
     * @param pwd 密码
     * @return
     */
    public List<Admins> getLogin(String name, String pwd) {
        Example example = new Example(Admins.class);
        example.createCriteria().andEqualTo("username",name)
            .andEqualTo("password",pwd);
        List<Admins> list = this.LayUiAdminsMapper.selectByExample(example);
        if (list.size()!=1){
            throw new CommonException(ExceptionEnum.USER_PASSWORD_MISMATCH);
        }
        return list;
    }
}
