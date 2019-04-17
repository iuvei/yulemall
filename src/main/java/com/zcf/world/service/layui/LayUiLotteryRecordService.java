package com.zcf.world.service.layui;

import com.zcf.world.pojo.LotteryRecord;
import com.zcf.world.mapper.LotteryRecordMapper;
import com.zcf.world.common.layui.LayUiResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import tk.mybatis.mapper.entity.Example;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
/**
* @author 许宝予
* @date 2019/04/17
*/
@Service
public class LayUiLotteryRecordService{

    @Autowired
    private LotteryRecordMapper LayUiLotteryRecordMapper;

    /**
    *新增数据
    */
    public boolean add(LotteryRecord lotteryRecord) {
        return this.LayUiLotteryRecordMapper.insert(lotteryRecord) == 1;
    }
    /**
    *根据主键删除数据
    */
    public boolean delete(Integer id) {
        return this.LayUiLotteryRecordMapper.deleteByPrimaryKey(id) == 1;
    }

    /**
    *根据主键更新非空数据
    */
    public boolean update(LotteryRecord lotteryRecord) {
        return this.LayUiLotteryRecordMapper.updateByPrimaryKeySelective(lotteryRecord) == 1;
    }

    /**
    *分页查询表中数据
    */
    public LayUiResult query(Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        List<LotteryRecord> list = this.LayUiLotteryRecordMapper.selectAll();
        return new LayUiResult("0", "查询成功", new PageInfo<>(list).getTotal(), list);
    }

    /**
    *分页并搜索关键字
    */
     public LayUiResult search(Integer page, Integer limit,String keywords) {
        Example example = new Example(LotteryRecord.class);
        example.createCriteria().andLike("id", "%" + keywords + "%");
        PageHelper.startPage(page, limit);
        List<LotteryRecord> list = this.LayUiLotteryRecordMapper.selectByExample(example);
        return new LayUiResult("0", "查询成功", new PageInfo<>(list).getTotal(), list);
    }
}
