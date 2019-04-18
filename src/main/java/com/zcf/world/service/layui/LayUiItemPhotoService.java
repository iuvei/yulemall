package com.zcf.world.service.layui;

import com.zcf.world.pojo.ItemPhoto;
import com.zcf.world.mapper.ItemPhotoMapper;
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
public class LayUiItemPhotoService{

    @Resource
    private ItemPhotoMapper LayUiItemPhotoMapper;

    /**
    *新增数据
    */
    public boolean add(ItemPhoto itemPhoto) {
        if (itemPhoto.getCreatTime() == null){
            itemPhoto.setCreatTime(new Date());
        }
        if (itemPhoto.getUpdateTime() == null){
            itemPhoto.setUpdateTime(new Date());
        }
        itemPhoto.setDeleted("N");
        return this.LayUiItemPhotoMapper.insert(itemPhoto) == 1;
    }
    /**
    *根据主键删除数据
    */
    public boolean delete(Integer id) {
        Example example = new Example(ItemPhoto.class);
        example.createCriteria().andEqualTo("id",id);
        List<ItemPhoto> list = this.LayUiItemPhotoMapper.selectByExample(example);
        if (list.size() != 1){
        return false;
        }
        ItemPhoto itemPhoto = new ItemPhoto();
        itemPhoto.setId(list.get(0).getId());
        itemPhoto.setDeleted("Y");
        itemPhoto.setUpdateTime(new Date());
        return update(itemPhoto);
    }

    /**
    *根据主键更新非空数据
    */
    public boolean update(ItemPhoto itemPhoto) {
        itemPhoto.setUpdateTime(new Date());
        return this.LayUiItemPhotoMapper.updateByPrimaryKeySelective(itemPhoto) == 1;
    }

    /**
    *分页查询表中数据
    */
    public LayUiResult query(Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        List<ItemPhoto> list = this.LayUiItemPhotoMapper.selectAll();
        return new LayUiResult("0", "查询成功", new PageInfo<>(list).getTotal(), list);
    }

    /**
    *分页并搜索关键字
    */
     public LayUiResult search(Integer page, Integer limit,String keywords) {
        Example example = new Example(ItemPhoto.class);
        example.createCriteria().andLike("id", "%" + keywords + "%");
        PageHelper.startPage(page, limit);
        List<ItemPhoto> list = this.LayUiItemPhotoMapper.selectByExample(example);
        return new LayUiResult("0", "查询成功", new PageInfo<>(list).getTotal(), list);
    }
}
