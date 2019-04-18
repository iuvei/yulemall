package com.zcf.world.service.layui;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zcf.world.common.layui.LayUiResult;
import com.zcf.world.mapper.ItemMapper;
import com.zcf.world.pojo.Item;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;
/**
* @author 许宝予
* @date 2019/04/18
*/
@Service
public class LayUiItemService{

    @Resource
    private ItemMapper LayUiItemMapper;

    /**
    *新增数据
    */
    public boolean add(Item item) {
        return this.LayUiItemMapper.insert(item) == 1;
    }
    /**
    *根据主键删除数据
    */
    public boolean delete(Integer id) {
        return this.LayUiItemMapper.deleteByPrimaryKey(id) == 1;
    }

    /**
    *根据主键更新非空数据
    */
    public boolean update(Item item) {
        return this.LayUiItemMapper.updateByPrimaryKeySelective(item) == 1;
    }

    /**
    *分页查询表中数据
    */
    public LayUiResult query(Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        List<Item> list = this.LayUiItemMapper.selectAll();
        return new LayUiResult("0", "查询成功", new PageInfo<>(list).getTotal(), list);
    }

    /**
    *分页并搜索关键字
    */
     public LayUiResult search(Integer page, Integer limit,String keywords) {
        Example example = new Example(Item.class);
        example.createCriteria().andLike("id", "%" + keywords + "%");
        PageHelper.startPage(page, limit);
        List<Item> list = this.LayUiItemMapper.selectByExample(example);
        return new LayUiResult("0", "查询成功", new PageInfo<>(list).getTotal(), list);
    }
}
