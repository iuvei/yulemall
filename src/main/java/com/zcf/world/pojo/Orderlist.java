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
* @date 2019/04/22
*/
@Data
@Table(name = "orderlist")
@ApiModel(value = "订单表模型", description = "订单表信息")
public class Orderlist{
    @Id
    @ApiModelProperty(value = "主键", required = true, position = 1)
    private Integer id;
    @ApiModelProperty(value = "用户id", position = 2)
    private Integer userId;
    @ApiModelProperty(value = "商品id", position = 3)
    private Integer itemId;
    @ApiModelProperty(value = "规格参数id(，隔开)", position = 4)
    private String spNames;
    @ApiModelProperty(value = "地址id", position = 5)
    private Integer addressId;
    @ApiModelProperty(value = "订单号", position = 6)
    private String buyNumbers;
    @ApiModelProperty(value = "数量", position = 7)
    private Integer num;
    @ApiModelProperty(value = "总价", position = 8)
    private BigDecimal totalMoney;
    @ApiModelProperty(value = "订单状态（1待付款2待发货3待收货4待评论5售后申请6申请记录）", position = 9)
    private String type;
    @ApiModelProperty(value = "创建时间", position = 10)
    private Date creatTime;
    @ApiModelProperty(value = "修改时间", position = 11)
    private Date updateTime;
    @ApiModelProperty(value = "是否删除N未删Y已删", position = 12)
    private String deleted;
    @ApiModelProperty(value = "备注", position = 13)
    private String remark;
    @ApiModelProperty(value = "实付金额", position = 14)
    private BigDecimal moneys;

}