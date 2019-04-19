package com.zcf.world.service;

import com.zcf.world.pojo.Youlike;
import com.zcf.world.mapper.YoulikeMapper;
import tk.mybatis.mapper.entity.Example;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import com.zcf.world.common.exception.CommonException;
import com.zcf.world.common.exception.ExceptionEnum;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
/**
* @author 许宝予
* @date 2019/04/19
*/
@Service
public class YoulikeService{

    @Resource
    private YoulikeMapper youlikemapper;

    /**
     * 新增一条数据
     *
     * @param youlike youlike对象
     */
    public void addYoulike(Youlike youlike) {
        if (youlike.getCreatTime() == null){
            youlike.setCreatTime(new Date());
        }
        if (youlike.getUpdateTime() == null){
            youlike.setUpdateTime(new Date());
        }
        youlike.setDeleted("N");
        int count = this.youlikemapper.insertSelective(youlike);
        if(count != 1){
             throw new CommonException(ExceptionEnum.SAVE_FAILURE);
        }
    }

    /**
     * 根据主键删除数据
     *
     * @param id 主键
     */
    public void deleteYoulikeById(Integer id) {

        Example example = new Example(Youlike.class);
        example.createCriteria().andEqualTo("id",id);
        List<Youlike> list = this.youlikemapper.selectByExample(example);
        if (list.size() != 1){
            throw new CommonException(ExceptionEnum.NULL_LIST);
        }
        Youlike youlike = new Youlike();
        youlike.setId(list.get(0).getId());
        youlike.setDeleted("Y");
        youlike.setUpdateTime(new Date());
        updateYoulike(youlike);
    }

    /**
     * 根据主键更新非空数据
     *
     * @param youlike youlike对象
     */
    public void updateYoulike(Youlike youlike) {
        youlike.setUpdateTime(new Date());
        int count = this.youlikemapper.updateByPrimaryKeySelective(youlike);
        if(count != 1){
             throw new CommonException(ExceptionEnum.UPDATE_FAILURE);
        }
    }

    /**
     * 获取所有表中数据
     *
     * @return Youlike对象集合
     */
    public List<Youlike> getAllYoulike() {
        Example example = new Example(Youlike.class);
        example.createCriteria().andEqualTo("deleted","N");
        List<Youlike> list = this.youlikemapper.selectByExample(example);
        if(CollectionUtils.isEmpty(list)){
            throw new CommonException(ExceptionEnum.DATA_DOES_NOT_EXIST);
        }
        return list;
    }

    /**
     * 根据主键获取单一数据
     *
     * @param id 主键
     * @return Youlike对象
     */
    public Youlike getYoulike(Integer id){
        Youlike Youlike = this.youlikemapper.selectByPrimaryKey(id);
        if (Youlike == null) {
            throw new CommonException(ExceptionEnum.DATA_DOES_NOT_EXIST);
        }
        return Youlike;
    }

    /**
     * 猜你喜欢 取八条数据  随机
     */
    public List<Youlike> getYoulikeShow(){
        List<Youlike> youlikeShow = youlikemapper.getYoulikeShow();
        return youlikeShow;
    }


}
