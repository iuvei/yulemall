package com.zcf.world.DTO;

import com.zcf.world.pojo.ShoppingCart;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ShoppingCartDTO extends ShoppingCart {

    /**
     * 商品名称
     */
    private String shopName;
    /**
     * 商品售价
     */
    private BigDecimal sellingPrice;
    /**
     * 商品列表图
     */
    private String images;
    /**
     * 规格参数列表
     */
    private List specList;
}
