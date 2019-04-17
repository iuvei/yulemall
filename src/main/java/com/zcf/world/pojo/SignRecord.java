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
@Table(name = "sign_record")
@ApiModel(value = "用户签到记录表模型", description = "用户签到记录表信息")
public class SignRecord{
    @Id
    @ApiModelProperty(value = "主键", required = true, position = 1)
    private Integer id;
    @ApiModelProperty(value = "用户id", position = 2)
    private Integer userid;
    @ApiModelProperty(value = "添加时间", position = 3)
    private Date creatTime;
    @ApiModelProperty(value = "修改时间", position = 4)
    private Date updateTime;
    @ApiModelProperty(value = "是否删除N未删Y已删", position = 5)
    private String deleted;
    @ApiModelProperty(value = "备注", position = 6)
    private String remark;
}
