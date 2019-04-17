package com.zcf.world.service;

import com.zcf.world.pojo.Qrcode;
import com.zcf.world.mapper.QrcodeMapper;
import tk.mybatis.mapper.entity.Example;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import com.zcf.world.common.exception.CommonException;
import com.zcf.world.common.exception.ExceptionEnum;
import java.util.List;
/**
* @author 许宝予
* @date 2019/04/17
*/
@Service
public class QrcodeService{

    @Autowired
    private QrcodeMapper qrcodemapper;

    /**
     * 新增一条数据
     *
     * @param qrcode qrcode对象
     */
    public void addQrcode(Qrcode qrcode) {
        int count = this.qrcodemapper.insertSelective(qrcode);
        if(count != 1){
             throw new CommonException(ExceptionEnum.SAVE_FAILURE);
        }
    }

    /**
     * 根据主键删除数据
     *
     * @param id 主键
     */
    public void deleteQrcodeById(Integer id) {
        int count = this.qrcodemapper.deleteByPrimaryKey(id);
        if(count != 1){
             throw new CommonException(ExceptionEnum.DELETE_FAILURE);
        }
    }

    /**
     * 根据主键更新非空数据
     *
     * @param qrcode qrcode对象
     */
    public void updateQrcode(Qrcode qrcode) {
        int count = this.qrcodemapper.updateByPrimaryKeySelective(qrcode);
         if(count != 1){
             throw new CommonException(ExceptionEnum.UPDATE_FAILURE);
        }
    }

    /**
     * 获取所有表中数据
     *
     * @return Qrcode对象集合
     */
    public List<Qrcode> getAllQrcode() {
        List<Qrcode> list = this.qrcodemapper.selectAll();
       if(CollectionUtils.isEmpty(list)){
            throw new CommonException(ExceptionEnum.DATA_DOES_NOT_EXIST);
        }
        return list;
    }

    /**
     * 根据主键获取单一数据
     *
     * @param id 主键
     * @return Qrcode对象
     */
    public Qrcode getQrcode(Integer id){
        Qrcode Qrcode = this.qrcodemapper.selectByPrimaryKey(id);
        if (Qrcode == null) {
            throw new CommonException(ExceptionEnum.DATA_DOES_NOT_EXIST);
        }
        return Qrcode;
    }

}
