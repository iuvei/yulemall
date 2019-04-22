package com.zcf.world.service.layui;

import com.zcf.world.pojo.SpecificationParam;
import com.zcf.world.mapper.SpecificationParamMapper;
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
* @date 2019/04/22
*/
@Service
public class LayUiSpecificationParamService{

    @Resource
    private SpecificationParamMapper LayUiSpecificationParamMapper;

    /**
    *新增数据
    */
    public boolean add(SpecificationParam specificationParam) {
        if (specificationParam.getCreatTime() == null){
            specificationParam.setCreatTime(new Date());
        }
        if (specificationParam.getUpdateTime() == null){
            specificationParam.setUpdateTime(new Date());
        }
        specificationParam.setDeleted("N");
        return this.LayUiSpecificationParamMapper.insert(specificationParam) == 1;
    }
    /**
    *根据主键删除数据
    */
    public boolean delete(Integer id) {
        Example example = new Example(SpecificationParam.class);
        example.createCriteria().andEqualTo("id",id);
        List<SpecificationParam> list = this.LayUiSpecificationParamMapper.selectByExample(example);
        if (list.size() != 1){
            return false;
        }
        SpecificationParam specificationParam = new SpecificationParam();
        specificationParam.setId(list.get(0).getId());
        specificationParam.setDeleted("Y");
        specificationParam.setUpdateTime(new Date());
        return this.update(specificationParam);
    }

    /**
    *根据主键更新非空数据
    */
    public boolean update(SpecificationParam specificationParam) {
        specificationParam.setUpdateTime(new Date());
        return this.LayUiSpecificationParamMapper.updateByPrimaryKeySelective(specificationParam) == 1;
    }

    /**
    *分页查询表中数据
    */
    public LayUiResult query(Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        List<SpecificationParam> list = this.LayUiSpecificationParamMapper.selectAll();
        return new LayUiResult("0", "查询成功", new PageInfo<>(list).getTotal(), list);
    }

    /**
    *分页并搜索关键字
    */
     public LayUiResult search(Integer page, Integer limit,String keywords) {
        Example example = new Example(SpecificationParam.class);
        example.createCriteria().andLike("id", "%" + keywords + "%");
        PageHelper.startPage(page, limit);
        List<SpecificationParam> list = this.LayUiSpecificationParamMapper.selectByExample(example);
        return new LayUiResult("0", "查询成功", new PageInfo<>(list).getTotal(), list);
    }
}
