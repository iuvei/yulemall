package com.zcf.world.pojo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
/**
* @author 许宝予
* @date 2019/04/17
*/
@Data
@Table(name = "lottery_record")
@ApiModel(value = "开奖记录表模型", description = "开奖记录表信息")
public class LotteryRecord{
    @Id
    @ApiModelProperty(value = "主键", required = true, position = 1)
    private Integer id;
    @ApiModelProperty(value = "期号", position = 2)
    private Integer issue;
    @ApiModelProperty(value = "开奖号码", position = 3)
    private String lotterynum;
    @ApiModelProperty(value = "添加时间", position = 4)
    private Date creatTime;
    @ApiModelProperty(value = "修改时间", position = 5)
    private Date updateTime;
    @ApiModelProperty(value = "开奖时间", position = 6)
    private Date kaiTime;
    @ApiModelProperty(value = "封盘时间", position = 7)
    private Date fengTime;
    @ApiModelProperty(value = "开奖状态(0未开奖1已开奖)", position = 8)
    private String type;
    @ApiModelProperty(value = "是否删除N未删Y已删", position = 9)
    private String deleted;
    @ApiModelProperty(value = "备注", position = 10)
    private String remark;
}
