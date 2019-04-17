package com.zcf.world.service.layui;

import com.zcf.world.pojo.Admins;
import com.zcf.world.mapper.AdminsMapper;
import com.zcf.world.common.layui.LayUiResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import tk.mybatis.mapper.entity.Example;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
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
        return this.LayUiAdminsMapper.insert(admins) == 1;
    }
    /**
    *根据主键删除数据
    */
    public boolean delete(Integer id) {
        return this.LayUiAdminsMapper.deleteByPrimaryKey(id) == 1;
    }

    /**
    *根据主键更新非空数据
    */
    public boolean update(Admins admins) {
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
        example.createCriteria().andLike("id", "%" + keywords + "%");
        PageHelper.startPage(page, limit);
        List<Admins> list = this.LayUiAdminsMapper.selectByExample(example);
        return new LayUiResult("0", "查询成功", new PageInfo<>(list).getTotal(), list);
    }
}
