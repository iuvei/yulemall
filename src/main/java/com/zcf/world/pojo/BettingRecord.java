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
* @date 2019/04/17
*/
@Data
@Table(name = "betting_record")
@ApiModel(value = "用户投注记录表模型", description = "用户投注记录表信息")
public class BettingRecord{
    @Id
    @ApiModelProperty(value = "主键", required = true, position = 1)
    private Integer id;
    @ApiModelProperty(value = "用户id", position = 2)
    private Integer userid;
    @ApiModelProperty(value = "添加时间", position = 3)
    private Date creatTime;
    @ApiModelProperty(value = "修改时间", position = 4)
    private Date updateTime;
    @ApiModelProperty(value = "是否撤单(N未删Y已删)", position = 5)
    private String deleted;
    @ApiModelProperty(value = "投注金额", position = 6)
    private Integer toumoney;
    @ApiModelProperty(value = "期号", position = 7)
    private String issue;
    @ApiModelProperty(value = "玩法id", position = 8)
    private Integer gameid;
    @ApiModelProperty(value = "投注内容", position = 9)
    private String touccontent;
    @ApiModelProperty(value = "开奖状态(0未开奖1中奖2未中奖)", position = 10)
    private String type;
    @ApiModelProperty(value = "获得金额", position = 11)
    private BigDecimal getmoney;
    @ApiModelProperty(value = "备注", position = 12)
    private String remark;
}
