package com.zcf.world.mapper;

import com.zcf.world.pojo.Searchs;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;
/**
* @author 许宝予
* @date 2019/04/19
*/
public interface SearchsMapper extends Mapper<Searchs> {

    @Update("UPDATE searchs SET deleted = 'Y' WHERE user_id = #{userId}")
    int updateUserSearchs(@Param("userId") Integer Param);

}
