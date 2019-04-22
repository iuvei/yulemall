package com.zcf.world.service.layui;

import com.zcf.world.pojo.Bank;
import com.zcf.world.mapper.BankMapper;
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
* @date 2019/04/22
*/
@Service
public class LayUiBankService{

    @Resource
    private BankMapper LayUiBankMapper;

    /**
    *新增数据
    */
    public boolean add(Bank bank) {
        if (bank.getCreatTime() == null){
            bank.setCreatTime(new Date());
        }
        if (bank.getUpdateTime() == null){
            bank.setUpdateTime(new Date());
        }
        bank.setDeleted("N");
        return this.LayUiBankMapper.insert(bank) == 1;
    }
    /**
    *根据主键删除数据
    */
    public boolean delete(Integer id) {
        Example example = new Example(Bank.class);
        example.createCriteria().andEqualTo("id",id);
        List<Bank> list = this.LayUiBankMapper.selectByExample(example);
        if (list.size() != 1){
            return false;
        }
        Bank bank = new Bank();
        bank.setId(list.get(0).getId());
        bank.setDeleted("Y");
        bank.setUpdateTime(new Date());
        return this.update(bank);
    }

    /**
    *根据主键更新非空数据
    */
    public boolean update(Bank bank) {
        bank.setUpdateTime(new Date());
        return this.LayUiBankMapper.updateByPrimaryKeySelective(bank) == 1;
    }

    /**
    *分页查询表中数据
    */
    public LayUiResult query(Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        List<Bank> list = this.LayUiBankMapper.selectAll();
        return new LayUiResult("0", "查询成功", new PageInfo<>(list).getTotal(), list);
    }

    /**
    *分页并搜索关键字
    */
     public LayUiResult search(Integer page, Integer limit,String keywords) {
        Example example = new Example(Bank.class);
        example.createCriteria().andLike("id", "%" + keywords + "%");
        PageHelper.startPage(page, limit);
        List<Bank> list = this.LayUiBankMapper.selectByExample(example);
        return new LayUiResult("0", "查询成功", new PageInfo<>(list).getTotal(), list);
    }
}
