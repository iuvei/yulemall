package com.zcf.world.service.layui;

import com.zcf.world.pojo.Youlike;
import com.zcf.world.mapper.YoulikeMapper;
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
* @date 2019/04/19
*/
@Service
public class LayUiYoulikeService{

    @Resource
    private YoulikeMapper LayUiYoulikeMapper;

    /**
    *新增数据
    */
    public boolean add(Youlike youlike) {
        if (youlike.getCreatTime() == null){
            youlike.setCreatTime(new Date());
        }
        if (youlike.getUpdateTime() == null){
            youlike.setUpdateTime(new Date());
        }
        youlike.setDeleted("N");
        return this.LayUiYoulikeMapper.insert(youlike) == 1;
    }
    /**
    *根据主键删除数据
    */
    public boolean delete(Integer id) {
        Example example = new Example(Youlike.class);
        example.createCriteria().andEqualTo("id",id);
        List<Youlike> list = this.LayUiYoulikeMapper.selectByExample(example);
        if (list.size() != 1){
            return false;
        }
        Youlike youlike = new Youlike();
        youlike.setId(list.get(0).getId());
        youlike.setDeleted("Y");
        youlike.setUpdateTime(new Date());
        return this.update(youlike);
    }

    /**
    *根据主键更新非空数据
    */
    public boolean update(Youlike youlike) {
        youlike.setUpdateTime(new Date());
        return this.LayUiYoulikeMapper.updateByPrimaryKeySelective(youlike) == 1;
    }

    /**
    *分页查询表中数据
    */
    public LayUiResult query(Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        List<Youlike> list = this.LayUiYoulikeMapper.selectAll();
        return new LayUiResult("0", "查询成功", new PageInfo<>(list).getTotal(), list);
    }

    /**
    *分页并搜索关键字
    */
     public LayUiResult search(Integer page, Integer limit,String keywords) {
        Example example = new Example(Youlike.class);
        example.createCriteria().andLike("id", "%" + keywords + "%");
        PageHelper.startPage(page, limit);
        List<Youlike> list = this.LayUiYoulikeMapper.selectByExample(example);
        return new LayUiResult("0", "查询成功", new PageInfo<>(list).getTotal(), list);
    }
}
