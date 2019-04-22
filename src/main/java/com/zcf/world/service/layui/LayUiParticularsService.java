package com.zcf.world.service.layui;

import com.zcf.world.pojo.Particulars;
import com.zcf.world.mapper.ParticularsMapper;
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
public class LayUiParticularsService{

    @Resource
    private ParticularsMapper LayUiParticularsMapper;

    /**
    *新增数据
    */
    public boolean add(Particulars particulars) {
        if (particulars.getCreatTime() == null){
            particulars.setCreatTime(new Date());
        }
        if (particulars.getUpdateTime() == null){
            particulars.setUpdateTime(new Date());
        }
        particulars.setDeleted("N");
        return this.LayUiParticularsMapper.insert(particulars) == 1;
    }
    /**
    *根据主键删除数据
    */
    public boolean delete(Integer id) {
        Example example = new Example(Particulars.class);
        example.createCriteria().andEqualTo("id",id);
        List<Particulars> list = this.LayUiParticularsMapper.selectByExample(example);
        if (list.size() != 1){
            return false;
        }
        Particulars particulars = new Particulars();
        particulars.setId(list.get(0).getId());
        particulars.setDeleted("Y");
        particulars.setUpdateTime(new Date());
        return this.update(particulars);
    }

    /**
    *根据主键更新非空数据
    */
    public boolean update(Particulars particulars) {
        particulars.setUpdateTime(new Date());
        return this.LayUiParticularsMapper.updateByPrimaryKeySelective(particulars) == 1;
    }

    /**
    *分页查询表中数据
    */
    public LayUiResult query(Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        List<Particulars> list = this.LayUiParticularsMapper.selectAll();
        return new LayUiResult("0", "查询成功", new PageInfo<>(list).getTotal(), list);
    }

    /**
    *分页并搜索关键字
    */
     public LayUiResult search(Integer page, Integer limit,String keywords) {
        Example example = new Example(Particulars.class);
        example.createCriteria().andLike("id", "%" + keywords + "%");
        PageHelper.startPage(page, limit);
        List<Particulars> list = this.LayUiParticularsMapper.selectByExample(example);
        return new LayUiResult("0", "查询成功", new PageInfo<>(list).getTotal(), list);
    }
}
