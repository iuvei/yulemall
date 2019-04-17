package com.zcf.world.pojo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
/**
* @author 许宝予
* @date 2019/04/13
*/
@Data
@Table(name = "announcement")
@ApiModel(value = "公告/消息广播模型", description = "公告/消息广播信息")
public class Announcement{
    @Id
    @ApiModelProperty(value = "主键", required = true, position = 1)
    private Integer id;
    @ApiModelProperty(value = "公告内容(消息广播)", position = 2)
    private String content;
    @ApiModelProperty(value = "创建时间", position = 3)
    private Date creatTime;
    @ApiModelProperty(value = "修改时间", position = 4)
    private Date updateTime;
    @ApiModelProperty(value = "是否删除(N未删Y已删)", position = 5)
    private String deleted;
    @ApiModelProperty(value = "备注", position = 6)
    private String remark;
}
