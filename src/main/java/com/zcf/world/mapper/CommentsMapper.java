package com.zcf.world.mapper;

import com.zcf.world.DTO.CommentsDTO;
import com.zcf.world.pojo.Comments;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
* @author 许宝予
* @date 2019/04/22
*/
public interface CommentsMapper extends Mapper<Comments> {
    @Select("SELECT a.*,b.nickname userName,b.img img FROM comments a\n" +
            "LEFT JOIN `user` b ON a.user_id = b.id\n" +
            "WHERE a.user_id = #{itemId}")
    List<CommentsDTO> getUserComments(@Param("itemId") Integer itemId);

}
