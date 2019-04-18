package com.zcf.world.service.layui;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zcf.world.common.layui.LayUiResult;
import com.zcf.world.mapper.BettingRecordMapper;
import com.zcf.world.pojo.BettingRecord;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;
/**
* @author 许宝予
* @date 2019/04/17
*/
@Service
public class LayUiBettingRecordService{

    @Resource
    private BettingRecordMapper LayUiBettingRecordMapper;

    /**
    *新增数据
    */
    public boolean add(BettingRecord bettingRecord) {
        return this.LayUiBettingRecordMapper.insert(bettingRecord) == 1;
    }
    /**
    *根据主键删除数据
    */
    public boolean delete(Integer id) {
        return this.LayUiBettingRecordMapper.deleteByPrimaryKey(id) == 1;
    }

    /**
    *根据主键更新非空数据
    */
    public boolean update(BettingRecord bettingRecord) {
        return this.LayUiBettingRecordMapper.updateByPrimaryKeySelective(bettingRecord) == 1;
    }

    /**
    *分页查询表中数据
    */
    public LayUiResult query(Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        List<BettingRecord> list = this.LayUiBettingRecordMapper.selectAll();
        return new LayUiResult("0", "查询成功", new PageInfo<>(list).getTotal(), list);
    }

    /**
    *分页并搜索关键字
    */
     public LayUiResult search(Integer page, Integer limit,String keywords) {
        Example example = new Example(BettingRecord.class);
        example.createCriteria().andLike("id", "%" + keywords + "%");
        PageHelper.startPage(page, limit);
        List<BettingRecord> list = this.LayUiBettingRecordMapper.selectByExample(example);
        return new LayUiResult("0", "查询成功", new PageInfo<>(list).getTotal(), list);
    }
}
