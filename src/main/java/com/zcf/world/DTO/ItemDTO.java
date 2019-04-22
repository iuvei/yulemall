package com.zcf.world.DTO;

import com.zcf.world.pojo.Item;
import lombok.Data;

import java.util.List;

@Data
public class ItemDTO extends Item {

    /**
     *  商品图片集
     */
    private List itemPhoto;

    /**
     * 规格参数集
     */
    private List specList;

}
