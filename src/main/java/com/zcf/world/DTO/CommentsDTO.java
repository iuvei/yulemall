package com.zcf.world.DTO;

import com.zcf.world.pojo.Comments;
import lombok.Data;

@Data
public class CommentsDTO extends Comments {

    //用户昵称
    private String userName;
    //用户头像
    private String img;

}
