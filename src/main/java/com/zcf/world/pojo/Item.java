package com.zcf.world.pojo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;
/**
* @author 许宝予
* @date 2019/04/18
*/
@Data
@Table(name = "item")
@ApiModel(value = "商品表模型", description = "商品表信息")
public class Item{
    @Id
    @ApiModelProperty(value = "主键", required = true, position = 1)
    private Integer id;
    @ApiModelProperty(value = "二级分类 id  (0不属于)", position = 3)
    private Integer reclassify;
    @ApiModelProperty(value = "商品名称", position = 4)
    private String itemName;
    @ApiModelProperty(value = "商品介绍", position = 5)
    private String itemIntroduce;
    @ApiModelProperty(value = "商品进价", position = 6)
    private BigDecimal purchasingPrice;
    @ApiModelProperty(value = "商品售价", position = 7)
    private BigDecimal sellingPrice;
    @ApiModelProperty(value = "销量", position = 8)
    private Integer saleCount;
    @ApiModelProperty(value = "商品列表图", position = 9)
    private String images;
    @ApiModelProperty(value = "创建时间", position = 10)
    private Date creatTime;
    @ApiModelProperty(value = "修改时间", position = 11)
    private Date updateTime;
    @ApiModelProperty(value = "是否删除N未删Y已删", position = 12)
    private String deleted;
    @ApiModelProperty(value = "备注", position = 13)
    private String remark;
}
