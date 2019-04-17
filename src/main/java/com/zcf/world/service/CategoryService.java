package com.zcf.world.service;

import com.zcf.world.pojo.Category;
import com.zcf.world.mapper.CategoryMapper;
import tk.mybatis.mapper.entity.Example;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import com.zcf.world.common.exception.CommonException;
import com.zcf.world.common.exception.ExceptionEnum;

import javax.annotation.Resource;
import java.util.List;
/**
* @author 许宝予
* @date 2019/04/17
*/
@Service
public class CategoryService{

    @Resource
    private CategoryMapper categorymapper;

    /**
     * 新增一条数据
     *
     * @param category category对象
     */
    public void addCategory(Category category) {
        int count = this.categorymapper.insertSelective(category);
        if(count != 1){
             throw new CommonException(ExceptionEnum.SAVE_FAILURE);
        }
    }

    /**
     * 根据主键删除数据
     *
     * @param id 主键
     */
    public void deleteCategoryById(Integer id) {
        int count = this.categorymapper.deleteByPrimaryKey(id);
        if(count != 1){
             throw new CommonException(ExceptionEnum.DELETE_FAILURE);
        }
    }

    /**
     * 根据主键更新非空数据
     *
     * @param category category对象
     */
    public void updateCategory(Category category) {
        int count = this.categorymapper.updateByPrimaryKeySelective(category);
         if(count != 1){
             throw new CommonException(ExceptionEnum.UPDATE_FAILURE);
        }
    }

    /**
     * 获取所有表中数据
     *
     * @return Category对象集合
     */
    public List<Category> getAllCategory() {
        List<Category> list = this.categorymapper.selectAll();
       if(CollectionUtils.isEmpty(list)){
            throw new CommonException(ExceptionEnum.DATA_DOES_NOT_EXIST);
        }
        return list;
    }

    /**
     * 根据主键获取单一数据
     *
     * @param id 主键
     * @return Category对象
     */
    public Category getCategory(Integer id){
        Category Category = this.categorymapper.selectByPrimaryKey(id);
        if (Category == null) {
            throw new CommonException(ExceptionEnum.DATA_DOES_NOT_EXIST);
        }
        return Category;
    }

}
