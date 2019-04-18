package com.zcf.world.service;

import com.zcf.world.pojo.LotteryRecord;
import com.zcf.world.mapper.LotteryRecordMapper;
import tk.mybatis.mapper.entity.Example;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import com.zcf.world.common.exception.CommonException;
import com.zcf.world.common.exception.ExceptionEnum;

import javax.annotation.Resource;
import java.util.List;
/**
* @author 许宝予
* @date 2019/04/17
*/
@Service
public class LotteryRecordService{

    @Resource
    private LotteryRecordMapper lotteryRecordmapper;

    /**
     * 新增一条数据
     *
     * @param lotteryRecord lotteryRecord对象
     */
    public void addLotteryRecord(LotteryRecord lotteryRecord) {
        int count = this.lotteryRecordmapper.insertSelective(lotteryRecord);
        if(count != 1){
             throw new CommonException(ExceptionEnum.SAVE_FAILURE);
        }
    }

    /**
     * 根据主键删除数据
     *
     * @param id 主键
     */
    public void deleteLotteryRecordById(Integer id) {
        int count = this.lotteryRecordmapper.deleteByPrimaryKey(id);
        if(count != 1){
             throw new CommonException(ExceptionEnum.DELETE_FAILURE);
        }
    }

    /**
     * 根据主键更新非空数据
     *
     * @param lotteryRecord lotteryRecord对象
     */
    public void updateLotteryRecord(LotteryRecord lotteryRecord) {
        int count = this.lotteryRecordmapper.updateByPrimaryKeySelective(lotteryRecord);
         if(count != 1){
             throw new CommonException(ExceptionEnum.UPDATE_FAILURE);
        }
    }

    /**
     * 获取所有表中数据
     *
     * @return LotteryRecord对象集合
     */
    public List<LotteryRecord> getAllLotteryRecord() {
        List<LotteryRecord> list = this.lotteryRecordmapper.selectAll();
       if(CollectionUtils.isEmpty(list)){
            throw new CommonException(ExceptionEnum.DATA_DOES_NOT_EXIST);
        }
        return list;
    }

    /**
     * 根据主键获取单一数据
     *
     * @param id 主键
     * @return LotteryRecord对象
     */
    public LotteryRecord getLotteryRecord(Integer id){
        LotteryRecord LotteryRecord = this.lotteryRecordmapper.selectByPrimaryKey(id);
        if (LotteryRecord == null) {
            throw new CommonException(ExceptionEnum.DATA_DOES_NOT_EXIST);
        }
        return LotteryRecord;
    }

}
