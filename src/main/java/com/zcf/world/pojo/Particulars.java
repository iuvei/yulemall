package com.zcf.world.pojo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
/**
* @author 许宝予
* @date 2019/04/22
*/
@Data
@Table(name = "particulars")
@ApiModel(value = "售后申请模型", description = "售后申请信息")
public class Particulars{
    @Id
    @ApiModelProperty(value = "主键", required = true, position = 1)
    private Integer id;
    @ApiModelProperty(value = "用户id", position = 10)
    private Integer userId;
    @ApiModelProperty(value = "订单id", position = 2)
    private Integer orderlistId;
    @ApiModelProperty(value = "订单号", position = 3)
    private String buyNumbers;
    @ApiModelProperty(value = "服务类型(1换货2退款退货)", position = 4)
    private Integer type;
    @ApiModelProperty(value = "详细说明", position = 5)
    private String particulars;
    @ApiModelProperty(value = "创建时间", position = 6)
    private Date creatTime;
    @ApiModelProperty(value = "修改时间", position = 7)
    private Date updateTime;
    @ApiModelProperty(value = "是否删除N未删Y已删", position = 8)
    private String deleted;
    @ApiModelProperty(value = "备注", position = 9)
    private String remark;
}
