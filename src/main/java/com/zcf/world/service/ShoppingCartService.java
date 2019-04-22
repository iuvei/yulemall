package com.zcf.world.service;

import com.zcf.world.DTO.ShoppingCartDTO;
import com.zcf.world.DTO.SpecDTO;
import com.zcf.world.common.utils.Body;
import com.zcf.world.pojo.ShoppingCart;
import com.zcf.world.mapper.ShoppingCartMapper;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import com.zcf.world.common.exception.CommonException;
import com.zcf.world.common.exception.ExceptionEnum;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
/**
* @author 许宝予
* @date 2019/04/19
*/
@Service
public class ShoppingCartService{

    @Resource
    private ShoppingCartMapper shoppingCartmapper;

    /**
     * 新增一条数据
     *
     * @param shoppingCart shoppingCart对象
     */
    public void addShoppingCart(ShoppingCart shoppingCart) {
        if (shoppingCart.getCreatTime() == null){
            shoppingCart.setCreatTime(new Date());
        }
        if (shoppingCart.getUpdateTime() == null){
            shoppingCart.setUpdateTime(new Date());
        }
        shoppingCart.setDeleted("N");
        int count = this.shoppingCartmapper.insertSelective(shoppingCart);
        if(count != 1){
             throw new CommonException(ExceptionEnum.SAVE_FAILURE);
        }
    }

    /**
     * 根据主键删除数据
     *
     * @param id 主键
     */
    public void deleteShoppingCartById(Integer id) {

        Example example = new Example(ShoppingCart.class);
        example.createCriteria().andEqualTo("id",id);
        List<ShoppingCart> list = this.shoppingCartmapper.selectByExample(example);
        if (list.size() != 1){
            throw new CommonException(ExceptionEnum.NULL_LIST);
        }
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setId(list.get(0).getId());
        shoppingCart.setDeleted("Y");
        shoppingCart.setUpdateTime(new Date());
        updateShoppingCart(shoppingCart);
    }

    /**
     * 根据主键更新非空数据
     *
     * @param shoppingCart shoppingCart对象
     */
    public void updateShoppingCart(ShoppingCart shoppingCart) {
        shoppingCart.setUpdateTime(new Date());
        int count = this.shoppingCartmapper.updateByPrimaryKeySelective(shoppingCart);
        if(count != 1){
             throw new CommonException(ExceptionEnum.UPDATE_FAILURE);
        }
    }

    /**
     * 获取所有表中数据
     *
     * @return ShoppingCart对象集合
     */
    public List<ShoppingCart> getAllShoppingCart() {
        Example example = new Example(ShoppingCart.class);
        example.createCriteria().andEqualTo("deleted","N");
        List<ShoppingCart> list = this.shoppingCartmapper.selectByExample(example);
        if(CollectionUtils.isEmpty(list)){
            throw new CommonException(ExceptionEnum.DATA_DOES_NOT_EXIST);
        }
        return list;
    }

    /**
     * 根据主键获取单一数据
     *
     * @param id 主键
     * @return ShoppingCart对象
     */
    public ShoppingCart getShoppingCart(Integer id){
        ShoppingCart ShoppingCart = this.shoppingCartmapper.selectByPrimaryKey(id);
        if (ShoppingCart == null) {
            throw new CommonException(ExceptionEnum.DATA_DOES_NOT_EXIST);
        }
        return ShoppingCart;
    }

    /**
     * 根据用户id 查询 购物车
     */
    public List<ShoppingCartDTO> getShopDTO(Integer userId){
        List<ShoppingCartDTO> allShopDTO = shoppingCartmapper.getAllShopDTO(userId);
        List<ShoppingCartDTO> shoppingCartDTOS = new ArrayList<>();
        List a = new ArrayList();
        if (!StringUtils.isEmpty(allShopDTO)){
            for (int i = 0;i<allShopDTO.size();i++){
                ShoppingCartDTO shoppingCartDTO = allShopDTO.get(i);
                String[] menus = allShopDTO.get(i).getSpId().split(",");
                for (int y = 0;y<menus.length;y++){
                    List<SpecDTO> spec = shoppingCartmapper.getSpec(menus[y]);
                    a.add(spec);
                }
                shoppingCartDTO.setSpecList(a);
                shoppingCartDTOS.add(shoppingCartDTO);
            }
        }
        return shoppingCartDTOS;
    }

    /**
     * 购物车数量加减
     */
    public Body updateShoppingCartNum(Integer id, Integer or){
        if (or == 1){
            int i = shoppingCartmapper.updateNumAdd(id);
            if (i>0){
                return Body.newInstance(200);
            }else {
                return Body.newInstance(100,"未知错误，请联系管理");
            }
        }else {
            int i = shoppingCartmapper.updateNumSubtract(id);
            if (i>0){
                return Body.newInstance(200);
            }else {
                return Body.newInstance(100,"未知错误，请联系管理");
            }
        }
    }

}
