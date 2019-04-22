package com.zcf.world.service;

import com.zcf.world.pojo.Specification;
import com.zcf.world.mapper.SpecificationMapper;
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
* @date 2019/04/22
*/
@Service
public class SpecificationService{

    @Resource
    private SpecificationMapper specificationmapper;

    /**
     * 新增一条数据
     *
     * @param specification specification对象
     */
    public void addSpecification(Specification specification) {
        if (specification.getCreatTime() == null){
            specification.setCreatTime(new Date());
        }
        if (specification.getUpdateTime() == null){
            specification.setUpdateTime(new Date());
        }
        specification.setDeleted("N");
        int count = this.specificationmapper.insertSelective(specification);
        if(count != 1){
             throw new CommonException(ExceptionEnum.SAVE_FAILURE);
        }
    }

    /**
     * 根据主键删除数据
     *
     * @param id 主键
     */
    public void deleteSpecificationById(Integer id) {

        Example example = new Example(Specification.class);
        example.createCriteria().andEqualTo("id",id);
        List<Specification> list = this.specificationmapper.selectByExample(example);
        if (list.size() != 1){
            throw new CommonException(ExceptionEnum.NULL_LIST);
        }
        Specification specification = new Specification();
        specification.setId(list.get(0).getId());
        specification.setDeleted("Y");
        specification.setUpdateTime(new Date());
        updateSpecification(specification);
    }

    /**
     * 根据主键更新非空数据
     *
     * @param specification specification对象
     */
    public void updateSpecification(Specification specification) {
        specification.setUpdateTime(new Date());
        int count = this.specificationmapper.updateByPrimaryKeySelective(specification);
        if(count != 1){
             throw new CommonException(ExceptionEnum.UPDATE_FAILURE);
        }
    }

    /**
     * 获取所有表中数据
     *
     * @return Specification对象集合
     */
    public List<Specification> getAllSpecification() {
        Example example = new Example(Specification.class);
        example.createCriteria().andEqualTo("deleted","N");
        List<Specification> list = this.specificationmapper.selectByExample(example);
        if(CollectionUtils.isEmpty(list)){
            throw new CommonException(ExceptionEnum.DATA_DOES_NOT_EXIST);
        }
        return list;
    }

    /**
     * 根据主键获取单一数据
     *
     * @param id 主键
     * @return Specification对象
     */
    public Specification getSpecification(Integer id){
        Specification Specification = this.specificationmapper.selectByPrimaryKey(id);
        if (Specification == null) {
            throw new CommonException(ExceptionEnum.DATA_DOES_NOT_EXIST);
        }
        return Specification;
    }

}
