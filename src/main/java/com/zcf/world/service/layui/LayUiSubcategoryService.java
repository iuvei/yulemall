package com.zcf.world.service.layui;

import com.zcf.world.pojo.Subcategory;
import com.zcf.world.mapper.SubcategoryMapper;
import com.zcf.world.common.layui.LayUiResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import tk.mybatis.mapper.entity.Example;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
/**
* @author 许宝予
* @date 2019/04/18
*/
@Service
public class LayUiSubcategoryService{

    @Resource
    private SubcategoryMapper LayUiSubcategoryMapper;

    /**
    *新增数据
    */
    public boolean add(Subcategory subcategory) {
        if (subcategory.getCreatTime() == null){
            subcategory.setCreatTime(new Date());
        }
        if (subcategory.getUpdateTime() == null){
            subcategory.setUpdateTime(new Date());
        }
        subcategory.setDeleted("N");
        return this.LayUiSubcategoryMapper.insert(subcategory) == 1;
    }
    /**
    *根据主键删除数据
    */
    public boolean delete(Integer id) {
        Example example = new Example(Subcategory.class);
        example.createCriteria().andEqualTo("id",id);
        List<Subcategory> list = this.LayUiSubcategoryMapper.selectByExample(example);
        if (list.size() != 1){
            return false;
        }
        Subcategory subcategory = new Subcategory();
        subcategory.setId(list.get(0).getId());
        subcategory.setDeleted("Y");
        subcategory.setUpdateTime(new Date());
        return this.update(subcategory);
    }

    /**
    *根据主键更新非空数据
    */
    public boolean update(Subcategory subcategory) {
        subcategory.setUpdateTime(new Date());
        return this.LayUiSubcategoryMapper.updateByPrimaryKeySelective(subcategory) == 1;
    }

    /**
    *分页查询表中数据
    */
    public LayUiResult query(Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        List<Subcategory> list = this.LayUiSubcategoryMapper.selectAll();
        return new LayUiResult("0", "查询成功", new PageInfo<>(list).getTotal(), list);
    }

    /**
    *分页并搜索关键字
    */
     public LayUiResult search(Integer page, Integer limit,String keywords) {
        Example example = new Example(Subcategory.class);
        example.createCriteria().andLike("id", "%" + keywords + "%");
        PageHelper.startPage(page, limit);
        List<Subcategory> list = this.LayUiSubcategoryMapper.selectByExample(example);
        return new LayUiResult("0", "查询成功", new PageInfo<>(list).getTotal(), list);
    }
}
