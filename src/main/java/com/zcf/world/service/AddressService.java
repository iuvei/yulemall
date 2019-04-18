package com.zcf.world.service;

import com.zcf.world.pojo.Address;
import com.zcf.world.mapper.AddressMapper;
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
* @date 2019/04/18
*/
@Service
public class AddressService{

    @Resource
    private AddressMapper addressmapper;

    /**
     * 新增一条数据
     *
     * @param address address对象
     */
    public void addAddress(Address address) {
        if (address.getCreatTime() == null){
            address.setCreatTime(new Date());
        }
        if (address.getUpdateTime() == null){
            address.setUpdateTime(new Date());
        }
        address.setDeleted("N");
        int count = this.addressmapper.insertSelective(address);
        if(count != 1){
             throw new CommonException(ExceptionEnum.SAVE_FAILURE);
        }
    }

    /**
     * 根据主键删除数据
     *
     * @param id 主键
     */
    public void deleteAddressById(Integer id) {

        Example example = new Example(Address.class);
        example.createCriteria().andEqualTo("id",id);
        List<Address> list = this.addressmapper.selectByExample(example);
        if (list.size() != 1){
            throw new CommonException(ExceptionEnum.NULL_LIST);
        }
        Address address = new Address();
        address.setId(list.get(0).getId());
        address.setDeleted("Y");
        address.setUpdateTime(new Date());
        updateAddress(address);
    }

    /**
     * 根据主键更新非空数据
     *
     * @param address address对象
     */
    public void updateAddress(Address address) {
        address.setUpdateTime(new Date());
        int count = this.addressmapper.updateByPrimaryKeySelective(address);
        if(count != 1){
             throw new CommonException(ExceptionEnum.UPDATE_FAILURE);
        }
    }

    /**
     * 获取所有表中数据
     *
     * @return Address对象集合
     */
    public List<Address> getAllAddress() {
        Example example = new Example(Address.class);
        example.createCriteria().andEqualTo("deleted","N");
        List<Address> list = this.addressmapper.selectByExample(example);
        if(CollectionUtils.isEmpty(list)){
            throw new CommonException(ExceptionEnum.DATA_DOES_NOT_EXIST);
        }
        return list;
    }

    /**
     * 根据主键获取单一数据
     *
     * @param id 主键
     * @return Address对象
     */
    public Address getAddress(Integer id){
        Address Address = this.addressmapper.selectByPrimaryKey(id);
        if (Address == null) {
            throw new CommonException(ExceptionEnum.DATA_DOES_NOT_EXIST);
        }
        return Address;
    }

}
