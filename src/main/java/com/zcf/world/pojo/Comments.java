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
@Table(name = "comments")
@ApiModel(value = "评论模型", description = "评论信息")
public class Comments{
    @Id
    @ApiModelProperty(value = "主键", required = true, position = 1)
    private Integer id;
    @ApiModelProperty(value = "用户id", position = 2)
    private Integer userId;
    @ApiModelProperty(value = "商品id", position = 3)
    private Integer itemId;
    @ApiModelProperty(value = "评论内容", position = 4)
    private String comments;
    @ApiModelProperty(value = "评论图片", position = 5)
    private String images;
    @ApiModelProperty(value = "创建时间", position = 6)
    private Date creatTime;
    @ApiModelProperty(value = "修改时间", position = 7)
    private Date updateTime;
    @ApiModelProperty(value = "是否删除N未删Y已删", position = 8)
    private String deleted;
    @ApiModelProperty(value = "备注", position = 9)
    private String remark;
}
