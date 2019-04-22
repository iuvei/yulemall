package com.zcf.world.service.layui;

import com.zcf.world.pojo.Specification;
import com.zcf.world.mapper.SpecificationMapper;
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
public class LayUiSpecificationService{

    @Resource
    private SpecificationMapper LayUiSpecificationMapper;

    /**
    *新增数据
    */
    public boolean add(Specification specification) {
        if (specification.getCreatTime() == null){
            specification.setCreatTime(new Date());
        }
        if (specification.getUpdateTime() == null){
            specification.setUpdateTime(new Date());
        }
        specification.setDeleted("N");
        return this.LayUiSpecificationMapper.insert(specification) == 1;
    }
    /**
    *根据主键删除数据
    */
    public boolean delete(Integer id) {
        Example example = new Example(Specification.class);
        example.createCriteria().andEqualTo("id",id);
        List<Specification> list = this.LayUiSpecificationMapper.selectByExample(example);
        if (list.size() != 1){
            return false;
        }
        Specification specification = new Specification();
        specification.setId(list.get(0).getId());
        specification.setDeleted("Y");
        specification.setUpdateTime(new Date());
        return this.update(specification);
    }

    /**
    *根据主键更新非空数据
    */
    public boolean update(Specification specification) {
        specification.setUpdateTime(new Date());
        return this.LayUiSpecificationMapper.updateByPrimaryKeySelective(specification) == 1;
    }

    /**
    *分页查询表中数据
    */
    public LayUiResult query(Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        List<Specification> list = this.LayUiSpecificationMapper.selectAll();
        return new LayUiResult("0", "查询成功", new PageInfo<>(list).getTotal(), list);
    }

    /**
    *分页并搜索关键字
    */
     public LayUiResult search(Integer page, Integer limit,String keywords) {
        Example example = new Example(Specification.class);
        example.createCriteria().andLike("id", "%" + keywords + "%");
        PageHelper.startPage(page, limit);
        List<Specification> list = this.LayUiSpecificationMapper.selectByExample(example);
        return new LayUiResult("0", "查询成功", new PageInfo<>(list).getTotal(), list);
    }
}
