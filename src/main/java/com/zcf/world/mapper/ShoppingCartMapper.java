package com.zcf.world.mapper;

import com.zcf.world.DTO.ShoppingCartDTO;
import com.zcf.world.DTO.SpecDTO;
import com.zcf.world.pojo.ShoppingCart;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
* @author 许宝予
* @date 2019/04/19
*/
public interface ShoppingCartMapper extends Mapper<ShoppingCart> {

    @Select("SELECT a.*,b.item_name shopName,b.selling_price sellingPrice,b.images images FROM shopping_cart a\n" +
            "LEFT JOIN item b ON a.item_id = b.id\n" +
            "LEFT JOIN specification_param c ON a.sp_id = c.id\n" +
            "LEFT JOIN specification d ON c.specification_id = d.id\n" +
            "LEFT JOIN `user` e ON a.user_id = e.id\n" +
            "WHERE e.id = #{userId}")
    List<ShoppingCartDTO> getAllShopDTO(@Param("userId")Integer userId);

    @Select("SELECT b.specification spec,a.sp_names spnames FROM specification_param a\n" +
            "LEFT JOIN specification b ON a.specification_id = b.id\n" +
            "WHERE a.id = #{id}")
    List<SpecDTO> getSpec(@Param("id") String id);

    @Update("UPDATE shopping_cart SET num = num+1 WHERE id = #{id}")
    int updateNumAdd(@Param("id") Integer id);

    @Update("UPDATE shopping_cart SET num = num-1 WHERE id = #{id}")
    int updateNumSubtract(@Param("id") Integer id);

}
