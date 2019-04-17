package com.zcf.world.service;

import com.zcf.world.pojo.InterruptSign;
import com.zcf.world.mapper.InterruptSignMapper;
import tk.mybatis.mapper.entity.Example;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import com.zcf.world.common.exception.CommonException;
import com.zcf.world.common.exception.ExceptionEnum;
import java.util.List;
/**
* @author 许宝予
* @date 2019/04/17
*/
@Service
public class InterruptSignService{

    @Autowired
    private InterruptSignMapper interruptSignmapper;

    /**
     * 新增一条数据
     *
     * @param interruptSign interruptSign对象
     */
    public void addInterruptSign(InterruptSign interruptSign) {
        int count = this.interruptSignmapper.insertSelective(interruptSign);
        if(count != 1){
             throw new CommonException(ExceptionEnum.SAVE_FAILURE);
        }
    }

    /**
     * 根据主键删除数据
     *
     * @param id 主键
     */
    public void deleteInterruptSignById(Integer id) {
        int count = this.interruptSignmapper.deleteByPrimaryKey(id);
        if(count != 1){
             throw new CommonException(ExceptionEnum.DELETE_FAILURE);
        }
    }

    /**
     * 根据主键更新非空数据
     *
     * @param interruptSign interruptSign对象
     */
    public void updateInterruptSign(InterruptSign interruptSign) {
        int count = this.interruptSignmapper.updateByPrimaryKeySelective(interruptSign);
         if(count != 1){
             throw new CommonException(ExceptionEnum.UPDATE_FAILURE);
        }
    }

    /**
     * 获取所有表中数据
     *
     * @return InterruptSign对象集合
     */
    public List<InterruptSign> getAllInterruptSign() {
        List<InterruptSign> list = this.interruptSignmapper.selectAll();
       if(CollectionUtils.isEmpty(list)){
            throw new CommonException(ExceptionEnum.DATA_DOES_NOT_EXIST);
        }
        return list;
    }

    /**
     * 根据主键获取单一数据
     *
     * @param id 主键
     * @return InterruptSign对象
     */
    public InterruptSign getInterruptSign(Integer id){
        InterruptSign InterruptSign = this.interruptSignmapper.selectByPrimaryKey(id);
        if (InterruptSign == null) {
            throw new CommonException(ExceptionEnum.DATA_DOES_NOT_EXIST);
        }
        return InterruptSign;
    }

}
