package com.zcf.world.service.layui;

import com.zcf.world.pojo.Category;
import com.zcf.world.mapper.CategoryMapper;
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
* @date 2019/04/17
*/
@Service
public class LayUiCategoryService{

    @Resource
    private CategoryMapper LayUiCategoryMapper;

    /**
    *新增数据
    */
    public boolean add(Category category) {
        if (category.getCreatTime() == null){
            category.setCreatTime(new Date());
        }
        if (category.getUpdateTime() == null){
            category.setUpdateTime(new Date());
        }
        category.setDeleted("N");
        return this.LayUiCategoryMapper.insert(category) == 1;
    }
    /**
    *根据主键删除数据
    */
    public boolean delete(Integer id) {
        Example example = new Example(Category.class);
        example.createCriteria().andEqualTo("id",id);
        List<Category> list = this.LayUiCategoryMapper.selectByExample(example);
        if (list.size() != 1){
            return false;
        }
        Category category = new Category();
        category.setId(list.get(0).getId());
        category.setDeleted("Y");
        boolean update = update(category);
        return update;
    }

    /**
    *根据主键更新非空数据
    */
    public boolean update(Category category) {
        category.setUpdateTime(new Date());
        return this.LayUiCategoryMapper.updateByPrimaryKeySelective(category) == 1;
    }

    /**
    *分页查询表中数据
    */
    public LayUiResult query(Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        Example example = new Example(Category.class);
        example.createCriteria().andEqualTo("deleted", "N");
        List<Category> list = this.LayUiCategoryMapper.selectByExample(example);
        return new LayUiResult("0", "查询成功", new PageInfo<>(list).getTotal(), list);
    }

    /**
    *分页并搜索关键字
    */
     public LayUiResult search(Integer page, Integer limit,String keywords) {
         Example example = new Example(Category.class);
         example.createCriteria().andLike("id", "%" + keywords + "%")
                 .andEqualTo("deleted","N");
         PageHelper.startPage(page, limit);
         List<Category> list = this.LayUiCategoryMapper.selectByExample(example);
        return new LayUiResult("0", "查询成功", new PageInfo<>(list).getTotal(), list);
    }
}
