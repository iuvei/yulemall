package com.zcf.world.service.layui;

import com.zcf.world.pojo.PlayOptions;
import com.zcf.world.mapper.PlayOptionsMapper;
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
* @date 2019/04/24
*/
@Service
public class LayUiPlayOptionsService{

    @Resource
    private PlayOptionsMapper LayUiPlayOptionsMapper;

    /**
    *新增数据
    */
    public boolean add(PlayOptions playOptions) {
        if (playOptions.getCreatTime() == null){
            playOptions.setCreatTime(new Date());
        }
        if (playOptions.getUpdateTime() == null){
            playOptions.setUpdateTime(new Date());
        }
        playOptions.setDeleted("N");
        return this.LayUiPlayOptionsMapper.insert(playOptions) == 1;
    }
    /**
    *根据主键删除数据
    */
    public boolean delete(Integer id) {
        Example example = new Example(PlayOptions.class);
        example.createCriteria().andEqualTo("id",id);
        List<PlayOptions> list = this.LayUiPlayOptionsMapper.selectByExample(example);
        if (list.size() != 1){
            return false;
        }
        PlayOptions playOptions = new PlayOptions();
        playOptions.setId(list.get(0).getId());
        playOptions.setDeleted("Y");
        playOptions.setUpdateTime(new Date());
        return this.update(playOptions);
    }

    /**
    *根据主键更新非空数据
    */
    public boolean update(PlayOptions playOptions) {
        playOptions.setUpdateTime(new Date());
        return this.LayUiPlayOptionsMapper.updateByPrimaryKeySelective(playOptions) == 1;
    }

    /**
    *分页查询表中数据
    */
    public LayUiResult query(Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        List<PlayOptions> list = this.LayUiPlayOptionsMapper.selectAll();
        return new LayUiResult("0", "查询成功", new PageInfo<>(list).getTotal(), list);
    }

    /**
    *分页并搜索关键字
    */
     public LayUiResult search(Integer page, Integer limit,String keywords) {
        Example example = new Example(PlayOptions.class);
        example.createCriteria().andLike("id", "%" + keywords + "%");
        PageHelper.startPage(page, limit);
        List<PlayOptions> list = this.LayUiPlayOptionsMapper.selectByExample(example);
        return new LayUiResult("0", "查询成功", new PageInfo<>(list).getTotal(), list);
    }
}
