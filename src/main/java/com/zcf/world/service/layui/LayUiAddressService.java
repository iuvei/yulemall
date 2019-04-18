package com.zcf.world.service.layui;

import com.zcf.world.pojo.Address;
import com.zcf.world.mapper.AddressMapper;
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
* @date 2019/04/18
*/
@Service
public class LayUiAddressService{

    @Resource
    private AddressMapper LayUiAddressMapper;

    /**
    *新增数据
    */
    public boolean add(Address address) {
        if (address.getCreatTime() == null){
            address.setCreatTime(new Date());
        }
        if (address.getUpdateTime() == null){
            address.setUpdateTime(new Date());
        }
        address.setDeleted("N");
        return this.LayUiAddressMapper.insert(address) == 1;
    }
    /**
    *根据主键删除数据
    */
    public boolean delete(Integer id) {
        Example example = new Example(Address.class);
        example.createCriteria().andEqualTo("id",id);
        List<Address> list = this.LayUiAddressMapper.selectByExample(example);
        if (list.size() != 1){
            return false;
        }
        Address address = new Address();
        address.setId(list.get(0).getId());
        address.setDeleted("Y");
        address.setUpdateTime(new Date());
        return this.update(address);
    }

    /**
    *根据主键更新非空数据
    */
    public boolean update(Address address) {
        address.setUpdateTime(new Date());
        return this.LayUiAddressMapper.updateByPrimaryKeySelective(address) == 1;
    }

    /**
    *分页查询表中数据
    */
    public LayUiResult query(Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        List<Address> list = this.LayUiAddressMapper.selectAll();
        return new LayUiResult("0", "查询成功", new PageInfo<>(list).getTotal(), list);
    }

    /**
    *分页并搜索关键字
    */
     public LayUiResult search(Integer page, Integer limit,String keywords) {
        Example example = new Example(Address.class);
        example.createCriteria().andLike("id", "%" + keywords + "%");
        PageHelper.startPage(page, limit);
        List<Address> list = this.LayUiAddressMapper.selectByExample(example);
        return new LayUiResult("0", "查询成功", new PageInfo<>(list).getTotal(), list);
    }
}
