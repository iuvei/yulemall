package com.zcf.world.service;

import com.zcf.world.pojo.Orderlist;
import com.zcf.world.mapper.OrderlistMapper;
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
public class OrderlistService{

    @Resource
    private OrderlistMapper orderlistmapper;

    /**
     * 新增一条数据
     *
     * @param orderlist orderlist对象
     */
    public void addOrderlist(Orderlist orderlist) {
        if (orderlist.getCreatTime() == null){
            orderlist.setCreatTime(new Date());
        }
        if (orderlist.getUpdateTime() == null){
            orderlist.setUpdateTime(new Date());
        }
        orderlist.setDeleted("N");
        int count = this.orderlistmapper.insertSelective(orderlist);
        if(count != 1){
             throw new CommonException(ExceptionEnum.SAVE_FAILURE);
        }
    }

    /**
     * 根据主键删除数据
     *
     * @param id 主键
     */
    public void deleteOrderlistById(Integer id) {

        Example example = new Example(Orderlist.class);
        example.createCriteria().andEqualTo("id",id);
        List<Orderlist> list = this.orderlistmapper.selectByExample(example);
        if (list.size() != 1){
            throw new CommonException(ExceptionEnum.NULL_LIST);
        }
        Orderlist orderlist = new Orderlist();
        orderlist.setId(list.get(0).getId());
        orderlist.setDeleted("Y");
        orderlist.setUpdateTime(new Date());
        updateOrderlist(orderlist);
    }

    /**
     * 根据主键更新非空数据
     *
     * @param orderlist orderlist对象
     */
    public void updateOrderlist(Orderlist orderlist) {
        orderlist.setUpdateTime(new Date());
        int count = this.orderlistmapper.updateByPrimaryKeySelective(orderlist);
        if(count != 1){
             throw new CommonException(ExceptionEnum.UPDATE_FAILURE);
        }
    }

    /**
     * 获取所有表中数据
     *
     * @return Orderlist对象集合
     */
    public List<Orderlist> getAllOrderlist() {
        Example example = new Example(Orderlist.class);
        example.createCriteria().andEqualTo("deleted","N");
        List<Orderlist> list = this.orderlistmapper.selectByExample(example);
        if(CollectionUtils.isEmpty(list)){
            throw new CommonException(ExceptionEnum.DATA_DOES_NOT_EXIST);
        }
        return list;
    }

    /**
     * 根据主键获取单一数据
     *
     * @param id 主键
     * @return Orderlist对象
     */
    public Orderlist getOrderlist(Integer id){
        Orderlist Orderlist = this.orderlistmapper.selectByPrimaryKey(id);
        if (Orderlist == null) {
            throw new CommonException(ExceptionEnum.DATA_DOES_NOT_EXIST);
        }
        return Orderlist;
    }



}
