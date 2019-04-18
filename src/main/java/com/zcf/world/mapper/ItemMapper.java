package com.zcf.world.mapper;

import com.zcf.world.pojo.Item;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
* @author 许宝予
* @date 2019/04/18
*/
public interface ItemMapper extends Mapper<Item> {

    @Select("<script> \n" +
            "SELECT * FROM item a \n" +
            "LEFT JOIN subcategory b ON a.reclassify = b.id \n"+
            "WHERE a.deleted = 'N' "+
            "<if test='search != null'> and a.item_name LIKE CONCAT('%',#{search},'%') OR a.item_introduce LIKE CONCAT('%',#{search},'%') OR b.subcategory LIKE CONCAT('%',#{search},'%') </if>" +
            "</script>")
    List<Item> getsecrchItem(@Param("search")String search);

    //随机取出10条猜你喜欢数据
    @Select("SELECT *,RAND() as r FROM item WHERE deleted = 'N' ORDER BY r LIMIT 0,10")
    List<Item> getCountTen();

}