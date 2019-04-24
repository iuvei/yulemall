package com.zcf.world.mapper;

import com.zcf.world.pojo.SignRecord;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

import java.util.Date;

/**
* @author 许宝予
* @date 2019/04/23
*/
public interface SignRecordMapper extends Mapper<SignRecord> {

    //删除上个月的数据
    @Delete("DELETE FROM sign_record WHERE months = #{month}")
    int deleteSignRecord(@Param("month")String month);

    @Update("UPDATE sign_record SET type = '1' WHERE id = #{id}")
    int updateTypeOfSign(@Param("id")Integer id);


    @Update("UPDATE sign_record SET type = '3' WHERE id = #{id}")
    int updateTypeDQ(@Param("id")Integer id);
}
