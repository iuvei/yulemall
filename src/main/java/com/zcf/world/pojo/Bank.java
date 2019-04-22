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
@Table(name = "bank")
@ApiModel(value = "用户银行卡模型", description = "用户银行卡信息")
public class Bank{
    @Id
    @ApiModelProperty(value = "主键", required = true, position = 1)
    private Integer id;
    @ApiModelProperty(value = "用户id", position = 2)
    private Integer userId;
    @ApiModelProperty(value = "银行卡", position = 3)
    private String bankCard;
    @ApiModelProperty(value = "创建时间", position = 4)
    private Date creatTime;
    @ApiModelProperty(value = "修改时间", position = 5)
    private Date updateTime;
    @ApiModelProperty(value = "是否删除N未删Y已删", position = 6)
    private String deleted;
    @ApiModelProperty(value = "备注", position = 7)
    private String remark;
    @ApiModelProperty(value = "持卡人", position = 8)
    private String names;
    @ApiModelProperty(value = "银行", position = 9)
    private String bank;
    @ApiModelProperty(value = "手机号", position = 10)
    private String phone;
}
