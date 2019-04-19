package com.zcf.world.DTO;

import com.zcf.world.pojo.ShoppingCart;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ShoppingCartDTO extends ShoppingCart {

//    private Integer shopId;

    /**
     * 商品名称
     */
    private String shopName;

    /**
     * 商品规格
     */
    private String spec;
    /**
     * 商品规格参数
     */
    private String spNames;
    /**
     * 商品售价
     */
    private BigDecimal sellingPrice;
    /**
     * 商品列表图
     */
    private String images;

    private List specList;

    private List spNamesList;
}
