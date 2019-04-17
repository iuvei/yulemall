package com.zcf.world.service;

import com.zcf.world.pojo.BettingRecord;
import com.zcf.world.mapper.BettingRecordMapper;
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
public class BettingRecordService{

    @Autowired
    private BettingRecordMapper bettingRecordmapper;

    /**
     * 新增一条数据
     *
     * @param bettingRecord bettingRecord对象
     */
    public void addBettingRecord(BettingRecord bettingRecord) {
        int count = this.bettingRecordmapper.insertSelective(bettingRecord);
        if(count != 1){
             throw new CommonException(ExceptionEnum.SAVE_FAILURE);
        }
    }

    /**
     * 根据主键删除数据
     *
     * @param id 主键
     */
    public void deleteBettingRecordById(Integer id) {
        int count = this.bettingRecordmapper.deleteByPrimaryKey(id);
        if(count != 1){
             throw new CommonException(ExceptionEnum.DELETE_FAILURE);
        }
    }

    /**
     * 根据主键更新非空数据
     *
     * @param bettingRecord bettingRecord对象
     */
    public void updateBettingRecord(BettingRecord bettingRecord) {
        int count = this.bettingRecordmapper.updateByPrimaryKeySelective(bettingRecord);
         if(count != 1){
             throw new CommonException(ExceptionEnum.UPDATE_FAILURE);
        }
    }

    /**
     * 获取所有表中数据
     *
     * @return BettingRecord对象集合
     */
    public List<BettingRecord> getAllBettingRecord() {
        List<BettingRecord> list = this.bettingRecordmapper.selectAll();
       if(CollectionUtils.isEmpty(list)){
            throw new CommonException(ExceptionEnum.DATA_DOES_NOT_EXIST);
        }
        return list;
    }

    /**
     * 根据主键获取单一数据
     *
     * @param id 主键
     * @return BettingRecord对象
     */
    public BettingRecord getBettingRecord(Integer id){
        BettingRecord BettingRecord = this.bettingRecordmapper.selectByPrimaryKey(id);
        if (BettingRecord == null) {
            throw new CommonException(ExceptionEnum.DATA_DOES_NOT_EXIST);
        }
        return BettingRecord;
    }

}
