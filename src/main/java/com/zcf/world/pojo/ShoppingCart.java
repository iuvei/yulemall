package com.zcf.world.pojo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
/**
* @author 许宝予
* @date 2019/04/19
*/
@Data
@Table(name = "shopping_cart")
@ApiModel(value = "购物车模型", description = "购物车信息")
public class ShoppingCart{
    @Id
    @ApiModelProperty(value = "主键", required = true, position = 1)
    private Integer id;
    @ApiModelProperty(value = "商品id", position = 2)
    private Integer itemId;
    @ApiModelProperty(value = "用户id", position = 3)
    private Integer userId;
    @ApiModelProperty(value = "规格参数id", position = 4)
    private String spId;
    @ApiModelProperty(value = "个数/数量", position = 5)
    private Integer num;
    @ApiModelProperty(value = "创建时间", position = 6)
    private Date creatTime;
    @ApiModelProperty(value = "修改时间", position = 7)
    private Date updateTime;
    @ApiModelProperty(value = "是否删除(N未删除Y已删)", position = 8)
    private String deleted;
    @ApiModelProperty(value = "备注", position = 9)
    private String remark;
}
