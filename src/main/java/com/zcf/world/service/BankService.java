package com.zcf.world.service;

import com.zcf.world.common.utils.Body;
import com.zcf.world.pojo.Bank;
import com.zcf.world.mapper.BankMapper;
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
* @date 2019/04/22
*/
@Service
public class BankService{

    @Resource
    private BankMapper bankmapper;

    /**
     * 新增一条数据
     *
     * @param bank bank对象
     */
    public void addBank(Bank bank) {
        if (bank.getCreatTime() == null){
            bank.setCreatTime(new Date());
        }
        if (bank.getUpdateTime() == null){
            bank.setUpdateTime(new Date());
        }
        bank.setDeleted("N");
        int count = this.bankmapper.insertSelective(bank);
        if(count != 1){
             throw new CommonException(ExceptionEnum.SAVE_FAILURE);
        }
    }

    /**
     * 根据主键删除数据
     *
     * @param id 主键
     */
    public void deleteBankById(Integer id) {

        Example example = new Example(Bank.class);
        example.createCriteria().andEqualTo("id",id);
        List<Bank> list = this.bankmapper.selectByExample(example);
        if (list.size() != 1){
            throw new CommonException(ExceptionEnum.NULL_LIST);
        }
        Bank bank = new Bank();
        bank.setId(list.get(0).getId());
        bank.setDeleted("Y");
        bank.setUpdateTime(new Date());
        updateBank(bank);
    }

    /**
     * 根据主键更新非空数据
     *
     * @param bank bank对象
     */
    public void updateBank(Bank bank) {
        bank.setUpdateTime(new Date());
        int count = this.bankmapper.updateByPrimaryKeySelective(bank);
        if(count != 1){
             throw new CommonException(ExceptionEnum.UPDATE_FAILURE);
        }
    }

    /**
     * 获取所有表中数据
     *
     * @return Bank对象集合
     */
    public List<Bank> getAllBank() {
        Example example = new Example(Bank.class);
        example.createCriteria().andEqualTo("deleted","N");
        List<Bank> list = this.bankmapper.selectByExample(example);
        if(CollectionUtils.isEmpty(list)){
            throw new CommonException(ExceptionEnum.DATA_DOES_NOT_EXIST);
        }
        return list;
    }

    /**
     * 根据主键获取单一数据
     *
     * @param id 主键
     * @return Bank对象
     */
    public Bank getBank(Integer id){
        Bank Bank = this.bankmapper.selectByPrimaryKey(id);
        if (Bank == null) {
            throw new CommonException(ExceptionEnum.DATA_DOES_NOT_EXIST);
        }
        return Bank;
    }
    /**
     * 根据用户id   获取用户银行卡
     */
    public Body getBankList(Integer userId){
        Example example = new Example(Bank.class);
        example.createCriteria().andEqualTo("deleted","N")
                    .andEqualTo("userId",userId);
        List<Bank> list = this.bankmapper.selectByExample(example);
        return Body.newInstance(list);
    }
}
