package com.zcf.world.service.layui;

import com.zcf.world.pojo.SignRecord;
import com.zcf.world.mapper.SignRecordMapper;
import com.zcf.world.common.layui.LayUiResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import tk.mybatis.mapper.entity.Example;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
/**
* @author 许宝予
* @date 2019/04/23
*/
@Service
public class LayUiSignRecordService{

    @Resource
    private SignRecordMapper LayUiSignRecordMapper;

    /**
    *新增数据
    */
    public boolean add(SignRecord signRecord) {
        if (signRecord.getCreatTime() == null){
            signRecord.setCreatTime(new Date());
        }
        if (signRecord.getUpdateTime() == null){
            signRecord.setUpdateTime(new Date());
        }
        signRecord.setDeleted("N");
        return this.LayUiSignRecordMapper.insert(signRecord) == 1;
    }
    /**
    *根据主键删除数据
    */
    public boolean delete(Integer id) {
        Example example = new Example(SignRecord.class);
        example.createCriteria().andEqualTo("id",id);
        List<SignRecord> list = this.LayUiSignRecordMapper.selectByExample(example);
        if (list.size() != 1){
            return false;
        }
        SignRecord signRecord = new SignRecord();
        signRecord.setId(list.get(0).getId());
        signRecord.setDeleted("Y");
        signRecord.setUpdateTime(new Date());
        return this.update(signRecord);
    }

    /**
    *根据主键更新非空数据
    */
    public boolean update(SignRecord signRecord) {
        signRecord.setUpdateTime(new Date());
        return this.LayUiSignRecordMapper.updateByPrimaryKeySelective(signRecord) == 1;
    }

    /**
    *分页查询表中数据
    */
    public LayUiResult query(Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        List<SignRecord> list = this.LayUiSignRecordMapper.selectAll();
        return new LayUiResult("0", "查询成功", new PageInfo<>(list).getTotal(), list);
    }

    /**
    *分页并搜索关键字
    */
     public LayUiResult search(Integer page, Integer limit,String keywords) {
        Example example = new Example(SignRecord.class);
        example.createCriteria().andLike("id", "%" + keywords + "%");
        PageHelper.startPage(page, limit);
        List<SignRecord> list = this.LayUiSignRecordMapper.selectByExample(example);
        return new LayUiResult("0", "查询成功", new PageInfo<>(list).getTotal(), list);
    }
}
