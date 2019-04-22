package com.zcf.world.service.layui;

import com.zcf.world.pojo.Orderlist;
import com.zcf.world.mapper.OrderlistMapper;
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
public class LayUiOrderlistService{

    @Resource
    private OrderlistMapper LayUiOrderlistMapper;

    /**
    *新增数据
    */
    public boolean add(Orderlist orderlist) {
        if (orderlist.getCreatTime() == null){
            orderlist.setCreatTime(new Date());
        }
        if (orderlist.getUpdateTime() == null){
            orderlist.setUpdateTime(new Date());
        }
        orderlist.setDeleted("N");
        return this.LayUiOrderlistMapper.insert(orderlist) == 1;
    }
    /**
    *根据主键删除数据
    */
    public boolean delete(Integer id) {
        Example example = new Example(Orderlist.class);
        example.createCriteria().andEqualTo("id",id);
        List<Orderlist> list = this.LayUiOrderlistMapper.selectByExample(example);
        if (list.size() != 1){
            return false;
        }
        Orderlist orderlist = new Orderlist();
        orderlist.setId(list.get(0).getId());
        orderlist.setDeleted("Y");
        orderlist.setUpdateTime(new Date());
        return this.update(orderlist);
    }

    /**
    *根据主键更新非空数据
    */
    public boolean update(Orderlist orderlist) {
        orderlist.setUpdateTime(new Date());
        return this.LayUiOrderlistMapper.updateByPrimaryKeySelective(orderlist) == 1;
    }

    /**
    *分页查询表中数据
    */
    public LayUiResult query(Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        List<Orderlist> list = this.LayUiOrderlistMapper.selectAll();
        return new LayUiResult("0", "查询成功", new PageInfo<>(list).getTotal(), list);
    }

    /**
    *分页并搜索关键字
    */
     public LayUiResult search(Integer page, Integer limit,String keywords) {
        Example example = new Example(Orderlist.class);
        example.createCriteria().andLike("id", "%" + keywords + "%");
        PageHelper.startPage(page, limit);
        List<Orderlist> list = this.LayUiOrderlistMapper.selectByExample(example);
        return new LayUiResult("0", "查询成功", new PageInfo<>(list).getTotal(), list);
    }
}
