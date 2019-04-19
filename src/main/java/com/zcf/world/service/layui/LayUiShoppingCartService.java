package com.zcf.world.service.layui;

import com.zcf.world.pojo.ShoppingCart;
import com.zcf.world.mapper.ShoppingCartMapper;
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
public class LayUiShoppingCartService{

    @Resource
    private ShoppingCartMapper LayUiShoppingCartMapper;

    /**
    *新增数据
    */
    public boolean add(ShoppingCart shoppingCart) {
        if (shoppingCart.getCreatTime() == null){
            shoppingCart.setCreatTime(new Date());
        }
        if (shoppingCart.getUpdateTime() == null){
            shoppingCart.setUpdateTime(new Date());
        }
        shoppingCart.setDeleted("N");
        return this.LayUiShoppingCartMapper.insert(shoppingCart) == 1;
    }
    /**
    *根据主键删除数据
    */
    public boolean delete(Integer id) {
        Example example = new Example(ShoppingCart.class);
        example.createCriteria().andEqualTo("id",id);
        List<ShoppingCart> list = this.LayUiShoppingCartMapper.selectByExample(example);
        if (list.size() != 1){
            return false;
        }
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setId(list.get(0).getId());
        shoppingCart.setDeleted("Y");
        shoppingCart.setUpdateTime(new Date());
        return this.update(shoppingCart);
    }

    /**
    *根据主键更新非空数据
    */
    public boolean update(ShoppingCart shoppingCart) {
        shoppingCart.setUpdateTime(new Date());
        return this.LayUiShoppingCartMapper.updateByPrimaryKeySelective(shoppingCart) == 1;
    }

    /**
    *分页查询表中数据
    */
    public LayUiResult query(Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        List<ShoppingCart> list = this.LayUiShoppingCartMapper.selectAll();
        return new LayUiResult("0", "查询成功", new PageInfo<>(list).getTotal(), list);
    }

    /**
    *分页并搜索关键字
    */
     public LayUiResult search(Integer page, Integer limit,String keywords) {
        Example example = new Example(ShoppingCart.class);
        example.createCriteria().andLike("id", "%" + keywords + "%");
        PageHelper.startPage(page, limit);
        List<ShoppingCart> list = this.LayUiShoppingCartMapper.selectByExample(example);
        return new LayUiResult("0", "查询成功", new PageInfo<>(list).getTotal(), list);
    }
}
