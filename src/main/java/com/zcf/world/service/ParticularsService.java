package com.zcf.world.service;

import com.zcf.world.pojo.Particulars;
import com.zcf.world.mapper.ParticularsMapper;
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
public class ParticularsService{

    @Resource
    private ParticularsMapper particularsmapper;

    /**
     * 新增一条数据
     *
     * @param particulars particulars对象
     */
    public void addParticulars(Particulars particulars) {
        if (particulars.getCreatTime() == null){
            particulars.setCreatTime(new Date());
        }
        if (particulars.getUpdateTime() == null){
            particulars.setUpdateTime(new Date());
        }
        particulars.setDeleted("N");
        int count = this.particularsmapper.insertSelective(particulars);
        if(count != 1){
             throw new CommonException(ExceptionEnum.SAVE_FAILURE);
        }
    }

    /**
     * 根据主键删除数据
     *
     * @param id 主键
     */
    public void deleteParticularsById(Integer id) {

        Example example = new Example(Particulars.class);
        example.createCriteria().andEqualTo("id",id);
        List<Particulars> list = this.particularsmapper.selectByExample(example);
        if (list.size() != 1){
            throw new CommonException(ExceptionEnum.NULL_LIST);
        }
        Particulars particulars = new Particulars();
        particulars.setId(list.get(0).getId());
        particulars.setDeleted("Y");
        particulars.setUpdateTime(new Date());
        updateParticulars(particulars);
    }

    /**
     * 根据主键更新非空数据
     *
     * @param particulars particulars对象
     */
    public void updateParticulars(Particulars particulars) {
        particulars.setUpdateTime(new Date());
        int count = this.particularsmapper.updateByPrimaryKeySelective(particulars);
        if(count != 1){
             throw new CommonException(ExceptionEnum.UPDATE_FAILURE);
        }
    }

    /**
     * 获取所有表中数据
     *
     * @return Particulars对象集合
     */
    public List<Particulars> getAllParticulars() {
        Example example = new Example(Particulars.class);
        example.createCriteria().andEqualTo("deleted","N");
        List<Particulars> list = this.particularsmapper.selectByExample(example);
        if(CollectionUtils.isEmpty(list)){
            throw new CommonException(ExceptionEnum.DATA_DOES_NOT_EXIST);
        }
        return list;
    }

    /**
     * 根据主键获取单一数据
     *
     * @param id 主键
     * @return Particulars对象
     */
    public Particulars getParticulars(Integer id){
        Particulars Particulars = this.particularsmapper.selectByPrimaryKey(id);
        if (Particulars == null) {
            throw new CommonException(ExceptionEnum.DATA_DOES_NOT_EXIST);
        }
        return Particulars;
    }

}
