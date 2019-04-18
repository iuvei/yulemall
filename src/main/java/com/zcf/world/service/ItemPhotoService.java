package com.zcf.world.service;

import com.zcf.world.pojo.ItemPhoto;
import com.zcf.world.mapper.ItemPhotoMapper;
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
public class ItemPhotoService{

    @Resource
    private ItemPhotoMapper itemPhotomapper;

    /**
     * 新增一条数据
     *
     * @param itemPhoto itemPhoto对象
     */
    public void addItemPhoto(ItemPhoto itemPhoto) {
        if (itemPhoto.getCreatTime() == null){
            itemPhoto.setCreatTime(new Date());
        }
        if (itemPhoto.getUpdateTime() == null){
            itemPhoto.setUpdateTime(new Date());
        }
        itemPhoto.setDeleted("N");
        int count = this.itemPhotomapper.insertSelective(itemPhoto);
        if(count != 1){
             throw new CommonException(ExceptionEnum.SAVE_FAILURE);
        }
    }

    /**
     * 根据主键删除数据
     *
     * @param id 主键
     */
    public void deleteItemPhotoById(Integer id) {

        Example example = new Example(ItemPhoto.class);
        example.createCriteria().andEqualTo("id",id);
        List<ItemPhoto> list = this.itemPhotomapper.selectByExample(example);
        if (list.size() != 1){
            throw new CommonException(ExceptionEnum.NULL_LIST);
        }
        ItemPhoto itemPhoto = new ItemPhoto();
        itemPhoto.setId(list.get(0).getId());
        itemPhoto.setDeleted("Y");
        itemPhoto.setUpdateTime(new Date());
        updateItemPhoto(itemPhoto);
    }

    /**
     * 根据主键更新非空数据
     *
     * @param itemPhoto itemPhoto对象
     */
    public void updateItemPhoto(ItemPhoto itemPhoto) {
        itemPhoto.setUpdateTime(new Date());
        int count = this.itemPhotomapper.updateByPrimaryKeySelective(itemPhoto);
        if(count != 1){
             throw new CommonException(ExceptionEnum.UPDATE_FAILURE);
        }
    }

    /**
     * 获取所有表中数据
     *
     * @return ItemPhoto对象集合
     */
    public List<ItemPhoto> getAllItemPhoto() {
        Example example = new Example(ItemPhoto.class);
        example.createCriteria().andEqualTo("deleted","N");
        List<ItemPhoto> list = this.itemPhotomapper.selectByExample(example);
        if(CollectionUtils.isEmpty(list)){
            throw new CommonException(ExceptionEnum.DATA_DOES_NOT_EXIST);
        }
        return list;
    }

    /**
     * 根据主键获取单一数据
     *
     * @param id 主键
     * @return ItemPhoto对象
     */
    public ItemPhoto getItemPhoto(Integer id){
        ItemPhoto ItemPhoto = this.itemPhotomapper.selectByPrimaryKey(id);
        if (ItemPhoto == null) {
            throw new CommonException(ExceptionEnum.DATA_DOES_NOT_EXIST);
        }
        return ItemPhoto;
    }

}
