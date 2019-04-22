package com.zcf.world.service;

import com.zcf.world.DTO.CommentsDTO;
import com.zcf.world.common.utils.Body;
import com.zcf.world.pojo.Comments;
import com.zcf.world.mapper.CommentsMapper;
import tk.mybatis.mapper.entity.Example;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import com.zcf.world.common.exception.CommonException;
import com.zcf.world.common.exception.ExceptionEnum;
import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @author 许宝予
* @date 2019/04/22
*/
@Service
public class CommentsService{

    @Resource
    private CommentsMapper commentsmapper;

    /**
     * 新增一条数据
     *
     * @param comments comments对象
     */
    public void addComments(Comments comments) {
        if (comments.getCreatTime() == null){
            comments.setCreatTime(new Date());
        }
        if (comments.getUpdateTime() == null){
            comments.setUpdateTime(new Date());
        }
        comments.setDeleted("N");
        int count = this.commentsmapper.insertSelective(comments);
        if(count != 1){
             throw new CommonException(ExceptionEnum.SAVE_FAILURE);
        }
    }

    /**
     * 根据主键删除数据
     *
     * @param id 主键
     */
    public void deleteCommentsById(Integer id) {

        Example example = new Example(Comments.class);
        example.createCriteria().andEqualTo("id",id);
        List<Comments> list = this.commentsmapper.selectByExample(example);
        if (list.size() != 1){
            throw new CommonException(ExceptionEnum.NULL_LIST);
        }
        Comments comments = new Comments();
        comments.setId(list.get(0).getId());
        comments.setDeleted("Y");
        comments.setUpdateTime(new Date());
        updateComments(comments);
    }

    /**
     * 根据主键更新非空数据
     *
     * @param comments comments对象
     */
    public void updateComments(Comments comments) {
        comments.setUpdateTime(new Date());
        int count = this.commentsmapper.updateByPrimaryKeySelective(comments);
        if(count != 1){
             throw new CommonException(ExceptionEnum.UPDATE_FAILURE);
        }
    }

    /**
     * 获取所有表中数据
     *
     * @return Comments对象集合
     */
    public List<Comments> getAllComments() {
        Example example = new Example(Comments.class);
        example.createCriteria().andEqualTo("deleted","N");

        List<Comments> list = this.commentsmapper.selectByExample(example);
        if(CollectionUtils.isEmpty(list)){
            throw new CommonException(ExceptionEnum.DATA_DOES_NOT_EXIST);
        }
        return list;
    }

    /**
     * 根据主键获取单一数据
     *
     * @param id 主键
     * @return Comments对象
     */
    public Comments getComments(Integer id){
        Comments Comments = this.commentsmapper.selectByPrimaryKey(id);
        if (Comments == null) {
            throw new CommonException(ExceptionEnum.DATA_DOES_NOT_EXIST);
        }
        return Comments;
    }

    /**
     * 根据商品id 查询商品评论
     */
    public Body getItemComments(Integer itemId) {
        Map map = new HashMap();
        List<CommentsDTO> userComments = commentsmapper.getUserComments(itemId);
        map.put("userComments",userComments);
        map.put("count",userComments.size());
        return Body.newInstance(map);
    }

}
