package com.zcf.world.mapper;

import com.zcf.world.pojo.Youlike;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
* @author 许宝予
* @date 2019/04/19
*/
public interface YoulikeMapper extends Mapper<Youlike> {


    @Select("SELECT *,RAND() as r FROM youlike WHERE deleted = 'N' ORDER BY r LIMIT 0,8")
    List<Youlike> getYoulikeShow();

}
