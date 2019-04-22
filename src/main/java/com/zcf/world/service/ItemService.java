package com.zcf.world.service;

import com.zcf.world.DTO.ItemDTO;
import com.zcf.world.DTO.SpecDTO;
import com.zcf.world.common.exception.CommonException;
import com.zcf.world.common.exception.ExceptionEnum;
import com.zcf.world.common.utils.Body;
import com.zcf.world.mapper.ItemMapper;
import com.zcf.world.mapper.ItemPhotoMapper;
import com.zcf.world.mapper.ShoppingCartMapper;
import com.zcf.world.pojo.Item;
import com.zcf.world.pojo.ItemPhoto;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
* @author 许宝予
* @date 2019/04/18
*/
@Service
public class ItemService{

    @Resource
    private ItemMapper itemmapper;
    @Resource
    private ItemPhotoMapper itemPhotoMapper;
    @Resource
    private ShoppingCartMapper shoppingCartMapper;

    /**
     * 新增一条数据
     *
     * @param item item对象
     */
    public void addItem(Item item) {
        if (item.getCreatTime() == null){
            item.setCreatTime(new Date());
        }
        if (item.getUpdateTime() == null){
            item.setUpdateTime(new Date());
        }
        item.setDeleted("N");
        int count = this.itemmapper.insertSelective(item);
        if(count != 1){
             throw new CommonException(ExceptionEnum.SAVE_FAILURE);
        }
    }

    /**
     * 根据主键删除数据
     *
     * @param id 主键
     */
    public void deleteItemById(Integer id) {

        Example example = new Example(Item.class);
        example.createCriteria().andEqualTo("id",id);
        List<Item> list = this.itemmapper.selectByExample(example);
        if (list.size() != 1){
            throw new CommonException(ExceptionEnum.NULL_LIST);
        }
        Item item = new Item();
        item.setId(list.get(0).getId());
        item.setDeleted("Y");
        item.setUpdateTime(new Date());
        updateItem(item);
    }

    /**
     * 根据主键更新非空数据
     *
     * @param item item对象
     */
    public void updateItem(Item item) {
        item.setUpdateTime(new Date());
        int count = this.itemmapper.updateByPrimaryKeySelective(item);
        if(count != 1){
             throw new CommonException(ExceptionEnum.UPDATE_FAILURE);
        }
    }

    /**
     * 获取所有表中数据
     *
     * @return Item对象集合
     */
    public List<Item> getAllItem() {
        Example example = new Example(Item.class);
        example.createCriteria().andEqualTo("deleted","N");
        List<Item> list = this.itemmapper.selectByExample(example);
        if(CollectionUtils.isEmpty(list)){
            throw new CommonException(ExceptionEnum.DATA_DOES_NOT_EXIST);
        }
        return list;
    }

    /**
     * 根据主键获取单一数据
     *
     * @param id 主键
     * @return Item对象
     */
    public Item getItem(Integer id){
        Item Item = this.itemmapper.selectByPrimaryKey(id);
        if (Item == null) {
            throw new CommonException(ExceptionEnum.DATA_DOES_NOT_EXIST);
        }
        return Item;
    }

    /**
     * 首页搜索
     */
    public Body getsecrchItem(String search){
        List<Item> items = itemmapper.getsecrchItem(search);
        return Body.newInstance(items);
    }

    /**
     * 猜你喜欢   10个商品
     */
    public Body getCountTen(){
        List<Item> items = itemmapper.getCountTen();
        return Body.newInstance(items);
    }


    public Body getOneItemDTO(Integer id){
        //DTO对象
        List<ItemDTO> itemDTOList = new ArrayList<>();
        ItemDTO itemDTO = new ItemDTO();

        //根据   商品id   获取商品图片
        Example example = new Example(Item.class);
        example.createCriteria().andEqualTo("deleted","N")
                .andEqualTo("id",id);
        List<Item> list = this.itemmapper.selectByExample(example);
        if (list.size() != 1){
            throw new CommonException(ExceptionEnum.LIST_THROW);
        }
        //获取商品图集
        Example e = new Example(ItemPhoto.class);
        e.createCriteria().andEqualTo("deleted","N")
                .andEqualTo("id",id);
        List<ItemPhoto> itemPhotos = this.itemPhotoMapper.selectByExample(e);
        List list1 = new ArrayList();
        for (int y = 0; y<itemPhotos.size();y++ ){
            list1.add(itemPhotos.get(y).getImg());
        }
        itemDTO.setItemPhoto(list1);
        //获取商品信息
        for (int i = 0;i<list.size();i++){
            itemDTO.setItemName(list.get(i).getItemName());
            itemDTO.setItemIntroduce(list.get(i).getItemIntroduce());
            itemDTO.setSaleCount(list.get(i).getSaleCount());
            itemDTO.setSellingPrice(list.get(i).getSellingPrice());
            itemDTO.setPurchasingPrice(list.get(i).getPurchasingPrice());
            itemDTO.setImages(list.get(i).getImages());
        }
        List<SpecDTO> spec = itemmapper.getSpec(id);
        itemDTO.setSpecList(spec);

        itemDTOList.add(itemDTO);
        return Body.newInstance(itemDTOList);
    }
}
