package com.zcf.world.pojo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
/**
* @author 许宝予
* @date 2019/04/18
*/
@Data
@Table(name = "item_photo")
@ApiModel(value = "商品详情图模型", description = "商品详情图信息")
public class ItemPhoto{
    @Id
    @ApiModelProperty(value = "主键", required = true, position = 1)
    private Integer id;
    @ApiModelProperty(value = "商品id", position = 2)
    private Integer itemId;
    @ApiModelProperty(value = "商品图片", position = 3)
    private String img;
    @ApiModelProperty(value = "创建时间", position = 4)
    private Date creatTime;
    @ApiModelProperty(value = "修改时间", position = 5)
    private Date updateTime;
    @ApiModelProperty(value = "是否删除N未删Y已删", position = 6)
    private String deleted;
    @ApiModelProperty(value = "备注", position = 7)
    private String remark;
}
