package com.zcf.world.service;

import com.zcf.world.pojo.Subcategory;
import com.zcf.world.mapper.SubcategoryMapper;
import tk.mybatis.mapper.entity.Example;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import com.zcf.world.common.exception.CommonException;
import com.zcf.world.common.exception.ExceptionEnum;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
/**
* @author 许宝予
* @date 2019/04/18
*/
@Service
public class SubcategoryService{

    @Resource
    private SubcategoryMapper subcategorymapper;

    /**
     * 新增一条数据
     *
     * @param subcategory subcategory对象
     */
    public void addSubcategory(Subcategory subcategory) {
        if (subcategory.getCreatTime() == null){
            subcategory.setCreatTime(new Date());
        }
        if (subcategory.getUpdateTime() == null){
            subcategory.setUpdateTime(new Date());
        }
        subcategory.setDeleted("N");
        int count = this.subcategorymapper.insertSelective(subcategory);
        if(count != 1){
             throw new CommonException(ExceptionEnum.SAVE_FAILURE);
        }
    }

    /**
     * 根据主键删除数据
     *
     * @param id 主键
     */
    public void deleteSubcategoryById(Integer id) {

        Example example = new Example(Subcategory.class);
        example.createCriteria().andEqualTo("id",id);
        List<Subcategory> list = this.subcategorymapper.selectByExample(example);
        if (list.size() != 1){
            throw new CommonException(ExceptionEnum.NULL_LIST);
        }
        Subcategory subcategory = new Subcategory();
        subcategory.setId(list.get(0).getId());
        subcategory.setDeleted("Y");
        subcategory.setUpdateTime(new Date());
        updateSubcategory(subcategory);
    }

    /**
     * 根据主键更新非空数据
     *
     * @param subcategory subcategory对象
     */
    public void updateSubcategory(Subcategory subcategory) {
        subcategory.setUpdateTime(new Date());
        int count = this.subcategorymapper.updateByPrimaryKeySelective(subcategory);
        if(count != 1){
             throw new CommonException(ExceptionEnum.UPDATE_FAILURE);
        }
    }

    /**
     * 获取所有表中数据
     *
     * @return Subcategory对象集合
     */
    public List<Subcategory> getAllSubcategory() {
        Example example = new Example(Subcategory.class);
        example.createCriteria().andEqualTo("deleted","N");
        List<Subcategory> list = this.subcategorymapper.selectByExample(example);
        if(CollectionUtils.isEmpty(list)){
            throw new CommonException(ExceptionEnum.DATA_DOES_NOT_EXIST);
        }
        return list;
    }

    /**
     * 根据主键获取单一数据
     *
     * @param id 主键
     * @return Subcategory对象
     */
    public Subcategory getSubcategory(Integer id){
        Subcategory Subcategory = this.subcategorymapper.selectByPrimaryKey(id);
        if (Subcategory == null) {
            throw new CommonException(ExceptionEnum.DATA_DOES_NOT_EXIST);
        }
        return Subcategory;
    }

    /**
     * 获取首页9个 子分类 显示
     */



}
