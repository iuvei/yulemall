package com.zcf.world.service;

import com.zcf.world.common.exception.CommonException;
import com.zcf.world.common.exception.ExceptionEnum;
import com.zcf.world.mapper.SignRecordMapper;
import com.zcf.world.pojo.SignRecord;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
/**
* @author 许宝予
* @date 2019/04/17
*/
@Service
public class SignRecordService{

    @Resource
    private SignRecordMapper signRecordmapper;

    /**
     * 新增一条数据
     *
     * @param signRecord signRecord对象
     */
    public void addSignRecord(SignRecord signRecord) {
        Date now=new Date();
        if (now.getDate()==1){

        }



        //生成一条签到记录
        signRecord.setCreatTime(now);
        signRecord.setDeleted("N");
        int count = this.signRecordmapper.insertSelective(signRecord);
        if(count != 1){
             throw new CommonException(ExceptionEnum.SAVE_FAILURE);
        }
    }

    /**
     * 根据主键删除数据
     *
     * @param id 主键
     */
    public void deleteSignRecordById(Integer id) {
        int count = this.signRecordmapper.deleteByPrimaryKey(id);
        if(count != 1){
             throw new CommonException(ExceptionEnum.DELETE_FAILURE);
        }
    }

    /**
     * 根据主键更新非空数据
     *
     * @param signRecord signRecord对象
     */
    public void updateSignRecord(SignRecord signRecord) {
        int count = this.signRecordmapper.updateByPrimaryKeySelective(signRecord);
         if(count != 1){
             throw new CommonException(ExceptionEnum.UPDATE_FAILURE);
        }
    }

    /**
     * 获取所有表中数据
     *
     * @return SignRecord对象集合
     */
    public List<SignRecord> getAllSignRecord() {
        List<SignRecord> list = this.signRecordmapper.selectAll();
       if(CollectionUtils.isEmpty(list)){
            throw new CommonException(ExceptionEnum.DATA_DOES_NOT_EXIST);
        }
        return list;
    }

    /**
     * 根据主键获取单一数据
     *
     * @param id 主键
     * @return SignRecord对象
     */
    public SignRecord getSignRecord(Integer id){
        SignRecord SignRecord = this.signRecordmapper.selectByPrimaryKey(id);
        if (SignRecord == null) {
            throw new CommonException(ExceptionEnum.DATA_DOES_NOT_EXIST);
        }
        return SignRecord;
    }

    public static void main(String[] args) {

    }
}
