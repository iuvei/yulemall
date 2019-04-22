package com.zcf.world.service;

import com.zcf.world.pojo.SpecificationParam;
import com.zcf.world.mapper.SpecificationParamMapper;
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
public class SpecificationParamService{

    @Resource
    private SpecificationParamMapper specificationParammapper;

    /**
     * 新增一条数据
     *
     * @param specificationParam specificationParam对象
     */
    public void addSpecificationParam(SpecificationParam specificationParam) {
        if (specificationParam.getCreatTime() == null){
            specificationParam.setCreatTime(new Date());
        }
        if (specificationParam.getUpdateTime() == null){
            specificationParam.setUpdateTime(new Date());
        }
        specificationParam.setDeleted("N");
        int count = this.specificationParammapper.insertSelective(specificationParam);
        if(count != 1){
             throw new CommonException(ExceptionEnum.SAVE_FAILURE);
        }
    }

    /**
     * 根据主键删除数据
     *
     * @param id 主键
     */
    public void deleteSpecificationParamById(Integer id) {

        Example example = new Example(SpecificationParam.class);
        example.createCriteria().andEqualTo("id",id);
        List<SpecificationParam> list = this.specificationParammapper.selectByExample(example);
        if (list.size() != 1){
            throw new CommonException(ExceptionEnum.NULL_LIST);
        }
        SpecificationParam specificationParam = new SpecificationParam();
        specificationParam.setId(list.get(0).getId());
        specificationParam.setDeleted("Y");
        specificationParam.setUpdateTime(new Date());
        updateSpecificationParam(specificationParam);
    }

    /**
     * 根据主键更新非空数据
     *
     * @param specificationParam specificationParam对象
     */
    public void updateSpecificationParam(SpecificationParam specificationParam) {
        specificationParam.setUpdateTime(new Date());
        int count = this.specificationParammapper.updateByPrimaryKeySelective(specificationParam);
        if(count != 1){
             throw new CommonException(ExceptionEnum.UPDATE_FAILURE);
        }
    }

    /**
     * 获取所有表中数据
     *
     * @return SpecificationParam对象集合
     */
    public List<SpecificationParam> getAllSpecificationParam() {
        Example example = new Example(SpecificationParam.class);
        example.createCriteria().andEqualTo("deleted","N");
        List<SpecificationParam> list = this.specificationParammapper.selectByExample(example);
        if(CollectionUtils.isEmpty(list)){
            throw new CommonException(ExceptionEnum.DATA_DOES_NOT_EXIST);
        }
        return list;
    }

    /**
     * 根据主键获取单一数据
     *
     * @param id 主键
     * @return SpecificationParam对象
     */
    public SpecificationParam getSpecificationParam(Integer id){
        SpecificationParam SpecificationParam = this.specificationParammapper.selectByPrimaryKey(id);
        if (SpecificationParam == null) {
            throw new CommonException(ExceptionEnum.DATA_DOES_NOT_EXIST);
        }
        return SpecificationParam;
    }

}
