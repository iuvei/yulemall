package com.zcf.world.service;

import com.zcf.world.pojo.Admins;
import com.zcf.world.mapper.AdminsMapper;
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
* @date 2019/04/16
*/
@Service
public class AdminsService{

    @Resource
    private AdminsMapper adminsmapper;

    /**
     * 新增一条数据
     *
     * @param admins admins对象
     */
    public void addAdmins(Admins admins) {
        int count = this.adminsmapper.insertSelective(admins);
        if(count != 1){
             throw new CommonException(ExceptionEnum.SAVE_FAILURE);
        }
    }

    /**
     * 根据主键删除数据
     *
     * @param id 主键
     */
    public void deleteAdminsById(Integer id) {
        int count = this.adminsmapper.deleteByPrimaryKey(id);
        if(count != 1){
             throw new CommonException(ExceptionEnum.DELETE_FAILURE);
        }
    }

    /**
     * 根据主键更新非空数据
     *
     * @param admins admins对象
     */
    public void updateAdmins(Admins admins) {
        int count = this.adminsmapper.updateByPrimaryKeySelective(admins);
         if(count != 1){
             throw new CommonException(ExceptionEnum.UPDATE_FAILURE);
        }
    }

    /**
     * 获取所有表中数据
     *
     * @return Admins对象集合
     */
    public List<Admins> getAllAdmins() {
        List<Admins> list = this.adminsmapper.selectAll();
       if(CollectionUtils.isEmpty(list)){
            throw new CommonException(ExceptionEnum.DATA_DOES_NOT_EXIST);
        }
        return list;
    }

    /**
     * 根据主键获取单一数据
     *
     * @param id 主键
     * @return Admins对象
     */
    public Admins getAdmins(Integer id){
        Admins Admins = this.adminsmapper.selectByPrimaryKey(id);
        if (Admins == null) {
            throw new CommonException(ExceptionEnum.DATA_DOES_NOT_EXIST);
        }
        return Admins;
    }

}
