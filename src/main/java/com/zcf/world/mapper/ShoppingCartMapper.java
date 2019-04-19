package com.zcf.world.mapper;

import com.zcf.world.DTO.ShoppingCartDTO;
import com.zcf.world.pojo.ShoppingCart;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
* @author 许宝予
* @date 2019/04/19
*/
public interface ShoppingCartMapper extends Mapper<ShoppingCart> {

    @Select("SELECT a.*,b.item_name shopName,b.selling_price sellingPrice,b.images images,d.specification spec,c.sp_names spNames FROM shopping_cart a\n" +
            "LEFT JOIN item b ON a.item_id = b.id\n" +
            "LEFT JOIN specification_param c ON a.sp_id = c.id\n" +
            "LEFT JOIN specification d ON c.specification_id = d.id\n" +
            "LEFT JOIN `user` e ON a.user_id = e.id\n" +
            "WHERE e.id = #{userId}")
    List<ShoppingCartDTO> getAllShopDTO(Integer userId);

}
